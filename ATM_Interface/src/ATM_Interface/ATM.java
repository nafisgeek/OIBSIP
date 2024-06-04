package ATM_Interface;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ATM {

    private double balance;
    private Map<String, String> history = new HashMap<>();


    public ATM() {
        this.balance = 0.0;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void checkBalance() {
        System.out.println("Available Balance: " + getBalance());
    }

    public void withdraw(double amount) {
        if (amount > getBalance()) {
            System.out.println("Insufficient Balance.");
        } else {
            balance -= amount;
            String timestamp = getCurrentTimestamp();
            history.put(timestamp, "Withdrawn: " + amount);
            System.out.println(amount + " Withdrawn Successfully.");
            checkBalance();
        }
    }

    public void deposit(double amount) {
        balance += amount;
        String timestamp = getCurrentTimestamp();
        history.put(timestamp, "Deposited: " + amount);
        System.out.println(amount + " Deposited Successfully.");
        checkBalance();
    }

    public void transfer(double amount) {
        if (amount > getBalance()) {
            System.out.println("Insufficient Balance.");
        } else {
            balance -= amount;
            String timestamp = getCurrentTimestamp();
            history.put(timestamp, "Transferred: " + amount);
            System.out.println("Transfer Successful!");
            checkBalance();
        }
    }

    public void showHistory() {
        for (Map.Entry<String, String> entry : history.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }

    private String getCurrentTimestamp() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public static void main(String[] args) {

        final int atmNumber = 54321;
        final int atmPin = 2024;

        try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Welcome to the ATM Machine");
			System.out.print("Enter ATM number: ");
			int enteredAtmNumber = sc.nextInt();
			System.out.print("Enter PIN: ");
			int enteredPin = sc.nextInt();

			ATM atm = new ATM();

			if (enteredAtmNumber == atmNumber && enteredPin == atmPin) {
			    while (true) {
			        System.out.println("\n1. Check Balance\n2. Withdraw\n3. Deposit\n4. Transfer\n5. Transaction History\n6. Exit");
			        System.out.print("Enter your choice: ");
			        int choice = sc.nextInt();

			        switch (choice) {
			            case 1:
			                atm.checkBalance();
			                break;
			            case 2:
			                System.out.print("Enter the amount to withdraw: ");
			                double withdrawAmount = sc.nextDouble();
			                atm.withdraw(withdrawAmount);
			                break;
			            case 3:
			                System.out.print("Enter the amount to deposit: ");
			                double depositAmount = sc.nextDouble();
			                atm.deposit(depositAmount);
			                break;
			            case 4:
			                System.out.print("Enter recipient's account number: ");
			                sc.next(); 
			                System.out.print("Enter the amount to transfer: ");
			                double transferAmount = sc.nextDouble();
			                atm.transfer(transferAmount);
			                break;
			            case 5:
			                atm.showHistory();
			                break;
			            case 6:
			                System.out.println("Collect Your ATM card\nThank you for using ATM Machine");
			                System.exit(0);
			                break;
			            default:
			                System.out.println("Please enter a correct choice.");
			        }
			    }
			} else {
			    System.out.println("Incorrect ATM Number or PIN");
			}
		}
    }
}
