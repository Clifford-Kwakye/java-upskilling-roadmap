import job.JobImpl;
import scheduler.SchedulerImpl;
import ui.UserInterface;

public class Main {
  public static void main(String[] args) {
    JobImpl job = new JobImpl();
    UserInterface userInterface = new UserInterface(new SchedulerImpl());

    job.doTask();
    userInterface.startTaskScheduler();
  }
}
