package UserInterface;

import calculator.MortgageCalculator;

import java.util.Scanner;

public class UserInterface implements CreateUserInterface {
  private final MortgageCalculator mortgageCalculator;

  public UserInterface(MortgageCalculator mortgageCalculator) {
    this.mortgageCalculator = mortgageCalculator;
  }

  private void showHowToUseApp() {
    System.out.println(" ");
    System.out.println("          MORTGAGE CALCULATOR");
    System.out.println(" ");
    System.out.println("NB: This mortgage calculator only works for the Ghana Cedi currency");
    System.out.println("Please choose how you would like to calculate your mortgage.");
    System.out.println("1. Calculate monthly payment on mortgage.");
    System.out.println("2. Calculate yearly payment on mortgage.");
    System.out.println("3 to exit");
    System.out.println(" ");
    System.out.print("Enter option eg 1 or 2: ");
  }

  private void promptUserInput() {
    System.out.println(" ");
    System.out.println("In order to do the selected calculations we will need the following:");

    System.out.println("* Principal: This the amount of loan for the mortgage");
    System.out.println("* Term: This refers to the period over which the mortgage will be paid");
    System.out.println("* Interest Rate");
  }

  private double performCalculations(Scanner scanner, int choice) {
    System.out.print("principal: ");
    double principal = scanner.nextDouble();

    System.out.print("term: ");
    int term = scanner.nextInt();

    System.out.print("rate: ");
    double rate = scanner.nextDouble();

    return switch (choice) {
      case 1 -> mortgageCalculator.calculateMonthlyPayment(principal, term, rate);
      case 2 -> mortgageCalculator.calculateYearlyPayment(principal, term, rate);
      default -> 0;
    };
  }

  @Override
  public void startMortgageCalculator() {
    showHowToUseApp();
    Scanner scanner = new Scanner(System.in);

    while (true) {
      int choice = scanner.nextInt();

      if (choice == 1) {
        promptUserInput();
        System.out.println(
            "Here is the amount to pay monthly on your mortgage: GHC "
                + performCalculations(scanner, choice));
      } else if (choice == 2) {
        promptUserInput();
        System.out.println(
            "Here is the amount to pay yearly on your mortgage: GHC "
                + performCalculations(scanner, choice));
      } else if (choice == 3) {
        System.out.println("Closing MORTGAGE CALCULATOR....");
        break;
      } else {
        System.out.println(" ");
        System.out.println("Incorrect option selected, please try again!");
        System.out.println(" ");
        System.out.println(" ");
        showHowToUseApp();
      }
    }
  }
}
