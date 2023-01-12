import java.io.Serializable;
import java.time.LocalDate;

public class Transaction implements Comparable<Transaction>, Serializable {

    private static int next = 1;
    int trsNo;
   Account account;
   LocalDate date;
   char operation;
   double amount;

    public Transaction(Account account, LocalDate date, char operation, double amount) {
        this.account = account;
        this.date = date;
        this.operation = operation;
        this.amount = amount;
        trsNo = next++;
    }


    public int getTrsNo() {
        return trsNo;
    }

    public Account getAccount() {
        return account;
    }

    public LocalDate getDate() {
        return date;
    }

    public char getOperation() {
        return operation;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public int compareTo(Transaction o) {
        return this.trsNo - o.trsNo;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "trsNo=" + trsNo +
                ", account=" + account +
                ", date=" + date +
                ", operation=" + operation +
                ", amount=" + amount +
                '}';
    }
}
