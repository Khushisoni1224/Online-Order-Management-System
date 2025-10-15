**Java Mini Projects (CRUD + Online Order Management)**

This repository contains two Java mini-projects developed using Java, MySQL, and the MySQL Connector JAR.
Both projects demonstrate database connectivity (JDBC) and core CRUD (Create, Read, Update, Delete) operations.

📁 Projects Included

1️⃣ **JavaCRUDdemo**

Description:
A simple console-based Java program that performs basic CRUD operations on a Student Database.

Features:
i) Add new student records
ii) Update existing student information
iii) Delete student data
iv) Display all student records

Connects to MySQL database using JDBC
Database Schema (MySQL):

CREATE DATABASE StudentDB;
USE StudentDB;
CREATE TABLE students (
    roll_no INT PRIMARY KEY,
    name VARCHAR(50),
    department VARCHAR(30),
    marks INT
);

# ** URL needed : **
String url = "jdbc:mysql://localhost:3306/"college_database";
String user = "root";
String password = "Khushimysql123";


2️⃣ **OnlineOrderManagement**

Description:
A Java program that manages Online Order Details — built to demonstrate CRUD and real-time data handling between Java and MySQL.

Features:
i) Add new customer order details
ii) Update order status or quantity
iii) Delete cancelled orders
iv) Display all orders

Uses MySQL Connector for database operations
Database Schema (MySQL):

CREATE DATABASE OnlineOrderDB;
USE OnlineOrderDB;
CREATE TABLE orders (
    order_id INT PRIMARY KEY,
    customer_name VARCHAR(50),
    product_name VARCHAR(50),
    quantity INT,
    price DECIMAL(10,2),
    order_date DATE
);


⚙️ Technologies Used
Language: Java (JDK 21 or above recommended)
Database: MySQL
Connector: mysql-connector-j-9.4.0.jar
IDE: Visual Studio Code / Eclipse / IntelliJ


🧩 How to Run
Install Java (JDK) and MySQL on your system.
Download and add the MySQL Connector JAR to your Java project’s classpath.
Create the respective databases and tables in MySQL using the above SQL scripts.
Update your MySQL credentials in the Java code (if required):

String url = "jdbc:mysql://localhost:3306/"order_database";
String user = "root";
String password = "Khushimysql123";


Compile and run your .java files:

javac JavaCRUDdemo.java
java JavaCRUDdemo
or
javac OnlineOrderManagement.java
java OnlineOrderManagement

🧠 Learning Outcomes
Establishing JDBC connection between Java and MySQL
Performing CRUD operations programmatically
Handling SQL exceptions and using Prepared Statements
Understanding database-driven Java applications


✨ Author
👩‍💻 Khushi Soni
📧 khushi2106soni@gmail.com
