// BankOperationsPage.java
package balance;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import login.Register;
import login.User;
import java.util.Optional;

public class BankOperationsPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel operationsPane;
    private BankAccount bankAccount;

    public BankOperationsPage(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
        setTitle("Bank Operations");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 811, 497);

        operationsPane = new JPanel();
        operationsPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(operationsPane);
        operationsPane.setLayout(null);

        // Buttons setup
        setupButtons();

        setVisible(true);
    }

    private void setupButtons() {
        JButton depositButton = new JButton("Deposit");
        depositButton.setBounds(272, 11, 232, 66);
        depositButton.addActionListener(e -> performDeposit());
        operationsPane.add(depositButton);

        JButton withdrawalButton = new JButton("Withdrawal");
        withdrawalButton.setBounds(272, 88, 232, 57);
        withdrawalButton.addActionListener(e -> performWithdrawal());
        operationsPane.add(withdrawalButton);

        JButton loanButton = new JButton("Request Loan");
        loanButton.setBounds(272, 156, 232, 66);
        loanButton.addActionListener(e -> requestLoan());
        operationsPane.add(loanButton);

        JButton repayLoanButton = new JButton("Repay Loan");
        repayLoanButton.setBounds(272, 233, 232, 66);
        repayLoanButton.addActionListener(e -> performLoanRepayment());
        operationsPane.add(repayLoanButton);

        JButton transferButton = new JButton("Transfer Money");
        transferButton.setBounds(272, 310, 232, 57);
        transferButton.addActionListener(e -> performTransfer());
        operationsPane.add(transferButton);

        JButton transactionHistoryButton = new JButton("Transaction History");
        transactionHistoryButton.setBounds(272, 375, 232, 57);
        transactionHistoryButton.addActionListener(e -> viewTransactionHistory());
        operationsPane.add(transactionHistoryButton);
    }

    private void performDeposit() {
        String amountStr = JOptionPane.showInputDialog(this, "Enter amount to deposit:");
        if (amountStr != null && !amountStr.isEmpty()) {
            try {
                double amount = Double.parseDouble(amountStr);
                bankAccount.deposit(amount);
                JOptionPane.showMessageDialog(this, "Deposited: $" + amount + "\nNew Balance: $" + bankAccount.getBalance());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }
    }

    private void performWithdrawal() {
        String amountStr = JOptionPane.showInputDialog(this, "Enter amount to withdraw:");
        if (amountStr != null && !amountStr.isEmpty()) {
            try {
                double amount = Double.parseDouble(amountStr);
                bankAccount.withdraw(amount);
                JOptionPane.showMessageDialog(this, "Withdrew: $" + amount + "\nNew Balance: $" + bankAccount.getBalance());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }
    }

    private void requestLoan() {
        String loanAmountStr = JOptionPane.showInputDialog(this, "Enter loan amount:");
        if (loanAmountStr != null && !loanAmountStr.isEmpty()) {
            try {
                double loanAmount = Double.parseDouble(loanAmountStr);
                bankAccount.takeLoan(loanAmount);
                JOptionPane.showMessageDialog(this, "Requested Loan: $" + loanAmount +
                        "\nTotal Due Including Interest: $" + bankAccount.getTotalDue() +
                        "\nNew Balance: $" + bankAccount.getBalance());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }
    }

    private void performLoanRepayment() {
        String repaymentAmountStr = JOptionPane.showInputDialog(this,
                "Enter repayment amount (Max due is $" + bankAccount.getOutstandingLoanAmount() + "):");
        if (repaymentAmountStr != null && !repaymentAmountStr.isEmpty()) {
            try {
                double repaymentAmount = Double.parseDouble(repaymentAmountStr);
                bankAccount.repayLoan(repaymentAmount);
                JOptionPane.showMessageDialog(this, "Repayment of: $" + repaymentAmount +
                        "\nNew Balance: $" + bankAccount.getBalance());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }
    }

    private void performTransfer() {
        String recipientIdStr = JOptionPane.showInputDialog(this, "Enter recipient's account ID:");
        if (recipientIdStr != null && !recipientIdStr.isEmpty()) {
            String transferAmountStr = JOptionPane.showInputDialog(this, "Enter transfer amount:");
            if (transferAmountStr != null && !transferAmountStr.isEmpty()) {
                try {
                    double transferAmount = Double.parseDouble(transferAmountStr);
                    Optional<User> recipientOpt = Register.users.values().stream()
                            .filter(user -> user.getAccountId() == Integer.parseInt(recipientIdStr))
                            .findFirst();

                    if (recipientOpt.isPresent()) {
                        User recipient = recipientOpt.get();
                        bankAccount.transfer(recipient.getBankAccount(), transferAmount, recipient.getUsername());
                        JOptionPane.showMessageDialog(this, "Transferred: $" + transferAmount +
                                " to " + recipient.getUsername() +
                                "\nNew Balance: $" + bankAccount.getBalance());
                    } else {
                        JOptionPane.showMessageDialog(this, "Recipient account not found.");
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
                }
            }
        }
    }

    private void viewTransactionHistory() {
        new TransactionHistory(bankAccount);
    }
}
