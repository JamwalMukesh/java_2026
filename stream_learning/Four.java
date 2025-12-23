import java.util.List;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collector;
class Transaction{
    double amount;
    Transaction(double amount) { this.amount = amount; }
    public double getAmount() { return this.amount; }
}
class TransactionStats{
    double sum = 0;
    double max = Double.MIN_VALUE;
    public void accept(Transaction t){
        sum += t.getAmount();
        max = Math.max(max,t.getAmount());
    }

    public TransactionStats combine(TransactionStats other){
        this.sum += other.sum;
        this.max = Math.max(other.max,this.max);
        return this;
    }
}
class Four{
	public static void main(String[] args){
	   List<Transaction> transactions = Arrays.asList(
            new Transaction(100.0),
            new Transaction(250.0),
            new Transaction(50.0)
       );	

       TransactionStats stats = transactions.stream()
                                    .collect(Collector.of(
                                        TransactionStats::new,
                                        TransactionStats::accept,
                                        TransactionStats::combine
                                    ));


       System.out.println("Sum: " + stats.sum + " Max: " + stats.max);
	}
}