import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class Food {
    private int id;
    private String type;
    private String name;
    private float calories;
    private int price;

    public Food(int id, String type, String name, float calories, int price) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.calories = calories;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public float getCalories() {
        return calories;
    }

    public int getPrice() {
        return price;
    }
}

public class FoodOrder {
    private static ArrayList<Food> foodList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static int nextId = 1;

    public static void main(String[] args) {
        boolean exit = false;

        while(!exit) {
            System.out.println("====Food Order====");
            System.out.println("1. Insert new food");
            System.out.println("2. List of food");
            System.out.println("3. Search food");
            System.out.println("4. Delete food");
            System.out.println("5. Exit");
            System.out.print("Choose: ");

            int menu = scanner.nextInt();
            scanner.nextLine();

            switch (menu) {
                case 1:
                    insertFood();
                    break;
                case 2:
                    listFood();
                    break;
                case 3:
                    searchFood();
                    break;
                case 4:
                    deleteFood();
                    break;
                case 5:
                    exit = true;
                    System.out.println("Exit...");
                    break;
                default:
                    System.out.println("Invalid menu");
                    break;
            }
        }  
    }

    static void insertFood() {
        String type;
        while (true) {
            System.out.print("Input food's type [Appetizer | Main Course | Dessert]: ");
            type = scanner.nextLine().trim().toLowerCase();

            if (type.equals("appetizer") || type.equals("main course") || type.equals("dessert")) break;
  
            System.out.println("Please enter Appetizer, Main Course, or Dessert.");  
        }

        String name;
        while(true) {
            System.out.print("Input food's name [3-20 characters]: ");
            name = scanner.nextLine().trim();

            if (name.length() >= 3 && name.length() <= 20) break;
            
            System.out.println("Food's name must be between 3 and 20 characters.");    
        }
        
        float calories;
        while (true) {
            System.out.print("Input food's calories [1.0 - 4.0]: ");
            try {
                calories = scanner.nextFloat();
                if (calories >= 1.0 && calories <= 4.0) break;
            
                System.out.println("Calories must be between 1.0 and 4.0.");
            } catch (InputMismatchException e) {
                System.out.println("Please input a decimal number.");
                scanner.next();
            }
        }

        int price;
        while (true) {
            System.out.print("Input food's price [10000 - 50000]: ");
            try {
                price = scanner.nextInt();
                if (price >= 10000 && price <= 50000) break;
                
                System.out.println("Price must be between 10000 and 50000.");
            } catch (InputMismatchException e) {
                System.out.println("Please input an integer.");
                scanner.next();
            }
        }

        boolean added = foodList.add(new Food(nextId++, type, name, calories, price));
        
        if(added) {
            System.out.println("Data has been successfully inserted!");
            pressEnterToContinue();
            return;
        }  
    }

    static void listFood() {
        if (!foodList.isEmpty()) {
            System.out.println("List of food items:");
            for (Food food : foodList) {
                System.out.println("Food ID: " + food.getId());
                System.out.println("Type: " + food.getType());
                System.out.println("Name: " + food.getName());
                System.out.println("Calories: " + food.getCalories());
                System.out.println("Price: " + food.getPrice());
                System.out.println("----------------------");
            }  
            return;
        }
        System.out.println("No data!");
        pressEnterToContinue();
    }

    static void searchFood() {
        String foodName;
        while(true) {
            System.out.print("Input food's name [3-20]: ");
            foodName = scanner.nextLine().trim();

            if (foodName.length() >= 3 || foodName.length() <= 20) break;
        
            System.out.println("Foods name must be between 3 and 20 characters.");
        }
        

        boolean found = false;
        for (Food food : foodList) {
            if (food.getName().equalsIgnoreCase(foodName)) {
                System.out.println("Food ID: " + food.getId());
                System.out.println("Type: " + food.getType());
                System.out.println("Name: " + food.getName());
                System.out.println("Calories: " + food.getCalories());
                System.out.println("Price: " + food.getPrice());
                System.out.println("----------------------");
                found = true;
                break;
            }
        }

        if (!found) System.out.println("No data found!");
    
        pressEnterToContinue();
    }

    static void deleteFood() {
        listFood();

        if(foodList.isEmpty()) return;

        while(true) {
            System.out.print("Input Food ID to be deleted [0 for cancel]: ");
            try {
                int foodId = scanner.nextInt();

                if(foodId == 0) return;

                boolean removed = foodList.removeIf(food -> food.getId() == foodId);
                if (removed) {
                    System.out.println("Data has been succesfully deleted!");
                    pressEnterToContinue();
                    return;
                } 
            } catch (InputMismatchException e) {
                System.out.println("Please input an integer");
                scanner.next();
            }   
            System.out.println("No data!");
            return;
        }
    }

    static void pressEnterToContinue() {
        System.out.print("Press Enter to go back to the menu...");
        scanner.nextLine();
        System.out.println();
    }
}

