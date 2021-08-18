package main;

import melbourneEats.*;
import melbourneEats.exceptions.EatsException;

import java.util.*;

public class Menu {
    Scanner sc;
    Order order;
    String banner;
    MelbourneEatsSystem melbourneEatsSystem;
    private static final String Browse_By_Category = "1";
    private static final String Search_By_Restaurant = "2";
    private static final String Checkout = "3";
    private static final String Exit = "4";
    private static final String Format = "%-30s %s\n";

    public Menu() {
        sc = new Scanner(System.in);
        melbourneEatsSystem = new MelbourneEatsSystem();
        banner = new String(new char[50]).replace('\u0000', '-');
    }

    public void run() {
        System.out.println("Welcome to Melbourne Eats");
        while (true) {
            displayMainMenu();
            String str = sc.nextLine();
            switch (str) {
                case Browse_By_Category -> browseByCategory();
                case Search_By_Restaurant -> searchByRestaurant();
                case Checkout -> {
                    ArrayList<Order> orderTest = melbourneEatsSystem.getOrder();
                    if (orderTest.size() == 0) {
                        System.out.println("there no any order");
                        break;
                    }
                    checkout(orderTest);
                    System.out.println(banner + "\n" + "Thanks for ordering with Melbourne Eats. Enjoy your meal.\n");
                    return;
                }
                case Exit -> {
                    exit();
                    return;
                }
                default -> System.out.println("Please select a valid menu option.");
            }
        }
    }

    private void exit() {
        System.out.println("Program ending.");
    }

    private void checkout(ArrayList<Order> orderTest) {
        System.out.println(banner + "\n" + "You have " + "ordered the following items" + "\n" + banner);
        double deliveryFee2 = 0;
        double allCost = 0;
        for (Order list : orderTest) {

            Map<String, Double> foodOder = new HashMap<>(list.getFoodList());
            Map<String, Double> foodOderAmounts = new HashMap<>(list.getFoodAmounts());

            System.out.println(list.getRestaurantName());
            for (Map.Entry<String, Double> map : foodOder.entrySet()) {
                if (foodOderAmounts.containsKey(map.getKey())) {
                    System.out.printf(Format, foodOderAmounts.get(map.getKey()).intValue() + " "
                            + map.getKey(), map.getValue() * foodOderAmounts.get(map.getKey()).intValue());
                }
            }

            System.out.printf(Format, "Delivery fee", list.getDeliveryFee());
            deliveryFee2 += list.getDeliveryFee();
            allCost += list.allFoodCost() + deliveryFee2;
            System.out.println(banner);
        }
        double save = melbourneEatsSystem.allOrderFoodDiscountsCost()
                + melbourneEatsSystem.allOrderFoodDiscountsDeliveryCost();

            System.out.printf("%-31s", "Order price:");
            System.out.printf("%-31s", "$" + String.format("%.2f",
                    melbourneEatsSystem.allOrderFoodDiscountsCost()));
            System.out.println();

            System.out.printf("%-31s", "Delivery fee:");
            System.out.printf("%-31s", "$" + String.format("%.2f",
                    melbourneEatsSystem.allOrderFoodDiscountsDeliveryCost()));
            System.out.println();

            System.out.printf("%-31s", "You have saved:");
            System.out.printf("%-31s", "$" + String.format("%.2f", allCost - save));
            System.out.println();

            System.out.printf("%-31s", "Total amount to pay:");
            System.out.printf("%-31s", "$" + String.format("%.2f", save));
            System.out.println();
    }

    private void searchByRestaurant() {
        try {
            ArrayList<MelbourneEatsPackage> List = melbourneEatsSystem.getRestaurantList();
            ArrayList<String> matchingList = new ArrayList<>();
            System.out.print("Please enter a restaurant name: ");
            String str = sc.nextLine();

            if (str.isEmpty()) {
                throw new EatsException("name is null or empty string.");
            }
            if (str.isBlank()) {
                throw new EatsException("name is null or empty string.");
            }
            for (MelbourneEatsPackage i : List) {
                if (i.getName().toLowerCase(Locale.ROOT).contains(str.toLowerCase(Locale.ROOT))) {
                    matchingList.add(i.getName());
                }
            }
            System.out.println(banner + "\n" + "Welcome to " + "> Select from matching list" + "\n" + banner);

            //loop matching List
            int number = 0;
            for (String i : matchingList) {

                System.out.println(number + 1 + ") " + i);
                number++;
            }
            System.out.println(number + 1 + ") Go to main menu");
            System.out.print("Please select: ");

            int restaurantNumber;
            String input = sc.nextLine();

            if (input.isEmpty()) {
                throw new EatsException("input is null or empty string.");
            }
            if (input.isBlank()) {
                throw new EatsException("input is null or empty string.");
            }
            try {
                restaurantNumber = Integer.parseInt(input);
            } catch (Exception e) {
                throw new EatsException("please input int number");
            }

            if (restaurantNumber == number + 1) {
                return;
            }
            if (restaurantNumber > number + 1) {
                throw new EatsException("Please select a valid menu option.");
            }
            displayFoodAndChoose(restaurantNumber - 1, matchingList.get(restaurantNumber - 1), List);
        } catch (EatsException e) {
            System.out.println(e.getMessage());
        }
    }

    private void browseByCategory() {
        try {

            //get list
            ArrayList<MelbourneEatsPackage> categoryList = melbourneEatsSystem.getRestaurantList();
            ArrayList<String> newCategoryList = new ArrayList<>();

            System.out.println(banner + "\n" + "Welcome to " + "> Select by category" + "\n" + banner);

            //remove same category
            for (MelbourneEatsPackage list : categoryList) {
                if (!newCategoryList.contains(list.getCategory())) {
                    newCategoryList.add(list.getCategory());
                }
            }
            //loop category
            int number = 0;
            for (String list : newCategoryList) {
                System.out.println(number + 1 + ") " + list);
                number++;
            }
            System.out.println(number + 1 + ") Go to main menu");
            System.out.print("Select an option: ");

            int categoryNumber;
            String str = sc.nextLine();

            if (str.isEmpty()) {
                throw new EatsException("input is null or empty string.");
            }
            if (str.isBlank()) {
                throw new EatsException("input is null or empty string.");
            }
            try {
                categoryNumber = Integer.parseInt(str);
            } catch (Exception e) {
                throw new EatsException("please input int number");
            }

            if (categoryNumber == number + 1) {
                return;
            }
            if (categoryNumber > number + 1) {
                throw new EatsException("Please select a valid menu option.");
            }
            displayRestaurantNameAndChoose(newCategoryList.get(categoryNumber - 1), categoryList);
        } catch (EatsException e) {
            System.out.println(e.getMessage());
        }
    }

    private void displayRestaurantNameAndChoose(String name, ArrayList<MelbourneEatsPackage> restaurantNameList) {
        try {
            ArrayList<String> newRestaurantNameList = new ArrayList<>();

            System.out.println(banner + "\n" + "Welcome to " + "> Select from " + name + " list" + "\n" + banner);

            //add same Restaurant Name
            for (MelbourneEatsPackage list : restaurantNameList) {
                if (name.equals(list.getCategory())) {
                    newRestaurantNameList.add(list.getName());
                }
            }
            //loop Restaurant
            int number = 0;
            for (String list : newRestaurantNameList) {
                System.out.println(number + 1 + ") " + list);
                number++;
            }
            System.out.println(number + 1 + ") Go to main menu");
            System.out.print("Select an option: ");

            int restaurantNumber;
            String str = sc.nextLine();

            if (str.isEmpty()) {
                throw new EatsException("input is null or empty string.");
            }
            if (str.isBlank()) {
                throw new EatsException("input is null or empty string.");
            }

            try {
                restaurantNumber = Integer.parseInt(str);
            } catch (Exception e) {
                throw new EatsException("please input int number");
            }

            if (restaurantNumber == number + 1) {
                return;
            }
            if (restaurantNumber > number + 1) {
                throw new EatsException("Please select a valid menu option.");
            }
            displayFoodAndChoose(restaurantNumber - 1, newRestaurantNameList.get(restaurantNumber - 1), restaurantNameList);
        } catch (EatsException e) {
            System.out.println(e.getMessage());
        }
    }

    private void displayFoodAndChoose(int i, String name, ArrayList<MelbourneEatsPackage> restaurantList) {
        try {

            ArrayList<Food> foodList = new ArrayList<>();
            Map<String, Double> foodOder = new HashMap<>();
            Map<String, Double> foodOderAmounts = new HashMap<>();

            System.out.println(banner + "\n" + "Welcome to " + "> Select from " + name + " list" + "\n" + banner);

            //add same Restaurant food list
            for (MelbourneEatsPackage list : restaurantList) {
                if (name.equals(list.getName())) {
                    foodList.addAll(list.getList());
                }
            }
            //loop Restaurant
            int number = 0;
            for (Food list : foodList) {
                System.out.printf(Format, number + 1 + ") " + list.getName(), "$" + list.getPrice());
                number++;
            }
            System.out.println(number + 1 + ") No more");

            System.out.print("Select an option: ");
            int foodName;
            String str = sc.nextLine();
            if (str.isEmpty()) {
                throw new EatsException("input is null or empty string.");
            }
            if (str.isBlank()) {
                throw new EatsException("input is null or empty string.");
            }
            try {
                foodName = Integer.parseInt(str);
            } catch (Exception e) {
                throw new EatsException("please input int number");
            }

            if (foodName == number + 1) {
                return;
            }
            if (foodName > number + 1) {
                throw new EatsException("Please select a valid menu option.");
            }
            System.out.print("Please enter an amount: ");
            double amounts;
            String foodAmount = sc.nextLine();
            if (foodAmount.isEmpty()) {
                throw new EatsException("input is null or empty string.");
            }
            if (foodAmount.isBlank()) {
                throw new EatsException("input is null or empty string.");
            }
            try {
                amounts = Double.parseDouble(foodAmount);
            } catch (NumberFormatException e) {
                throw new EatsException("please input int number");
            }

            foodOder.put(foodList.get(foodName - 1).getName(), foodList.get(foodName - 1).getPrice());
            foodOderAmounts.put(foodList.get(foodName - 1).getName(), amounts);
            order = new Order(name, foodOder, foodOderAmounts, restaurantList.get(i).getDelivery_fee());

            while (true) {
                System.out.println(banner + "\n" + "Welcome to " + "> Select from " + name + " list" + "\n" + banner);
                int number1 = 0;
                for (Food list : foodList) {

                    System.out.printf(Format, number1 + 1 + ") " + list.getName(), "$" + list.getPrice());
                    number1++;
                }
                System.out.println(number1 + 1 + ") No more");

                System.out.print("Select an option: ");
                int foodName1;
                String str1 = sc.nextLine();
                if (str1.isEmpty()) {
                    throw new EatsException("input is null or empty string.");
                }
                if (str1.isBlank()) {
                    throw new EatsException("input is null or empty string.");
                }
                try {
                    foodName1 = Integer.parseInt(str1);
                } catch (Exception e) {
                    throw new EatsException("please input int number");
                }

                if (foodName1 == number1 + 1) {
                    melbourneEatsSystem.addOrder(order);
                    return;
                }
                if (foodName1 > number1 + 1) {
                    throw new EatsException("Please select a valid menu option.");
                }

                System.out.print("Please enter an amount: ");
                double amounts1;
                String foodAmount1 = sc.nextLine();
                if (foodAmount1.isEmpty()) {
                    throw new EatsException("input is null or empty string.");
                }
                if (foodAmount1.isBlank()) {
                    throw new EatsException("input is null or empty string.");
                }
                try {
                    amounts1 = Double.parseDouble(foodAmount1);
                } catch (NumberFormatException e) {
                    throw new EatsException("please input int number");
                }
                order.addFood(foodList.get(foodName1 - 1).getName(), foodList.get(foodName1 - 1).getPrice());
                order.addFoodAmount(foodList.get(foodName1 - 1).getName(), amounts1);
            }
        } catch (EatsException e) {
            System.out.println(e.getMessage());
        }
    }

    private void displayMainMenu() {
        System.out.println(banner + "\n" + "Welcome to " + "> Select from main menu" + "\n" + banner);
        System.out.println("1) Browse by category");
        System.out.println("2) Search by restaurant");
        System.out.println("3) Checkout");
        System.out.println("4) Exit");
        System.out.print("Select an option: ");
    }
}
