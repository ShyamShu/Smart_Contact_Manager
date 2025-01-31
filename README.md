Smart Contact Manager

# 📌 Overview

Smart Contact Manager is a web-based application designed to securely store, manage, and organize contacts. Built using Spring Boot for the backend and Thymeleaf for the frontend, it provides users with an intuitive interface to add, edit, delete, and search contacts effortlessly.

# 🎯 Features

🔐 User Authentication & Authorization (OAuth-based Login)

📇 Add, Edit, Delete Contacts

🔍 Search Contacts

📁 Upload Profile Pictures for Contacts

📜 Responsive UI for both Desktop & Mobile

📊 Dashboard with Contact Statistics

📂 Secure Data Storage

# 🛠️ Technology Stack

## Frontend:

Thymeleaf (Templating Engine)

Tailwind CSS / CSS (Styling)

## Backend:

Spring Boot (REST API)

Spring Security (OAuth Authentication & Authorization)

Hibernate & JPA (Database ORM)

MySQL/PostgreSQL (Database)

## Other Tools & Libraries:

OAuth2 for secure authentication

Lombok for reducing boilerplate code

Swagger for API documentation

Postman for API testing

# 📌 Installation & Setup

Prerequisites:

## Make sure you have the following installed:

Java 17+ (JDK)

MySQL/PostgreSQL Database

Postman (Optional, for API testing)

## Backend Setup (Spring Boot & Thymeleaf)

### Clone the repository:

git clone https://github.com/your-username/smart-contact-manager.git
cd smart-contact-manager/backend

## Configure the application.properties file:

spring.datasource.url=jdbc:mysql://localhost:3306/contact_manager
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.security.oauth2.client.registration.google.client-id=your_google_client_id
spring.security.oauth2.client.registration.google.client-secret=your_google_client_secret

## Build and run the application:

mvn clean install
mvn spring-boot:run

# 💻 Hardware & Software Requirements

## Minimum Hardware Requirements:

🖥️ Processor: Intel Core i3 or equivalent

🏗️ RAM: 4GB (8GB recommended for smooth performance)

💾 Storage: Minimum 500MB free space

## Software Requirements:

🏗️ Operating System: Windows/Linux/macOS

📜 Database: MySQL/PostgreSQL

🔥 Backend: Java 17+, Spring Boot

🎨 Frontend: Thymeleaf, Tailwind CSS / CSS

# 🚀 Use Cases

📞 Personal Contact Management: Store and retrieve contacts easily.

🏢 Business Directory: Small businesses can maintain customer contact records.

🏫 Educational Use: Teachers and students can store classmate and faculty contacts.

🏥 Healthcare: Doctors and medical staff can manage patient contact details.

# 🔐 Security Measures

🛡️ OAuth-based Authentication

🔒 Password Encryption (BCrypt)

🗄️ Role-Based Access Control (RBAC)

🏗️ Input Validation to prevent SQL Injection & XS

# 🤝 Contributing

Want to contribute? Follow these steps:

## Fork the repository.

Create a feature branch (git checkout -b feature-branch).

Commit your changes (git commit -m 'Added new feature').

Push to the branch (git push origin feature-branch).

Open a Pull Request.

# 📞 Contact

Developer: Shyam Shukla

Email: shyamshukla.cs25@gmail.com

Phone: 8960558313

GitHub: Shyam Shukla

# ⚖️ License

This project is licensed under the MIT License. You are free to modify and distribute it as per the license terms.
