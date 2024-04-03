import scheduler.SchedulerImpl;
import ui.UserInterface;

public class Main {
  public static void main(String[] args) {
    UserInterface userInterface = new UserInterface(new SchedulerImpl());
    userInterface.startTaskScheduler();
  }
}
