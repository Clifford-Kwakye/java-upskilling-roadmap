package job;

import CustomQueue.CustomQueue;
import dto.Task;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class JobImpl implements Job {
  private final CustomQueue<Task> customQueue = CustomQueue.getInstance();

  @Override
  public void doTask() {
    int numThreads = 2;
    long delay = 2;
    long initialDelay = 1;

    try (ScheduledExecutorService executor = Executors.newScheduledThreadPool(numThreads)) {
      Task task = customQueue.poll();

      if (task == null) return;

      Runnable processTask =
          () -> {
            if (task.getTime().isBefore(LocalDateTime.now())
                || task.getTime().isEqual(LocalDateTime.now())) {
              System.out.println(task.getJob());
            }
          };

      executor.scheduleAtFixedRate(processTask, initialDelay, delay, TimeUnit.SECONDS);
    }
  }
}
