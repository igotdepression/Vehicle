package vehicle;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class VehicleManager {
    private List<Vehicle> vehicles;
    private String filename;

    public VehicleManager(String filename) {
        this.filename = filename;
        this.vehicles = loadVehicles();
    }

    private List<Vehicle> loadVehicles() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<Vehicle>) ois.readObject();
        } catch (FileNotFoundException | EOFException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void saveVehicles() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(vehicles);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String addVehicle(Vehicle vehicle) {
        if (vehicles.stream().anyMatch(v -> v.getId().equals(vehicle.getId()))) {
            return "Vehicle ID already exists!";
        }
        vehicles.add(vehicle);
        saveVehicles();
        return "Vehicle added successfully!";
    }

    public boolean checkVehicleExists(String id) {
        return vehicles.stream().anyMatch(v -> v.getId().equals(id));
    }

    public String updateVehicle(String id, String name, String color, String price, String brand, String type, Integer productYear) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getId().equals(id)) {
                if (name != null && !name.isEmpty()) vehicle.setName(name);
                if (color != null && !color.isEmpty()) vehicle.setColor(color);
                if (price != null && !price.isEmpty()) vehicle.setPrice(price);
                if (brand != null && !brand.isEmpty()) vehicle.setBrand(brand);
                if (type != null && !type.isEmpty()) vehicle.setType(type);
                if (productYear != null) vehicle.setProductYear(productYear);
                saveVehicles();
                return "Vehicle updated successfully!";
            }
        }
        return "Vehicle not found!";
    }

    public String deleteVehicle(String id) {
        Vehicle vehicleToDelete = null;
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getId().equals(id)) {
                vehicleToDelete = vehicle;
                break;
            }
        }
        if (vehicleToDelete != null) {
            vehicles.remove(vehicleToDelete);
            saveVehicles();
            return "Vehicle deleted successfully!";
        }
        return "Vehicle not found!";
    }

    public String searchVehicleById(String id) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getId().equals(id)) {
                return vehicle.toString();
            }
        }
        return "Vehicle not found!";
    }

    public List<String> searchVehicleByName(String name) {
        List<String> results = vehicles.stream()
                .filter(v -> v.getName().toLowerCase().contains(name.toLowerCase()))
                .map(Vehicle::toString)
                .collect(Collectors.toList());
        return results.isEmpty() ? List.of("No vehicles found!") : results;
    }

    public List<String> displayAllVehicles() {
        return vehicles.stream().map(Vehicle::toString).collect(Collectors.toList());
    }

    public List<String> displayVehiclesSortedByPrice() {
        return vehicles.stream()
                .sorted(Comparator.comparingDouble(v -> Double.parseDouble(v.getPrice())).reversed())
                .map(Vehicle::toString)
                .collect(Collectors.toList());
    }

    public void printVehicles(boolean sortByPrice) {
        List<String> vehiclesToPrint = sortByPrice ? displayVehiclesSortedByPrice() : displayAllVehicles();
        vehiclesToPrint.forEach(System.out::println);
    }
}
