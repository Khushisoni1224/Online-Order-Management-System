import java.sql.*;
import java.util.Scanner;

public class OnlineOrderManagement {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/order_database";
        String user = "root";  // your MySQL username
        String pass = "Khushimysql123";  // your MySQL password

        Scanner sc = new Scanner(System.in);

        try {
            // 1️⃣ Load and register driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2️⃣ Establish connection
            Connection con = DriverManager.getConnection(url, user, pass);
            System.out.println("✅ Connected to Order Database Successfully!");

            while (true) {
                System.out.println("\n=== 🛒 Online Order Management Menu ===");
                System.out.println("1. Add New Order");
                System.out.println("2. Display All Orders");
                System.out.println("3. Update Order Details");
                System.out.println("4. Delete Order");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();

                switch (choice) {

                    case 1:
                        // ➕ INSERT Operation
                        System.out.print("Enter Order ID: ");
                        int orderId = sc.nextInt();
                        sc.nextLine(); // consume newline
                        System.out.print("Enter Customer Name: ");
                        String custName = sc.nextLine();
                        System.out.print("Enter Product Name: ");
                        String prodName = sc.nextLine();
                        System.out.print("Enter Quantity: ");
                        int qty = sc.nextInt();
                        System.out.print("Enter Total Price: ");
                        double price = sc.nextDouble();

                        String insertQuery = "INSERT INTO orders VALUES (?, ?, ?, ?, ?)";
                        PreparedStatement psInsert = con.prepareStatement(insertQuery);
                        psInsert.setInt(1, orderId);
                        psInsert.setString(2, custName);
                        psInsert.setString(3, prodName);
                        psInsert.setInt(4, qty);
                        psInsert.setDouble(5, price);

                        int rowsInserted = psInsert.executeUpdate();
                        System.out.println(rowsInserted + " 🧾 order added successfully!");
                        psInsert.close();
                        break;

                    case 2:
                        // 📋 DISPLAY Operation
                        String selectQuery = "SELECT * FROM orders";
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(selectQuery);

                        System.out.println("\nOrder_ID\tCustomer\tProduct\t\tQuantity\tTotal Price");
                        System.out.println("-------------------------------------------------------------------");
                        while (rs.next()) {
                            System.out.printf("%d\t\t%-12s\t%-12s\t%d\t\t%.2f%n",
                                    rs.getInt("order_id"),
                                    rs.getString("customer_name"),
                                    rs.getString("product_name"),
                                    rs.getInt("quantity"),
                                    rs.getDouble("total_price"));
                        }
                        rs.close();
                        stmt.close();
                        break;

                    case 3:
                        // ✏ UPDATE Operation
                        System.out.print("Enter Order ID to update: ");
                        int uid = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter new Product Name: ");
                        String newProduct = sc.nextLine();
                        System.out.print("Enter new Quantity: ");
                        int newQty = sc.nextInt();
                        System.out.print("Enter new Total Price: ");
                        double newPrice = sc.nextDouble();

                        String updateQuery = "UPDATE orders SET product_name=?, quantity=?, total_price=? WHERE order_id=?";
                        PreparedStatement psUpdate = con.prepareStatement(updateQuery);
                        psUpdate.setString(1, newProduct);
                        psUpdate.setInt(2, newQty);
                        psUpdate.setDouble(3, newPrice);
                        psUpdate.setInt(4, uid);

                        int rowsUpdated = psUpdate.executeUpdate();
                        System.out.println(rowsUpdated + " 🛍️ order updated successfully!");
                        psUpdate.close();
                        break;

                    case 4:
                        // ❌ DELETE Operation
                        System.out.print("Enter Order ID to delete: ");
                        int did = sc.nextInt();

                        String deleteQuery = "DELETE FROM orders WHERE order_id=?";
                        PreparedStatement psDelete = con.prepareStatement(deleteQuery);
                        psDelete.setInt(1, did);

                        int rowsDeleted = psDelete.executeUpdate();
                        System.out.println(rowsDeleted + " 🗑️ order deleted successfully!");
                        psDelete.close();
                        break;

                    case 5:
                        // 🚪 EXIT
                        System.out.println("👋 Exiting Order Management System... Goodbye!");
                        con.close();
                        sc.close();
                        System.exit(0);

                    default:
                        System.out.println("❌ Invalid choice! Please try again.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
