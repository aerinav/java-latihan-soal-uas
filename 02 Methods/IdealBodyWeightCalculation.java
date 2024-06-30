import java.util.InputMismatchException;
import java.util.Scanner;

public class IdealBodyWeightCalculation {
    private static String username;
    private static String password;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;

        while(!exit) {
            System.out.println("====Ideal Body Wieight Calculation====");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Choose: ");

            int menu = scanner.nextInt();
            scanner.nextLine();

            switch (menu) {
                case 1:
                    login();
                    break;
                case 2:
                    exit = true;
                    System.out.println("Exit...");
                    break;
                default:
                    System.out.println("Invalid menu");
                    break;
            }
        }  
    }

    static void login() {
        while(true) {
            System.out.print("Input username [5-30]: ");
            username = scanner.nextLine();

            if(username.length() >= 5 && username.length() <= 30) break;
            
            System.out.println("Username must be between 5 and 30 characters");
        }

        while(true) {
            System.out.print("Input password ['cancel' for cancel]: ");
            password = scanner.nextLine();

            if(password.equals("cancel")) return;

            if(password.equals(username)) {
                app(); 
                return; 
            }
            System.out.println("Incorrect password");
        }
    }

    static void app() {
        System.out.println("Welcome, " + username);

        int age;
        while(true) {
            try {
                System.out.print("Input your age [1-60]: ");
                age = scanner.nextInt();
                if(age >= 1 && age <= 10) {
                    float idealWeight = (age * 2) + 8;
                    System.out.println("Your ideal weight is: " + idealWeight + "kg");
                    return;
                }

                if(age > 10 && age <= 60) break;
            
                System.out.println("Age must be between 1 and 60");
            } catch(InputMismatchException e) {
                System.out.println("Please input an integer");
                scanner.next();
            }
        }  

        int height;
        while(true) {
            try {
                System.out.print("Input your height [150-300] cm: ");
                height = scanner.nextInt();
                if(height >= 150 && height <= 300) {
                    float idealWeight = (height - 100) * 0.9f;
                    System.out.println("Your ideal weight is: " + idealWeight + "kg");
                    return;
                }
                System.out.println("Height must be between 150 and 300");
            } catch (InputMismatchException e) {
                System.out.println("Please input an integer");
                scanner.next();
            }
        }
    }
}
