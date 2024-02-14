package calculator;

public class MortgageCalculator implements Calculator {
  @Override
  public double calculateMonthlyPayment(double principal, int term, double rate) {
    try {
      rate = rate / 100;
      return (principal * (rate / N))
              / (1 - Math.pow((1 + rate / N), -(N * term)));
    } catch (ArithmeticException e) {
      throw new ArithmeticException();
    }
  }

  @Override
  public double calculateYearlyPayment(double principal, int term, double rate) {
    return calculateMonthlyPayment(principal, term, rate) * N;
  }
}
