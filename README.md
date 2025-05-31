
````markdown
# Inventory Management System (Java + JavaFX + MySQL)

## 📦 Project Overview

A comprehensive Inventory Management System built using **Java**, **JavaFX**, and **MySQL**. The system streamlines inventory operations including product management, supplier tracking, order processing (both sales and purchases), and real-time stock updates.

---

## ✅ Features

- **Product Management**: Add, update, and delete products with details like name, category, price, and manufacturer.
- **Supplier Management**: Store and manage supplier contact information and product types.
- **Inventory Control**: Automatically update stock quantities on purchase and sale.
- **Order Handling**:
  - Create purchase orders to suppliers.
  - Manage customer sales orders.
- **Order Details**: Maintain quantity and price per item within each order.
- **Real-Time Updates**: Sync inventory levels with every order processed.
- **Modern GUI**: JavaFX-based responsive and user-friendly interface.
- **Robust Database Integration**: Persistent storage with MySQL and JDBC.

---

## 🛠️ Technologies Used

- **Java** (JDK 11 or later)
- **JavaFX**
- **MySQL** (MariaDB compatible)
- **JDBC**
- **Maven / Gradle** (optional for dependency and build management)

---

## 🗃️ Database Schema Overview

Tables used in the system:

- `customer`: Customer data.
- `product`: Product catalog.
- `supplier`: Supplier info.
- `inventory`: Stock level tracking.
- `order_request`: Sales orders from customers.
- `order_detail`: Line items for each order.

Relational integrity is enforced using foreign key constraints.

---

## 🚀 Installation & Setup

### Prerequisites

- Java JDK 11+
- MySQL Server installed and running
- MySQL Workbench (optional)
- IDE like IntelliJ IDEA, Eclipse, or NetBeans

---

### 1. Clone the repository

```bash
git clone https://github.com/mohammednnimer/Storage-management-system-.git
cd Storage-management-system-
````

---

### 2. Create the MySQL Database

Log into your MySQL client:

```bash
mysql -u yourusername -p
```

Create the database and import tables:

```sql
CREATE DATABASE databease;
USE databease;
-- Then run the SQL dump provided in the project
```

---

### 3. Configure Database Connection

Update your JDBC connection in the Java code:

```java
String url = "jdbc:mysql://localhost:3306/databease";
String user = "yourusername";
String password = "yourpassword";
```

---

### 4. Build and Run the Application

* Open the project in your preferred IDE.
* Build the project.
* Run the main JavaFX class to start the app.

---

## 🧑‍💻 Usage

* Add new products and suppliers.
* Place customer orders and supplier purchases.
* Track and update inventory in real time.
* Optionally implement reporting modules.


---


---




---

```

هل ترغب أن أجهز لك نسخة بصيغة `README.md` قابلة للتحميل مباشرة؟
