# Wofy - Beauty Saloon and Spa Massage Center 💇‍♀️💆‍♂️

**Wofy** is a complete management system for beauty salons, spa centers, and massage parlors.  
It allows customers to browse available services & packages, make bookings, and leave reviews.  
Owners can manage their salon details, packages, shop photos, and view customer bookings.  
Admins have full control over the platform.

---

## ✨ Features

### For Customers:
- View city-wise salon listings.
- Browse salon details, packages, and offers.
- Book services online with date & time selection.
- Make payments in different modes.
- Submit ratings and reviews.

### For Salon Owners:
- Manage salon profile (name, email, location, timings, experience, etc.).
- Upload salon photos.
- Add and update service packages with pricing.
- View bookings and customer details.

### For Admin:
- Manage all salons, owners, and users.
- Oversee reviews and ratings.
- Ensure platform-wide smooth operation.

---

## 🛠 Tech Stack
- **Frontend:** Java Swing (or other UI framework you’re using)
- **Backend:** Java + JDBC
- **Database:** MySQL
- **Other Libraries:** Unirest for API calls (if applicable)

---

## 📦 Project Setup

### 1️⃣ Install MySQL
Make sure MySQL is installed and running.

### 2️⃣ Create Database
Open **MySQL Workbench** and run the following SQL script:

```sql
CREATE DATABASE saloonmanagement;
USE saloonmanagement;

-- Admin Table
CREATE TABLE admin (
    id INT PRIMARY KEY AUTO_INCREMENT,
    User VARCHAR(60),
    Password VARCHAR(80)
);

-- Cities Table
CREATE TABLE cities (
    cityid INT PRIMARY KEY AUTO_INCREMENT,
    cityname VARCHAR(45),
    citydesc VARCHAR(500),
    cityphotos VARCHAR(500)
);

-- Owner Table
CREATE TABLE owner (
    ownerid INT PRIMARY KEY AUTO_INCREMENT,
    ownername VARCHAR(45),
    owneremail VARCHAR(45),
    ownerpass VARCHAR(45),
    ownerphoto VARCHAR(500),
    shopphoto VARCHAR(500),
    shopname VARCHAR(45),
    shopdesc VARCHAR(500),
    cityid INT,
    latitude VARCHAR(100),
    longitude VARCHAR(100),
    starttime VARCHAR(45),
    endtime VARCHAR(45),
    experience VARCHAR(45),
    status VARCHAR(45),
    FOREIGN KEY (cityid) REFERENCES cities(cityid)
);

-- Shop Photos
CREATE TABLE shopphotos (
    photoid INT PRIMARY KEY AUTO_INCREMENT,
    ownerid INT,
    photo VARCHAR(500),
    FOREIGN KEY (ownerid) REFERENCES owner(ownerid)
);

-- Packages Table
CREATE TABLE packages (
    packageid INT PRIMARY KEY AUTO_INCREMENT,
    ownerid INT,
    packagename VARCHAR(100),
    packagedesc VARCHAR(1000),
    packagephoto VARCHAR(500),
    price VARCHAR(45),
    offerprice VARCHAR(45),
    type VARCHAR(45),
    FOREIGN KEY (ownerid) REFERENCES owner(ownerid)
);

-- Users Table
CREATE TABLE user (
    userid INT PRIMARY KEY AUTO_INCREMENT,
    uname VARCHAR(45),
    uemail VARCHAR(45),
    upassword VARCHAR(45),
    uphoto VARCHAR(500)
);

-- Booking Table
CREATE TABLE booking (
    bookingid INT PRIMARY KEY AUTO_INCREMENT,
    packageid INT,
    userid INT,
    username VARCHAR(45),
    useremail VARCHAR(45),
    bookingdate VARCHAR(45),
    bookingtime VARCHAR(45),
    modeofpayment VARCHAR(45),
    address VARCHAR(500),
    status VARCHAR(45),
    FOREIGN KEY (packageid) REFERENCES packages(packageid),
    FOREIGN KEY (userid) REFERENCES user(userid)
);

-- Review Table
CREATE TABLE review_table (
    review_id INT PRIMARY KEY AUTO_INCREMENT,
    rating INT,
    thingslike VARCHAR(500),
    summery VARCHAR(500),
    review VARCHAR(500),
    userid INT,
    ownerid INT,
    review_date VARCHAR(200),
    FOREIGN KEY (userid) REFERENCES user(userid),
    FOREIGN KEY (ownerid) REFERENCES owner(ownerid)
);
```

## 🚀 How to Run the Project

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/Saksham-Chanouria/Wofy---Saloon-Management.git
cd Wofy---Saloon-Management

```
### 2️⃣ Import Database
- Open **MySQL Workbench**.
- Paste and run the provided SQL script to create the **database** and **tables**.

### 3️⃣ Configure Database Connection
In your Java project, update the JDBC connection details:
```java
Connection conn = DriverManager.getConnection(
    "jdbc:mysql://127.0.0.1:3306/saloonmanagement", 
    "root", 
    "your_password_here"
);
```

### 4️⃣ Run the Project

**Using terminal:**
```bash
javac Main.java
java Main
```

### 5️⃣ Login & Explore

Use the admin, owner, or user accounts you created in MySQL to log in and explore the system.
