

# README for Inventory Management System (Java + JavaFX + MySQL)

## Project Overview

This project is a comprehensive Inventory Management System developed in **Java** using **JavaFX** for the graphical user interface and **MySQL** as the database backend. It manages products, suppliers, stock levels, purchase orders, and sales transactions. The system enables smooth tracking and organization of goods from suppliers to customers while maintaining real-time stock updates.

---

## Features

* **Product Management**: Add, update, and delete products with details such as name, category, price, and manufacturer.
* **Supplier Management**: Manage supplier data including contact information and product types supplied.
* **Inventory Control**: Track stock quantities with updates upon purchase or sale.
* **Order Processing**:

  * Create and manage purchase orders to suppliers.
  * Create and manage sales orders from customers.
* **Order Details**: Manage order line items including quantity and price.
* **Real-time Stock Updates**: Inventory quantities update automatically when orders are processed.
* **User-friendly GUI**: Built with JavaFX for an intuitive and responsive interface.
* **Database Integration**: Uses MySQL to persist data securely and reliably.

---

## Technologies Used

* **Java** (JDK 11 or higher recommended)
* **JavaFX** (for UI development)
* **MySQL** (Database Server)
* **JDBC** (Java Database Connectivity for MySQL)
* **Maven/Gradle** (optional for build management)

---

## Database Schema Overview

The system uses the following tables (as per your SQL dump):

* `customer`: Stores customer information.
* `product`: Stores product details.
* `supplier`: Stores supplier details.
* `inventory`: Tracks product quantities and update history.
* `order_request`: Stores orders placed by customers.
* `order_detail`: Stores details of each product in an order.

Foreign keys enforce referential integrity among orders, products, customers, and inventory.

---

## Installation and Setup

### Prerequisites

* Java JDK 11+ installed
* MySQL server installed and running
* MySQL Workbench or any MySQL client (optional, for database management)
* Maven or Gradle (optional)

### Steps

1. **Clone the repository**

   ```bash
  git clone https://github.com/mohammednnimer/Storage-management-system-.git
cd Storage-management-system-
   ```

2. **Create the MySQL Database**

   * Log into MySQL:

     ```bash
     mysql -u yourusername -p
     ```
   * Create the database:

     ```sql
     CREATE DATABASE databease;
     USE databease;
     ```
   * Run the provided SQL dump script to create tables and constraints.

3. **Configure Database Connection**

   * In your Java project, configure the JDBC connection with the MySQL database URL, username, and password.
   * Example JDBC URL:

     ```java
     String url = "jdbc:mysql://localhost:3306/databease";
     String user = "yourusername";
     String password = "yourpassword";
     ```

4. **Build and Run the Application**

   * Using your IDE (IntelliJ IDEA, Eclipse, NetBeans) or command line, build the project.
   * Run the JavaFX main class to launch the UI.

---

## Usage

* **Add New Products & Suppliers** from the UI.
* **Place Orders** for purchase or sale.
* **View and Update Inventory** quantities.
* **Generate Reports** (if implemented) for stock and sales.

---

## Project Structure



---

## Future Improvements

* User authentication and role-based access.
* Reporting and analytics dashboard.
* Barcode scanning integration.
* Automated email notifications for low stock.
* Export/import data functionality (CSV, Excel).

---

## License

Specify your license here, e.g. MIT, GPL, etc.

---

## Contact

For questions or support, contact:
**Your Name**
Email: [your.email@example.com](mailto:your.email@example.com)
