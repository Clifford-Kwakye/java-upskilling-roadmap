package job;

import CustomQueue.CustomQueue;
import dto.Task;

public class JobImpl implements Job {
  private final CustomQueue<Task> customQueue = CustomQueue.getInstance();

  @Override
  public void doTask() {
    System.out.println(customQueue.poll());
  }
}
