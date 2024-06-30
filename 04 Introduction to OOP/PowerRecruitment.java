import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;

class Candidate {
    private String name;
    private String initial;
    private String gender;
    private int age;
    private String address;

    public Candidate(String name, String initial, String gender, int age, String address) {
        this.name = name;
        this.initial = initial;
        this.gender = gender;
        this.age = age;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getInitial() {
        return initial;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }
}

public class PowerRecruitment {
    private static ArrayList<Candidate> candidatesData = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;

        while(!exit) {
            System.out.println("====Power Recruitment====");
            System.out.println("1. Input New Candidate");
            System.out.println("2. View Candidate's Data");
            System.out.println("3. Remove Candidate");
            System.out.println("4. Exit");
            System.out.print("Choose: ");

            int menu = scanner.nextInt();
            scanner.nextLine();

            switch (menu) {
                case 1:
                    inputData();
                    break;
                case 2:
                    viewData();
                    break;
                case 3:
                    removeData();
                    break;
                case 4:
                    exit = true;
                    System.out.println("Exit...");
                    break;
                default:
                    System.out.println("Invalid menu");
                    break;
            }
        }  
    }

    static void inputData() {
        String name;
        while(true) {
            System.out.print("Input candidate's name [5-20 characters]: ");
            name = scanner.nextLine().trim();

            if (name.length() >= 5 && name.length() <= 20) break;
            
            System.out.println("Name must be between 5 and 20 characters");    
        }

        String gender;
        while (true) {
            System.out.print("Input candidate's gender [Male | Female]: ");
            gender = scanner.nextLine().trim().toLowerCase(); 

            if (gender.equals("male") || gender.equals("female")) break;
  
            System.out.println("Input male or female");  
        }

        int age;
        while(true) {
            System.out.print("Input age [17-30]: ");
            try {
                age = scanner.nextInt();   
                if(age >= 17 && age <= 30) break;
                System.out.println("Age must be between 17 and 30");
            } catch (InputMismatchException e) {
                System.out.println("Input must be numeric");
                scanner.next();
            }
        }

        String address;
        while(true) {
            System.out.print("Input address [must be ended with 'street']: ");
            address = scanner.nextLine().trim();

            if (address.endsWith("street")) break;
            
            System.out.println("Address must be ended with 'street'");    
        }

        String initial = "" + name.charAt(0) + name.charAt(1);
        initial.toLowerCase();

        boolean added = candidatesData.add(new Candidate(name, initial, gender, age, address));

        if(added) {
            System.out.println("Data has been successfully inserted!");
            return;
        }
    }

    static void viewData() {
        candidatesDataByNameAscending();

        if(candidatesData.isEmpty()) return;
    }

    static void removeData() {
        candidatesDataByNameAscending();

        if(candidatesData.isEmpty()) return;

        int index;
        while(true) {
            System.out.print("Input candidate number to be removed [0 for cancel]: ");
            try {
                index = scanner.nextInt();

                if (index == 0) return;

                if (index > 0 && index <= candidatesData.size()) {
                    candidatesData.remove(index - 1);
                    System.out.println("Data has been successfully deleted!");
                    return;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input must be numeric");
                scanner.next();
            }
            System.out.println("No candidate found!");
        }
    }

    static void candidatesData() {
        if (!candidatesData.isEmpty()) {
            System.out.printf("%-3s | %-20s | %-7s | %-6s | %-3s | %-20s%n", "No.", "Name", "Initial", "Gender", "Age", "Address");
            System.out.println("==========================================================================");
            int count = 1;
            for (Candidate candidate : candidatesData) {
                System.out.printf("%-3d | %-20s | %-7s | %-3d | %-20s | %-6s%n", count, candidate.getName(), candidate.getInitial(), candidate.getGender(), candidate.getAge(), candidate.getAddress());
                count++;
            }
            return;
        }
        System.out.println("No data!");
    }

    static void candidatesDataByNameAscending() {
        Collections.sort(candidatesData, new Comparator<Candidate>() {
            @Override
            public int compare(Candidate c1, Candidate c2) {
                return c1.getName().compareTo(c2.getName());
            }
        });
        candidatesData();
    }
}
