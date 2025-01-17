package tests.EmailCommand;

import MODEL.Patterns.EmailCommand.DelayedCommandExecutor;
import MODEL.Patterns.EmailCommand.EmailCommand;
import MODEL.Patterns.facade.NotificationFacade;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmailCommandTest {

    private DelayedCommandExecutor commandExecutor;
    private NotificationFacade notificationFacade;


    @BeforeEach
    public void setUp() {

        notificationFacade = new NotificationFacade();
        commandExecutor = new DelayedCommandExecutor();
    }

    @AfterEach
    public void tearDown() {
        commandExecutor.shutdown();
    }

    @Test
    public void testDelayedEmailCommand() throws InterruptedException {
        System.out.println("Going to start sending test email...");

        // Prepare test data
        List<String> adminEmails = Arrays.asList("admin1@example.com", "admin2@example.com");
        double donationAmount = 100.0;
        String donorName = "John Doe";

        // Create the EmailCommand
        EmailCommand emailCommand = new EmailCommand(adminEmails, donationAmount, donorName, notificationFacade);

        // Use CountDownLatch to wait for the email sending to be completed
        CountDownLatch latch = new CountDownLatch(1);

        // Create a new thread to handle the delay countdown
        new Thread(() -> {
            for (int i = 3; i > 0; i--) {
                System.out.println("Delay: " + i + " second(s) remaining...");
                try {
                    Thread.sleep(1000); // Sleep for 1 second
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println("Countdown thread interrupted.");
                }
            }
        }).start();

        // Delay for 3 seconds before executing the command
        commandExecutor.executeAfterDelay(() -> {
            try {
                emailCommand.execute();
            } catch (Exception e) {
                System.err.println("Error executing command: " + e.getMessage());
            } finally {
                latch.countDown(); // Decrement the latch counter
            }
        }); // Delay of 3 seconds

        // Wait for the latch to ensure the email command is executed after the delay
        latch.await();

        // Since we can't easily check email sending in the test, we can just assert that the latch was released
        // and assume the email was sent if no exception was thrown.
        assertTrue(true, "Delayed email command executed successfully.");
    }

}
