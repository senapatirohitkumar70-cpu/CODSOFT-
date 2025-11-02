import java.util.Scanner;


class BankAccount {
    double balance;

    
    BankAccount(double balance) {
        this.balance = balance;
    }

    
    void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Amount Deposited: ₹" + amount);
        } else {
            System.out.println("Invalid amount! Deposit failed.");
        }
    }

    
    void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient Balance!");
        } else if (amount <= 0) {
            System.out.println("Invalid amount! Withdrawal failed.");
        } else {
            balance -= amount;
            System.out.println("Amount Withdrawn: ₹" + amount);
        }
    }

   
    void checkBalance() {
        System.out.println("Your Current Balance is: ₹" + balance);
    }
}



class ATM {
    BankAccount account;   
    Scanner sc = new Scanner(System.in);

    
    ATM(BankAccount account) {
        this.account = account;
    }

    
    void menu() {
        int choice;
        do {
            System.out.println("\n=== ATM MENU ===");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

           
            switch (choice) {
                case 1:
                    account.checkBalance();
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = sc.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = sc.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }

        } while (choice != 4);
    }
}



public class AtmMachine {
    public static void main(String[] args) {

       
        BankAccount myAccount = new BankAccount(5000.0);

        
        ATM atm = new ATM(myAccount);

       
        atm.menu();
    }
}
