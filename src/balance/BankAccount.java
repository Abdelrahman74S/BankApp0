package balance;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private double balance;
    private List<String> transactions; // List to hold transaction history
    private double outstandingLoanAmount; // Track outstanding loan amount
    private double totalDue; // Track total amount due for loans

    public BankAccount() {
        this.balance = 0.0; // Initial balance
        this.transactions = new ArrayList<>(); // Initialize the transaction list
        this.outstandingLoanAmount = 0.0; // No outstanding loan initially
        this.totalDue = 0.0; // No total due initially
    }

    // Deposit method
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactions.add("Deposited: $" + amount + " | New Balance: $" + balance); // Record transaction with new balance
        } else {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
    }

    // Withdrawal method
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactions.add("Withdrew: $" + amount + " | New Balance: $" + balance); // Record transaction with new balance
        } else {
            throw new IllegalArgumentException("Invalid withdrawal amount.");
        }
    }

    // Transfer method
    public void transfer(BankAccount recipient, double amount, String recipientName) {
        if (amount > 0 && amount <= balance) {
            this.withdraw(amount); // Withdraw from current account
            recipient.deposit(amount); // Deposit into recipient's account

            // Log transactions for both accounts
            this.transactions.add("Transferred: $" + amount + " to " + recipientName + " | New Balance: $" + this.balance);
            recipient.transactions.add("Received: $" + amount + " from account | New Balance: $" + recipient.getBalance());
        } else {
            throw new IllegalArgumentException("Invalid transfer amount. Ensure it does not exceed your current balance.");
        }
    }

    // Take a loan method
    public void takeLoan(double amount) {
        if (amount > 0) {
            double interest = amount * 0.05; // Assume 5% interest
            totalDue += (amount + interest); // Update total due for the loan
            outstandingLoanAmount += (amount + interest); // Update outstanding loan amount
            transactions.add("Loan Taken: $" + amount + " | Interest: $" + interest + " | Total Due: $" + totalDue);
            balance += amount; // Add loan amount to balance
        } else {
            throw new IllegalArgumentException("Loan amount must be positive.");
        }
    }

    // Repay loan method
    public void repayLoan(double amount) {
        if (amount > 0 && amount <= balance) {
            if (outstandingLoanAmount > 0) {
                // Repay only if there is an outstanding loan
                double repaymentAmount = Math.min(amount, outstandingLoanAmount);
                balance -= repaymentAmount; // Deduct repayment from balance
                outstandingLoanAmount -= repaymentAmount; // Reduce outstanding loan amount
                transactions.add("Loan Repayment: $" + repaymentAmount + " | New Balance: $" + balance);
            } else {
                // If no loan amount is outstanding
                throw new IllegalArgumentException("No outstanding loan to repay.");
            }
        } else {
            throw new IllegalArgumentException("Invalid repayment amount. Ensure it does not exceed your current balance.");
        }
    }

    // Getter methods for balance and transaction history
    public double getBalance() {
        return balance;
    }

    public List<String> getTransactions() {
        return transactions;
    }

    public double getOutstandingLoanAmount() {
        return outstandingLoanAmount;
    }

    public double getTotalDue() {
        return totalDue;
    }
}
