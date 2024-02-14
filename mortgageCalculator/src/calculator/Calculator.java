package calculator;

public interface Calculator {
    /**
     * Principal or loan (P)
     * Term (t)
     * Rate (r)
     * number of months (n)
     * Monthly Payment (MP)
     * MP = [P(r/t)] / [1-(1+r/n)^-nt]
     */
    int N = 12;
    double calculateMonthlyPayment(double principal, int term, double rate);

    double calculateYearlyPayment(double principal, int term, double rate);
}
