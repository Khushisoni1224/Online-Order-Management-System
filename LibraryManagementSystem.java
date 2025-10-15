import java.sql.*;
import java.util.Scanner;

public class LibraryManagementSystem {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/library_database";
        String user = "root";  // 🔑 your MySQL username
        String pass = "Khushimysql123";  // 🔒 your MySQL password

        Scanner sc = new Scanner(System.in);

        try {
            // 1️⃣ Load and register driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2️⃣ Establish connection
            Connection con = DriverManager.getConnection(url, user, pass);
            System.out.println("✅ Connected to Library Database Successfully!");

            while (true) {
                System.out.println("\n=== 📚 Library Management Menu ===");
                System.out.println("1. Add Book");
                System.out.println("2. Display All Books");
                System.out.println("3. Update Book Details");
                System.out.println("4. Delete Book");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();

                switch (choice) {

                    case 1:
                        // ➕ INSERT Operation
                        System.out.print("Enter Book ID: ");
                        int bookId = sc.nextInt();
                        sc.nextLine(); // consume newline
                        System.out.print("Enter Book Title: ");
                        String title = sc.nextLine();
                        System.out.print("Enter Author Name: ");
                        String author = sc.nextLine();
                        System.out.print("Enter Quantity: ");
                        int qty = sc.nextInt();

                        String insertQuery = "INSERT INTO books VALUES (?, ?, ?, ?)";
                        PreparedStatement psInsert = con.prepareStatement(insertQuery);
                        psInsert.setInt(1, bookId);
                        psInsert.setString(2, title);
                        psInsert.setString(3, author);
                        psInsert.setInt(4, qty);

                        int rowsInserted = psInsert.executeUpdate();
                        System.out.println(rowsInserted + " 📖 book added successfully!");
                        psInsert.close();
                        break;

                    case 2:
                        // 📋 DISPLAY Operation
                        String selectQuery = "SELECT * FROM books";
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(selectQuery);

                        System.out.println("\nBook_ID\tTitle\t\tAuthor\t\tQuantity");
                        System.out.println("-----------------------------------------------------");
                        while (rs.next()) {
                            System.out.printf("%d\t%-15s\t%-15s\t%d%n",
                                    rs.getInt("book_id"),
                                    rs.getString("title"),
                                    rs.getString("author"),
                                    rs.getInt("quantity"));
                        }
                        rs.close();
                        stmt.close();
                        break;

                    case 3:
                        // ✏ UPDATE Operation
                        System.out.print("Enter Book ID to update: ");
                        int uid = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter new Title: ");
                        String newTitle = sc.nextLine();
                        System.out.print("Enter new Author: ");
                        String newAuthor = sc.nextLine();
                        System.out.print("Enter new Quantity: ");
                        int newQty = sc.nextInt();

                        String updateQuery = "UPDATE books SET title=?, author=?, quantity=? WHERE book_id=?";
                        PreparedStatement psUpdate = con.prepareStatement(updateQuery);
                        psUpdate.setString(1, newTitle);
                        psUpdate.setString(2, newAuthor);
                        psUpdate.setInt(3, newQty);
                        psUpdate.setInt(4, uid);

                        int rowsUpdated = psUpdate.executeUpdate();
                        System.out.println(rowsUpdated + " 📘 book details updated successfully!");
                        psUpdate.close();
                        break;

                    case 4:
                        // ❌ DELETE Operation
                        System.out.print("Enter Book ID to delete: ");
                        int did = sc.nextInt();

                        String deleteQuery = "DELETE FROM books WHERE book_id=?";
                        PreparedStatement psDelete = con.prepareStatement(deleteQuery);
                        psDelete.setInt(1, did);

                        int rowsDeleted = psDelete.executeUpdate();
                        System.out.println(rowsDeleted + " 📕 book deleted successfully!");
                        psDelete.close();
                        break;

                    case 5:
                        // 🚪 EXIT
                        System.out.println("👋 Exiting Library System... Goodbye!");
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
