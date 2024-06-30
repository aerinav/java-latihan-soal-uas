import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;

class Patients {
    private String name;
    private int age;
    private String address;

    public Patients(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }
}

public class PatientsData {
    private static ArrayList<Patients> patientsData = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;

        while(!exit) {
            System.out.println("====Patient's Data====");
            System.out.println("1. Input New Data");
            System.out.println("2. View Patient's Data");
            System.out.println("3. Exit");
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
            System.out.print("Input name [3-20 characters]: ");
            name = scanner.nextLine().trim();

            if (name.length() >= 3 && name.length() <= 20) break;
            
            System.out.println("Name must be between 3 and 20 characters");    
        }

        int age;
        while(true) {
            System.out.print("Input age [10-100]: ");
            try {
                age = scanner.nextInt();   
                if(age >= 10 && age <= 100) break;
                System.out.println("Age must be between 10 and 100");
            } catch (InputMismatchException e) {
                System.out.println("Input must be numeric");
                scanner.next();
            }
        }

        String address;
        while(true) {
            System.out.print("Input address [5-30 characters]: ");
            address = scanner.nextLine().trim();

            if (address.length() >= 5 && address.length() <= 30) break;
            
            System.out.println("Address must be between 5 and 30 characters");    
        }

        boolean added = patientsData.add(new Patients(name, age, address));

        if(added) {
            System.out.println("Data has been successfully inserted!");
            return;
        }
    }

    static void viewData() {
        patientsData();

        if(patientsData.isEmpty()) return;

        boolean back = false;

        while(!back) {
            System.out.println();
            System.out.println("1. Sort data by Name Ascending");
            System.out.println("2. Sort data by Name Descending");
            System.out.println("3. Sort data by Age Ascending");
            System.out.println("4. Sort data by Age Descending");
            System.out.println("5. Back");
            System.out.print("Choose: ");

            int menu = scanner.nextInt();
            scanner.nextLine();

            switch (menu) {
                case 1:
                    nameAscending();
                    break;
                case 2:
                    nameDescending();
                    break;
                case 3:
                    ageAscending();
                    break;
                case 4:
                    ageDescending();
                    break;
                case 5:
                    back = true;
                    System.out.println("Exit...");
                    break;
                default:
                    System.out.println("Invalid menu");
                    break;
            }
        }  
    }

    static void patientsData() {
        if (!patientsData.isEmpty()) {
            System.out.printf("%-3s | %-20s | %-3s | %-20s%n", "No.", "Name", "Age", "Address");
            System.out.println("===========================================================");
            int count = 1;
            for (Patients patient : patientsData) {
                System.out.printf("%-3d | %-20s | %-3d | %-20s%n", count, patient.getName(), patient.getAge(), patient.getAddress());
                count++;
            }  
            return;
        }
        System.out.println("No data!");
    }

    static void nameAscending() {
        Collections.sort(patientsData, new Comparator<Patients>() {
            @Override
            public int compare(Patients p1, Patients p2) {
                return p1.getName().compareTo(p2.getName());
            }
        });
        patientsData();
    }

    static void nameDescending() {
        Collections.sort(patientsData, new Comparator<Patients>() {
            @Override
            public int compare(Patients p1, Patients p2) {
                return p2.getName().compareTo(p1.getName());
            }
        });
        patientsData();
    }

    static void ageAscending() {
        Collections.sort(patientsData, new Comparator<Patients>() {
            @Override
            public int compare(Patients p1, Patients p2) {
                return Integer.compare(p1.getAge(), p2.getAge());
            }
        });
        patientsData();
    }

    static void ageDescending() {
        Collections.sort(patientsData, new Comparator<Patients>() {
            @Override
            public int compare(Patients p1, Patients p2) {
                return Integer.compare(p2.getAge(), p1.getAge());
            }
        });
        patientsData();
    }
}