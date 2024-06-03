package vehicle;

import java.util.Scanner;

public class Tester {
    public static void main(String[] args) {
        VehicleManager manager = new VehicleManager("vehicle.dat");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nVehicle Management System");
            System.out.println("0. Add new vehicle");
            System.out.println("1. Check if vehicle exists");
            System.out.println("2. Update vehicle");
            System.out.println("3. Delete vehicle");
            System.out.println("4. Search vehicle");
            System.out.println("5. Display vehicle list");
            System.out.println("6. Save vehicles to file");
            System.out.println("7. Print vehicle list");
            System.out.println("8. Quit");

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "0":
                    System.out.print("Enter ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Color: ");
                    String color = scanner.nextLine();
                    System.out.print("Enter Price: ");
                    String price = scanner.nextLine();  
                    System.out.print("Enter Brand: ");
                    String brand = scanner.nextLine();
                    System.out.print("Enter Type: ");
                    String type = scanner.nextLine();
                    System.out.print("Enter Product Year: ");
                    int productYear = Integer.parseInt(scanner.nextLine());
                    Vehicle vehicle = new Vehicle(id, name, color, price, brand, type, productYear);
                    System.out.println(manager.addVehicle(vehicle));
                    break;

                case "1":
                    System.out.print("Enter vehicle ID to check: ");
                    id = scanner.nextLine();
                    if (manager.checkVehicleExists(id)) {
                        System.out.println("Vehicle exists.");
                    } else {
                        System.out.println("No vehicle found.");
                    }
                    break;

                case "2":
                    System.out.print("Enter vehicle ID to update: ");
                    id = scanner.nextLine();
                    System.out.print("Enter new name (or leave blank to keep current): ");
                    name = scanner.nextLine();
                    System.out.print("Enter new color (or leave blank to keep current): ");
                    color = scanner.nextLine();
                    System.out.print("Enter new price (or leave blank to keep current): ");
                    String priceInput = scanner.nextLine();
                    System.out.print("Enter new brand (or leave blank to keep current): ");
                    brand = scanner.nextLine();
                    System.out.print("Enter new type (or leave blank to keep current): ");
                    type = scanner.nextLine();
                    System.out.print("Enter new product year (or leave blank to keep current): ");
                    String productYearInput = scanner.nextLine();
                    Integer productYearValue = productYearInput.isEmpty() ? null : Integer.parseInt(productYearInput);
                    System.out.println(manager.updateVehicle(id, name, color, priceInput, brand, type, productYearValue));
                    break;

                case "3":
                    System.out.print("Enter vehicle ID to delete: ");
                    id = scanner.nextLine();
                    System.out.print("Are you sure you want to delete vehicle with ID " + id + "? (yes/no): ");
                    String confirmation = scanner.nextLine();
                    if (confirmation.equalsIgnoreCase("yes")) {
                        System.out.println(manager.deleteVehicle(id));
                    } else {
                        System.out.println("Delete operation cancelled.");
                    }
                    break;

                case "4":
                    System.out.print("Search by 1. ID or 2. Name? ");
                    String subChoice = scanner.nextLine();
                    if (subChoice.equals("1")) {
                        System.out.print("Enter vehicle ID to search: ");
                        id = scanner.nextLine();
                        System.out.println(manager.searchVehicleById(id));
                    } else if (subChoice.equals("2")) {
                        System.out.print("Enter vehicle name to search: ");
                        name = scanner.nextLine();
                        manager.searchVehicleByName(name).forEach(System.out::println);
                    } else {
                        System.out.println("Invalid choice.");
                    }
                    break;

                case "5":
                    System.out.print("Display 1. All vehicles or 2. Sorted by price? ");
                    subChoice = scanner.nextLine();
                    if (subChoice.equals("1")) {
                        manager.displayAllVehicles().forEach(System.out::println);
                    } else if (subChoice.equals("2")) {
                        manager.displayVehiclesSortedByPrice().forEach(System.out::println);
                    } else {
                        System.out.println("Invalid choice.");
                    }
                    break;

                case "6":
                    manager.saveVehicles();
                    System.out.println("Vehicles saved to file.");
                    break;

                case "7":
                    System.out.print("Print 1. All vehicles or 2. Sorted by price? ");
                    subChoice = scanner.nextLine();
                    if (subChoice.equals("1")) {
                        manager.printVehicles(false);
                    } else if (subChoice.equals("2")) {
                        manager.printVehicles(true);
                    } else {
                        System.out.println("Invalid choice.");
                    }
                    break;

                case "8":
                    System.out.println("Exiting program.");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
