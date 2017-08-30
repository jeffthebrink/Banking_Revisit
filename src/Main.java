import java.util.HashMap;
import java.util.Scanner;

public class Main {

    static HashMap<String, Double> accountList = new HashMap();
    static boolean bankingActive = true;


    private static void checkBalance(String userName) {
        if (accountList.get(userName) != null) {
            System.out.println("Your current balance is: $" + accountList.get(userName));
        } else {
            System.out.println("You must be logged in to view a balance!");
        }
    }

    private static void withdraw(String userName) {
        if (accountList.containsKey(userName)) {
            Scanner scannerDouble = new Scanner(System.in);
            System.out.println("How much would you like to withdraw?");
            Double withdrawAmt = scannerDouble.nextDouble();
            if (withdrawAmt > accountList.get(userName)) {
                System.out.println("You cannot withdraw more than your balance!");
            } else {
                Double currentBalance = accountList.get(userName);
                Double newBalance = currentBalance - withdrawAmt;
                accountList.put(userName, newBalance);
                System.out.println("Your new balance is: $" + accountList.get(userName));
            }
        } else {
            System.out.println("You must be logged in to withdraw money!");
        }
    }

    private static void closeAccount(String userName) {
        accountList.remove(userName);
        System.out.println("Account removed.");
    }

    private static void depositFunds(String userName) {
        Scanner scannerDouble = new Scanner(System.in);
        System.out.println("How much would you like to deposit?");
        Double depositAmt = scannerDouble.nextDouble();
        Double currentBalance = accountList.get(userName);
        Double newBalance = depositAmt + currentBalance;
        accountList.put(userName, newBalance);
        System.out.println("Your new balance is: $" + accountList.get(userName));
    }

    private static void chooseOption(String userName) {
        System.out.println();
        System.out.println("Please choose an option below: ");
        System.out.println("1. Check balance");
        System.out.println("2. Withdraw money");
        System.out.println("3. Close account");
        System.out.println("4. Deposit funds");
        System.out.println("5. Exit system");

        Scanner scannerInt = new Scanner(System.in);
        Integer option = scannerInt.nextInt();

        switch (option) {
            case 1:
                checkBalance(userName);
                break;
            case 2:
                withdraw(userName);
                break;
            case 3:
                closeAccount(userName);
                break;
            case 4:
                depositFunds(userName);
                break;
            case 5:
                System.out.println("Exiting system...");
                bankingActive = false;
                break;
            default:
                System.out.println("You must input a valid option!");
                break;
        }

    }

    private static void createNewAccount(String userName) {
        Scanner scannerDouble = new Scanner(System.in);
        System.out.println("Creating new account.");
        System.out.println("How much would you like to deposit into your new account?");
        Double depositAmt = scannerDouble.nextDouble();
        accountList.put(userName, depositAmt);
        System.out.println("Your new account balance is: $" + accountList.get(userName));
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            bankingActive = true;
            System.out.println("Hello and welcome to the Bank!");
            System.out.println();
            System.out.println("Please enter your name to begin banking.");

            String userName = scanner.nextLine();

            if (accountList.containsKey(userName)) {
                while (bankingActive) {
                    chooseOption(userName);
                }
            } else {
                System.out.println("No user name found. Would you like to create an account? y/n?");
                String createAccount = scanner.nextLine().toLowerCase();
                if (createAccount.equals("y")) {
                    createNewAccount(userName);
                    while (bankingActive) {
                        chooseOption(userName);
                    }
                } else {
                    System.out.println("You have selected to not create an account. Goodbye!");
                }
            }
        }
    }
}
