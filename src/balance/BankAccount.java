package balance;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {
	private double balance;
	private List<String> transactions; 
	private double outstandingLoanAmount; 
	private double totalDue; 

	public BankAccount() {
    	this.balance = 0.0; 
    	this.transactions = new ArrayList<>(); 
    	this.outstandingLoanAmount = 0.0; 
    	this.totalDue = 0.0; 
   }

	public void deposit(double amount) {
    	if (amount > 0) {
        	balance += amount;
        	transactions.add("Deposited: $" + amount + " | New Balance: $" + balance); 
    	} else {
        	throw new IllegalArgumentException("Deposit amount must be positive.");
    	}
   }

	public void withdraw(double amount) {
    	if (amount > 0 && amount <= balance) {
        	balance -= amount;
        	transactions.add("Withdrew: $" + amount + " | New Balance: $" + balance); 
    	} else {
        	throw new IllegalArgumentException("Invalid withdrawal amount.");
    	}
   }

	public void transfer(BankAccount recipient, double amount, String recipientName) {
    	if (amount > 0 && amount <= balance) {
        	this.withdraw(amount); 
        	recipient.deposit(amount); 

        	this.transactions.add("Transferred: $" + amount + " to " + recipientName + " | New Balance: $" + this.balance);
        	recipient.transactions.add("Received: $" + amount + " from account | New Balance: $" + recipient.getBalance());
    	} else {
        	throw new IllegalArgumentException("Invalid transfer amount. Ensure it does not exceed your current balance.");
    	}
   }

	public void takeLoan(double amount) {
    	if (amount > 0) {
        	double interest = amount * 0.05; 
        	totalDue += (amount + interest); 
        	outstandingLoanAmount += (amount + interest); 
        	transactions.add("Loan Taken: $" + amount + " | Interest: $" + interest + " | Total Due: $" + totalDue);
        	balance += amount; 
    	} else {
        	throw new IllegalArgumentException("Loan amount must be positive.");
    	}
   }

	public void repayLoan(double amount) {
    	if (amount > 0 && amount <= balance) {
        	if (outstandingLoanAmount > 0) {
            	double repaymentAmount = Math.min(amount, outstandingLoanAmount);
            	balance -= repaymentAmount; 
            	outstandingLoanAmount -= repaymentAmount; 
            	transactions.add("Loan Repayment: $" + repaymentAmount + " | New Balance: $" + balance);
        	} else {  
            	throw new IllegalArgumentException("No outstanding loan to repay.");
        	}
    	} else {  
        	throw new IllegalArgumentException("Invalid repayment amount. Ensure it does not exceed your current balance.");
    	}
   }

	public double getBalance() { return balance; }
	public List<String> getTransactions() { return transactions; }
	public double getOutstandingLoanAmount() { return outstandingLoanAmount; }
	public double getTotalDue() { return totalDue; }
}
