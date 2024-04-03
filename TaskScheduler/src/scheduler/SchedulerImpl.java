package scheduler;

import CustomQueue.CustomQueue;
import dto.Task;

import java.time.LocalDateTime;

public class SchedulerImpl implements Scheduler {
  private final CustomQueue<Task> customQueue = CustomQueue.getInstance();

  @Override
  public void scheduleTask(Task task) {
    LocalDateTime localDateTime = LocalDateTime.of(2024, 4, 3, 14, 0);
    customQueue.offer(new Task("Write my essay", localDateTime));
  }

  @Override
  public void showTasks() {
    System.out.println(customQueue);
  }
}
