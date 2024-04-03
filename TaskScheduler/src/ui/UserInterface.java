package ui;

import dto.Task;
import scheduler.Scheduler;

import java.time.LocalDateTime;
import java.util.Scanner;

public class UserInterface implements UI {
  private final Scheduler scheduler;

  public UserInterface(Scheduler scheduler) {
    this.scheduler = scheduler;
  }

  private void showTutorial() {
    System.out.println(" ");
    System.out.println("          TASK SCHEDULER");
    System.out.println(" ");
    System.out.println("Please select your option");
    System.out.println("1. Schedule a task");
    System.out.println("2. Show scheduled tasks");
    System.out.println("3 to exit");
    System.out.println(" ");
    System.out.print("Enter option eg 1 or 2: ");
  }

  private void schedule(Scanner scanner) {
    System.out.println("Enter the following information to schedule your task");

    System.out.print("Task: ");
    String task = scanner.next();

    System.out.print("Year: ");
    int year = scanner.nextInt();

    System.out.print("Month: ");
    int month = scanner.nextInt();

    System.out.print("Day: ");
    int day = scanner.nextInt();

    System.out.print("Hour: ");
    int hours = scanner.nextInt();

    System.out.print("Minute: ");
    int minutes = scanner.nextInt();

    LocalDateTime localDateTime = LocalDateTime.of(year, month, day, hours, minutes);
    scheduler.scheduleTask(new Task(task, localDateTime));

    System.out.println("Your task has been scheduled");
  }

  @Override
  public void startTaskScheduler() {
    showTutorial();
    Scanner scanner = new Scanner(System.in);

    while (true) {
      int choice = scanner.nextInt();

      if (choice == 1) schedule(scanner);

      if (choice == 2) scheduler.showTasks();

      if (choice == 3) {
        System.out.println("Closing TASK SCHEDULER....");
        break;
      }

      System.out.println(" ");
      System.out.println("Incorrect option selected, please try again!");
      System.out.println(" ");
      System.out.println(" ");
      showTutorial();
    }
  }
}
