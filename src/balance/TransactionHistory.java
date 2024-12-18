package balance;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;
import login.Register;
import login.User;

public class TransactionHistory extends JFrame {

	private static final long serialVersionUID =1L;
	private JPanel contentPane;

	public TransactionHistory(BankAccount bankAccount) {

	  setTitle("Transaction History");
	  setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	  setBounds(100 ,100 ,450 ,300);

	  contentPane=new JPanel();
	  contentPane.setBorder(new EmptyBorder(5 ,5 ,5 ,5));
	  setContentPane(contentPane);
	  contentPane.setLayout(new BorderLayout());

	  JTextArea textArea=new JTextArea();
	  textArea.setEditable(false);
	  JScrollPane scrollpane=new JScrollPane(textArea); // Corrected variable name
	  contentPane.add(scrollpane ,BorderLayout.CENTER);

	  List<String> transactions= bankAccount.getTransactions();

	  if(transactions.isEmpty()) {

	      textArea.setText("No transactions available.");
	  } else {

	      for(String transaction : transactions) {

	          textArea.append(transaction+"\n"); // Append each transaction
	      }
	      textArea.append("\n-------------------------------------------------------------------");
	      textArea.append("\nCurrent Balance: $"+bankAccount.getBalance());

	      User user= Register.users.values().stream()
	              .filter(u -> u.getBankAccount().equals(bankAccount))
	              .findFirst()
	              .orElse(null);

	      if(user != null) {

	          textArea.append("\nUser ID: "+user.getAccountId());
	          textArea.append("\nAccount Holder: "+user.getUsername());
	      } else {

	          textArea.append("\nUser ID: Not Found");
	          textArea.append("\nAccount Holder: Not Found");
	      }

	      if(bankAccount.getOutstandingLoanAmount()>0){

	          textArea.append("\nOutstanding Loan Amount: $"+bankAccount.getOutstandingLoanAmount());
	      } else {

	          textArea.append("\nNo outstanding loan.");
	      }
	  }

	  setVisible(true); // Make the window visible
   }
}
