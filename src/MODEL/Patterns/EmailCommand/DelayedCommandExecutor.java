package MODEL.Patterns.EmailCommand;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DelayedCommandExecutor {
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public void executeAfterDelay(Command command, long delayInSeconds) {
        scheduler.schedule(() -> {
            try {
                command.execute();
            } catch (Exception e) {
                System.out.println("Error executing command: " + e.getMessage());
            }
        }, delayInSeconds, TimeUnit.SECONDS);
    }

    public void shutdown() {
        scheduler.shutdown();
    }
}
