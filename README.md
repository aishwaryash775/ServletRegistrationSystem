# ServletRegistrationSystem
# 📝 Registration Management System (Java Servlet + MySQL)

This is a **Java Servlet-based web application** built using **NetBeans, Apache Tomcat, and MySQL**.  
It allows users to **register**, and admins can **view, edit, or delete** user records from the database.

---

## 🚀 Features
- ✅ User Registration with validation  
- ✅ Store data into MySQL (`mudb.users` table)  
- ✅ View all registered users in a table format  
- ✅ **Edit** user details  
- ✅ **Delete** user records with confirmation  
- ✅ Success and error alerts for better UX  

---

## 🛠️ Tech Stack
- **Frontend:** HTML, CSS  
- **Backend:** Java Servlets (NetBeans)  
- **Database:** MySQL (via XAMPP or standalone)  
- **Server:** Apache Tomcat  

---

## 📂 Project Structure
ServletRegistrationSystem/
├── src/
│ └── servlets/
│ ├── mu.java # Handles registration & inserts data
│ ├── ViewUsers.java # Displays stored data
│ ├── DeleteUser.java # Deletes a user
│ └── EditUser.java # Updates user details
│
├── web/
│ ├── index.html # Registration form
│ ├── success.html # Success page with link to View Users
│ ├── mu.css # Stylesheet
│
└── screenshot/ # Project screenshots
├── registration.png
├── registrationsuccessfull.png
├── viewuserstable.png
└── editusers.png

## ⚙️ Setup Instructions

Follow these steps to run the project:

1. **Clone the repository**
   ```bash
   git clone [https://github.com/aishwaryash775/ServletRegistrationSystem.git](https://github.com/aishwaryash775/ServletRegistrationSystem.git)
Open in NetBeans

File → Open Project → Select ServletRegistrationSystem

Setup MySQL Database

CREATE DATABASE mudb;
USE mudb;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    relation_type VARCHAR(20),
    mother_name VARCHAR(50),
    father_husband_name VARCHAR(50),
    dob DATE,
    gender VARCHAR(10),
    mobile_number VARCHAR(15),
    confirm_mobile_number VARCHAR(15),
    email VARCHAR(100)
);
Update DB config in servlets if needed

java
Copy code
String dbUrl = "jdbc:mysql://localhost:3306/mudb?useSSL=false&serverTimezone=UTC";
String dbUser = "root";
String dbPass = "";
Deploy to Tomcat

Right-click the project → Run


🎯 Usage
Open index.html → Fill in the registration form.

After successful registration → Redirects to success page.

Go to View Users to see all registered users.

Use Edit or Delete in the Action column to manage records.

📸 Screenshots
## 📸 screenshot

### 📝 Registration Page
![Registration Form](screenshot/registration.png)

### ✅ Registration Successful
![Success Page](screenshot/registration.png)

### 📊 View Registered Users
![Users Table](screenshot/viewuserstable.png)

### ✏️ Edit User
![Edit User Form](screenshot/editusers.png)

✏️ Edit User

🤝 Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

📜 License
This project is licensed under the MIT License – free to use and modify.



