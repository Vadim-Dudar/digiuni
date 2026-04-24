# DigiUni - University Manager

A comprehensive console-based Java application tailored for managing university entities. Built as an advanced university management system, it provides role-based access control and an intuitive command-line interface.

## 👥 Authors
* **Dudar Vadim** & **Demkiv Max**

## 🚀 Key Features

* **Role-Based Access Control (RBAC):** Supports different user roles (`TECH_ADMIN`, `ADMIN`, `EXPLORER`) determining the allowed operations.
* **Complex Data Management:** 
  * University, Faculties, Departments
  * Teachers (Positions, Degrees)
  * Students (Courses, Groups, Study Forms, Statuses)
* **Interactive CLI:** Deeply nested command-line menus for viewing, adding, editing, and deleting records.
* **Reports & Sorting:** Powerful data querying, filtering, grouping, and sorting utilizing Java Stream API.
* **Data Persistence:** Seamlessly loads and saves university state using Java Object Serialization.
* **Background Auto-Save:** Implements multithreading with a background daemon thread that automatically persists data every minute to avoid data loss.
* **Custom Annotations & Validation:** Employs Reflection and custom annotations (e.g., `@ValidPhone`, `@NotNullOrEmpty`) for strict data validation (like validating phone number formats).

## 🛠️ Technology Stack
* **Java 17+** (Stream API, Collections, IO, Multithreading, Reflection)
* **Lombok** (Boilerplate reduction)
* **SLF4J & Logback** (Logging application events)
* **Maven** (Dependency management and build lifecycle)

## 🎮 How to Run

1. Make sure you have Java and Maven installed.
2. Build the project using Maven:
   ```bash
   mvn clean install
   ```
3. Run the compiled `Main` class:
   ```bash
   mvn exec:java -Dexec.mainClass="edu.naukma.Main"
   ```
4. Follow the interactive console menu. Use the built-in users to log in (e.g., `admin` / `1234` or `super` / `1234`).

## 📁 Storage
All university structural data is persistently saved out-of-the-box into a local `university_data.ser` file through object serialization. Application logs are written to the `logs/app.log` file based on the configured logback format.

