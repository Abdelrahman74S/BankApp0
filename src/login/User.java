package login;

import balance.BankAccount;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
    private String username;
    private String password;
    private int accountId;
    private BankAccount bankAccount;

    public User(String username, String password, int accountId) {
        this.username = username;
        this.password = hashPassword(password);
        this.accountId = accountId;
        this.bankAccount = new BankAccount();
    }

    public String getUsername() {
    	return username;
    }

    public String getPassword() {
    	return password;
    }

    public int getAccountId() {
    	return accountId;
    }

    public BankAccount getBankAccount() {
    	return bankAccount;
    }

    public String hashPassword(String password) {
    	try {
        	MessageDigest md = MessageDigest.getInstance("SHA-256");
        	byte[] hashedBytes = md.digest(password.getBytes());
        	StringBuilder sb = new StringBuilder();
        	for (byte b : hashedBytes) {
            	sb.append(String.format("%02x", b));
        	}
        	return sb.toString();
    	} catch (NoSuchAlgorithmException e) {
        	throw new RuntimeException("Hashing algorithm not found", e);
    	}
   }
}
