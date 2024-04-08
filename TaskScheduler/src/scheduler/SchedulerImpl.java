package scheduler;

import CustomQueue.CustomQueue;
import dto.Task;

import java.time.LocalDateTime;
import java.util.List;

public class SchedulerImpl implements Scheduler {
  private final CustomQueue<Task> customQueue = CustomQueue.getInstance();

  @Override
  public void scheduleTask(Task task) {
    customQueue.offer(new Task(task.getJob(), task.getTime()));
  }

  @Override
  public void showTasks() {
    System.out.println(customQueue.isEmpty() ? "No current task in the queue" : dereferenceTasks());
  }

  private List<String> dereferenceTasks() {
    return customQueue.stream()
        .map(task -> task.getJob() + " scheduled for " + task.getTime())
        .toList();
  }
}
