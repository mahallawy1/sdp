package test.patterns.singleTon;

import MODEL.Patterns.singleton.DbConnectionSingleton;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DbConnectionSingletonTest {

    private DbConnectionSingleton dbSingleton;

    @BeforeAll
    void initAll() {
        System.out.println("Running @BeforeAll - Executed once before all test methods in this class");
    }

    @Test
    void testSingletonInstanceCreation() {
        System.out.println("Running testSingletonInstanceCreation");
        dbSingleton = DbConnectionSingleton.getInstance();
        assertNotNull(dbSingleton, "Expected DbConnectionSingleton instance to be non-null");
    }

    @Test
    void testSingletonInstanceConsistency() {
        System.out.println("Running testSingletonInstanceConsistency");
        DbConnectionSingleton firstInstance = DbConnectionSingleton.getInstance();
        DbConnectionSingleton secondInstance = DbConnectionSingleton.getInstance();
        assertSame(firstInstance, secondInstance, "Expected only one instance of DbConnectionSingleton");
    }

    @Test
    void testConnectionNotNull() throws SQLException {
        System.out.println("Running testConnectionNotNull");
        dbSingleton = DbConnectionSingleton.getInstance();
        try (Connection connection = dbSingleton.getConnection()) {
            assertNotNull(connection, "Expected a non-null connection");
            assertFalse(connection.isClosed(), "Expected the connection to be open");
        }
    }

    @Test
    void testConnectionClose() throws SQLException {
        System.out.println("Running testConnectionClose");
        dbSingleton = DbConnectionSingleton.getInstance();
        Connection connection = dbSingleton.getConnection();
        dbSingleton.close(connection, null);
        assertTrue(connection.isClosed(), "Expected the connection to be closed");
    }

    @Test
    void testSingletonThreadSafety() throws InterruptedException, ExecutionException {
        System.out.println("Running testSingletonThreadSafety");

        Callable<DbConnectionSingleton> task = DbConnectionSingleton::getInstance;
        ExecutorService executor = Executors.newFixedThreadPool(10);

        // Submit 10 tasks and get futures
        Future<DbConnectionSingleton>[] futures = new Future[10];
        for (int i = 0; i < futures.length; i++) {
            futures[i] = executor.submit(task);
        }

        DbConnectionSingleton firstInstance = futures[0].get();

        // Check that all instances returned from different threads are the same
        for (Future<DbConnectionSingleton> future : futures) {
            assertSame(firstInstance, future.get(), "Expected all threads to receive the same instance");
        }

        executor.shutdown();
    }

    @Test
    void testMultipleConnections() throws SQLException {
        System.out.println("Running testMultipleConnections");
        dbSingleton = DbConnectionSingleton.getInstance();

        Connection firstConnection = dbSingleton.getConnection();
        Connection secondConnection = dbSingleton.getConnection();

        assertNotSame(firstConnection, secondConnection, "Each call to getConnection() should return a new Connection instance");
        dbSingleton.close(firstConnection, null);
        dbSingleton.close(secondConnection, null);
    }

    @Test
    void testDoubleCheckedLocking() {
        System.out.println("Running testDoubleCheckedLocking");

        dbSingleton = DbConnectionSingleton.getInstance();

        // Try creating multiple instances to ensure the double-checked locking is working
        DbConnectionSingleton anotherInstance = DbConnectionSingleton.getInstance();
        assertSame(dbSingleton, anotherInstance, "Double-checked locking failed, instances should be the same");
    }




    @Test
    void testMemoryLeak() {
        System.out.println("Running testMemoryLeak");

        dbSingleton = DbConnectionSingleton.getInstance();
        for (int i = 0; i < 1000; i++) {
            dbSingleton = DbConnectionSingleton.getInstance();
        }
        // This test would typically be complemented by a memory profiler to verify there is no leak
        assertNotNull(dbSingleton, "Expected singleton instance to be maintained without memory issues");
    }


    @Test
    void testSingletonUnderHeavyLoad() throws InterruptedException, ExecutionException {
        System.out.println("Running testSingletonUnderHeavyLoad");

        Callable<DbConnectionSingleton> task = DbConnectionSingleton::getInstance;
        ExecutorService executor = Executors.newFixedThreadPool(20);

        // Submit 100 tasks to simulate high load
        List<Future<DbConnectionSingleton>> futures = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            futures.add(executor.submit(task));
        }

        DbConnectionSingleton firstInstance = futures.get(0).get();

        for (Future<DbConnectionSingleton> future : futures) {
            assertSame(firstInstance, future.get(), "Expected only one instance under heavy load");
        }

        executor.shutdown();
    }


    @Test
    void testCloseAlreadyClosedConnection() throws SQLException {
        System.out.println("Running testCloseAlreadyClosedConnection");

        dbSingleton = DbConnectionSingleton.getInstance();
        Connection connection = dbSingleton.getConnection();
        dbSingleton.close(connection, null);

        // Attempt to close the already closed connection
        assertDoesNotThrow(() -> dbSingleton.close(connection, null), "Expected no exception when closing an already closed connection");
    }

    @AfterAll
    void tearDownAll() {
        System.out.println("Running @AfterAll - Executed once after all test methods in this class");
    }
}
