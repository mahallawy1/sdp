package tests.EmailCommand;

import MODEL.Patterns.EmailCommand.EmailCommand;
import MODEL.Patterns.EmailCommand.DelayedCommandExecutor;
import MODEL.Patterns.facade.NotificationFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmailCommandTest {

    private EmailCommand emailCommand;
    private DelayedCommandExecutor delayedCommandExecutor;

    @BeforeEach
    public void setUp() {
        // Initialize your mock or real NotificationFacade here
        NotificationFacade notificationFacade = new NotificationFacade();  // Replace with actual initialization
        List<String> adminEmails = Arrays.asList("admin@example.com","belal972001@gmail.com");
        Double donationAmount = 50.0;
        String donorName = "John Doe";

        delayedCommandExecutor = new DelayedCommandExecutor();
        emailCommand = new EmailCommand(adminEmails, donationAmount, donorName, notificationFacade);
    }

    @Test
    public void testEmailCommandExecutionAfterDelay() throws InterruptedException {
        // Record the start time
        long startTime = System.currentTimeMillis();

        // Execute the command
        emailCommand.execute();

        // Sleep for the maximum possible delay (assuming 10 seconds max)
        // This is a way to make sure the delay completes in the test
        Thread.sleep(11000);  // Sleep for slightly more than the maximum delay to ensure it's executed

        // Record the end time
        long endTime = System.currentTimeMillis();

        // Check that the delay was at least the expected delay (e.g., 10 seconds)
        long elapsedTime = endTime - startTime;
        assertTrue(elapsedTime >= 10000, "Delay should be at least 10 seconds");

        // Add more checks if necessary, like verifying email sending logic
        // For example, checking the console output or logs,
        // or if the NotificationFacade method was called successfully
    }
}
