import entity.Customer;
import service.BillingService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BillingService billingService = new BillingService();

        while (true) {
            System.out.println("1. Add Customer");
            System.out.println("2. Generate Invoice");
            System.out.println("3. Show Invoices");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            String choiceInput = sc.nextLine().trim();
            int choice;
            try {
                choice = Integer.parseInt(choiceInput);
            } catch (NumberFormatException ex) {
                System.out.println("Invalid option. Please enter a number between 1 and 4.");
                continue;
            }

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Customer name: ");
                        String name = sc.nextLine().trim();
                        System.out.print("Phone: ");
                        String phone = sc.nextLine().trim();
                        billingService.customerService.addCustomer(new Customer(0, name, phone));
                        System.out.println("Customer added successfully.");
                        break;
                    case 2:
                        System.out.print("Customer ID: ");
                        int customerId = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("Vehicle ID: ");
                        int vehicleId = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("Service IDs (comma separated): ");
                        String servicesRaw = sc.nextLine();
                        List<Integer> serviceIds = parseServiceIds(servicesRaw);
                        billingService.createInvoice(customerId, vehicleId, serviceIds);
                        break;
                    case 3:
                        billingService.showAllInvoices();
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid option. Please choose between 1 and 4.");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Please enter numeric values where required.");
            } catch (SQLException ex) {
                System.out.println("Database error: " + ex.getMessage());
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }

    private static List<Integer> parseServiceIds(String servicesRaw) {
        String[] parts = servicesRaw.split(",");
        List<Integer> ids = new ArrayList<>();
        for (String part : parts) {
            String trimmed = part.trim();
            if (!trimmed.isEmpty()) {
                ids.add(Integer.parseInt(trimmed));
            }
        }
        return ids;
    }
}
