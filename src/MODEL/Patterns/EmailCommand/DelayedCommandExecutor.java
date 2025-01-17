package MODEL.Patterns.EmailCommand;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;



import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DelayedCommandExecutor {
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private int load = 4; // Load value to determine the delay

    // Method to set the load
    public int getLoad() {
        return this.load;
    }

    // Method to calculate delay based on load
    private long calculateDelay() {
        if (load > 0 && load <= 100) {
            return 3; // Delay of 3 seconds
        } else if (load > 100 && load <= 1000) {
            return 6; // Delay of 6 seconds
        } else if (load > 1000) {
            return 10; // Delay of 10 seconds
        }
        return 0; // No delay for load <= 0
    }

    public void executeAfterDelay(Command command) {
        long delayInSeconds = calculateDelay();
        //System.out.println("Calculated delay based on load (" + load + "): " + delayInSeconds + " seconds");

        scheduler.schedule(() -> {
            try {
                command.execute();
            } catch (Exception e) {
                //System.out.println("Error executing command: " + e.getMessage());
            }
        }, delayInSeconds, TimeUnit.SECONDS);
    }

    public void shutdown() {
        scheduler.shutdown();
    }
}
