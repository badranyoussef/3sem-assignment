package assignment7;
import utility.Printer;

import java.util.*;
import java.util.stream.Collectors;

class Transaction {
    private int id;
    private double amount;
    private String currency;

    public Transaction(int id, double amount, String currency) {
        this.id = id;
        this.amount = amount;
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public int getId() {
        return id;
    }

    public String getCurrency() {
        return currency;
    }
}

class CollectorsExercise {
    public static void main(String[] args) {
        List<Transaction> transactions = List.of(
                new Transaction(1, 100.0, "USD"),
                new Transaction(2, 150.0, "EUR"),
                new Transaction(3, 200.0, "USD"),
                new Transaction(4, 75.0, "GBP"),
                new Transaction(5, 120.0, "EUR"),
                new Transaction(6, 300.0, "GBP")
        );

        // Calculate the total sum of all transaction amounts
        double totalSum = transactions.stream()
                .mapToDouble(Transaction::getAmount)
                .sum();
        System.out.println("Total sum of all transactions: " + totalSum);
        Printer.aBreak();

        // Group transactions by currency and calculate sum for each currency
        Map<String, Double> currencies = transactions.stream().collect(Collectors.groupingBy(Transaction::getCurrency, Collectors.averagingDouble(Transaction::getAmount)));

        currencies.forEach((currency , amount) -> {
            Printer.result(currency + " amount: " + amount);

        });
        Printer.aBreak();

        // Find the highest transaction amount
        Optional<Transaction> highestTransaction = transactions.stream().collect(Collectors.maxBy(Comparator.comparing(transaction -> transaction.getAmount())));

        Printer.result("ID: " + highestTransaction.get().getId() +" Currency: "+ highestTransaction.get().getCurrency() +" amount: "+highestTransaction.get().getAmount());


        // Find the average transaction amount
        OptionalDouble avgTransaction = transactions.stream().mapToDouble(Transaction::getAmount).average();
        System.out.println(avgTransaction);

        double avg = transactions.stream().collect(Collectors.averagingDouble(Transaction::getAmount));

        System.out.println(avg);

    }
}