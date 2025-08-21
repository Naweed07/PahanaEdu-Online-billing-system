# 📚 PahaanEdu - Book Inventory & Billing System

PahaanEdu is a simple Book Inventory and Billing Management System designed to manage books, customer records, and billing. It allows administrators to maintain inventory, add customers, and generate bills seamlessly.

---

## 🚀 Features

- 🔐 **User Authentication** – Login & Register  
- 📖 **Manage Books** – Add, update, delete, and view book inventory  
- 👤 **Manage Customers** – Add and maintain customer details  
- 💵 **Billing System** – Generate bill by entering account number, customer name, and number of books purchased  
- 📊 **Automatic Calculation** – Fetches book price and calculates total bill  
- 🖥️ **User-Friendly Interface**  

---

## 🛠️ Tech Stack

- **Frontend:** HTML, CSS, JavaScript  
- **Backend:** Java 
- **Database:** MySQL  
- **Build Tool:** Maven / Gradle  

---

## 📂 Project Structure

PahaanEdu/
│── src/ # Source code
│ ├── com.pahanaedu/ # Main packages
│ └── resources/ # Config & properties
│── web/ # Frontend (JSP/HTML/CSS/JS)
│── pom.xml # Maven build file
│── README.md # Project documentation


---

## ⚙️ Installation & Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/pahanaedu.git
   cd pahanaedu
2.Configure Database

Create a MySQL database:

CREATE DATABASE pahanaedu;


3.Import the provided SQL file (if available).

Update DB credentials inside:

src/com/pahanaedu/config/DBConnection.java


4.Run the Project

If using Maven:

mvn clean install


5.Deploy on Apache Tomcat (or your chosen server).
