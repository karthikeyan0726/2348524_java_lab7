import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

class Customer implements Comparable<Customer> {
    String customerId;
    String name;

    public Customer(String customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Customer other) {
        return this.customerId.compareTo(other.customerId);
    }

    public String toString() {
        return "Customer{id='" + customerId + "', name='" + name + "'}";
    }
}

class Product implements Comparable<Product> {
    String productId;
    String name;
    double price;

    public Product(String productId, String name, double price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public int compareTo(Product other) {
        return this.productId.compareTo(other.productId);
    }

    public String toString() {
        return "Product{id='" + productId + "', name='" + name + "', price=" + price + "}";
    }
}

class Order implements Comparable<Order> {
    String orderId;
    Customer customer;
    Product product;
    int quantity;

    public Order(String orderId, Customer customer, Product product, int quantity) {
        this.orderId = orderId;
        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
    }

    public String getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        if (product != null) {
            return quantity * product.getPrice();
        } else {
            return 0.0; // Or handle it as needed
        }
    }

    @Override
    public int compareTo(Order other) {
        return this.orderId.compareTo(other.orderId);
    }

    public String toString() {
        return "Order{id='" + orderId + "', customer=" + customer +
               ", product=" + product + ", quantity=" + quantity +
               ", total price=" + getTotalPrice() + "}";
    }
}

public class AmazonCRMSystem {
    private static ArrayList<Customer> customerList = new ArrayList<>();
    private static ArrayList<Product> productList = new ArrayList<>();
    private static ArrayList<Order> orderList = new ArrayList<>();
    private static HashMap<String, Customer> customerMap = new HashMap<>();
    private static HashMap<String, Product> productMap = new HashMap<>();
    private static HashSet<Product> uniqueProducts = new HashSet<>();
    private static TreeSet<Customer> sortedCustomers = new TreeSet<>();
    private static TreeSet<Product> sortedProducts = new TreeSet<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        displayMenu();
    }

    private static void displayMenu() {
        int choice;
        do {
            System.out.println("\n---- Amazon CRM System ----");
            System.out.println("1. Add Customer");
            System.out.println("2. Add Product");
            System.out.println("3. Place Order");
            System.out.println("4. Display Order List");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addCustomer();
                    break;
                case 2:
                    addProduct();
                    break;
                case 3:
                    placeOrder();
                    break;
                case 4:
                    displayOrderList();
                    break;
                case 5:
                    System.out.println("Exiting... Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    private static void addCustomer() {
        System.out.print("Enter customer ID: ");
        String customerId = scanner.nextLine();
        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();
        Customer customer = new Customer(customerId, customerName);
        customerList.add(customer);
        customerMap.put(customerId, customer);
        sortedCustomers.add(customer);
        System.out.println("Customer added successfully!");
    }

    private static void addProduct() {
        System.out.print("Enter product ID: ");
        String productId = scanner.nextLine();
        System.out.print("Enter product name: ");
        String productName = scanner.nextLine();
        System.out.print("Enter product price: ");
        double productPrice = scanner.nextDouble();
        Product product = new Product(productId, productName, productPrice);
        productList.add(product);
        productMap.put(productId, product);
        uniqueProducts.add(product);
        sortedProducts.add(product);
        System.out.println("Product added successfully!");
    }

    private static void placeOrder() {
        System.out.print("Enter customer ID: ");
        String customerId = scanner.nextLine();
        Customer customer = customerMap.get(customerId);
        if (customer == null) {
            System.out.println("Customer not found. Please add the customer first.");
            return;
        }

        System.out.print("Enter product ID: ");
        String productId = scanner.nextLine();
        Product product = productMap.get(productId);
        if (product == null) {
            System.out.println("Product not found. Please add the product first.");
            return;
        }

        System.out.print("Enter order ID: ");
        String orderId = scanner.nextLine();
        System.out.print("Enter order quantity: ");
        int orderQuantity = scanner.nextInt();

        Order order = new Order(orderId, customer, product, orderQuantity);
        orderList.add(order);
        System.out.println("Order placed successfully!");
    }

    private static void displayOrderList() {
        if (orderList.isEmpty()) {
            System.out.println("Order list is empty.");
        } else {
            System.out.println("\n---- Order List ----");
            for (Order order : orderList) {
                System.out.println(order);
            }
        }
    }
}
