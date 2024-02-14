import calculator.MortgageCalculator;
import UserInterface.UserInterface;

public class Main {
  public static void main(String[] args) {
    UserInterface userInterface = new UserInterface(new MortgageCalculator());
    userInterface.startMortgageCalculator();
  }
}
