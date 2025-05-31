# Todo Manager - Spring Boot Application

A comprehensive todo management application built with Spring Boot, featuring user authentication, CRUD operations, and a clean web interface.

## Features

- **User Authentication**: Secure registration and login system
- **Personal Todo Management**: Create, read, update, and delete todos
- **User Isolation**: Each user can only access their own todos
- **Responsive Web Interface**: Clean UI built with Thymeleaf templates
- **Session Management**: Secure session-based authentication
- **MySQL Integration**: Robust database persistence

## Technology Stack

- **Backend Framework**: Spring Boot 3.4.5
- **Database**: MySQL 8.0+
- **Template Engine**: Thymeleaf
- **Build Tool**: Maven 3.6+
- **Java Version**: 17 LTS
- **ORM**: Spring Data JPA with Hibernate

## Prerequisites

### Required Software

1. **Java Development Kit (JDK) 17 or higher**
   - Download from [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://openjdk.org/)
   - Verify installation: `java -version`

2. **MySQL 8.0 or higher**
   - Download from [MySQL Official Site](https://dev.mysql.com/downloads/mysql/)
   - Alternative: Use [XAMPP](https://www.apachefriends.org/) for easy MySQL setup

3. **Maven 3.6+ (Choose one option)**

### Maven Installation Options

#### Option 1: IntelliJ IDEA (Recommended for Beginners)
IntelliJ IDEA comes with Maven pre-installed and configured. This is the easiest option for development.

**Download IntelliJ IDEA:**
- **Community Edition** (Free): [Download here](https://www.jetbrains.com/idea/download/)
- **Ultimate Edition** (Paid): Includes additional enterprise features

**Benefits of IntelliJ IDEA:**
- Built-in Maven support (no separate installation needed)
- Excellent Spring Boot integration
- Smart code completion and debugging
- Integrated terminal and version control
- Database tools for MySQL management

#### Option 2: Standalone Maven Installation

**For Windows:**
1. Download Maven from [Apache Maven](https://maven.apache.org/download.cgi)
2. Extract to `C:\Program Files\Apache\maven`
3. Add Maven to System PATH:
   - Open System Properties → Environment Variables
   - Add `C:\Program Files\Apache\maven\bin` to PATH
   - Create `MAVEN_HOME` variable pointing to `C:\Program Files\Apache\maven`
4. Verify installation: `mvn -version`

**For macOS:**
```bash
# Using Homebrew
brew install maven

# Verify installation
mvn -version
```

**For Linux (Ubuntu/Debian):**
```bash
# Install Maven
sudo apt update
sudo apt install maven

# Verify installation
mvn -version
```

## Database Setup

### 1. Install and Configure MySQL

**Option A: Standalone MySQL**
1. Download and install MySQL 8.0+ from [MySQL Downloads](https://dev.mysql.com/downloads/mysql/)
2. During installation, set a root password (remember this!)
3. Start MySQL service

**Option B: Using XAMPP (Easier for beginners)**
1. Download [XAMPP](https://www.apachefriends.org/)
2. Install and start Apache and MySQL services
3. Default credentials: username=`root`, password=`(empty)`

### 2. Create Database
Connect to MySQL and create the required database:

```sql
-- Connect to MySQL (use your MySQL client or command line)
mysql -u root -p

-- Create database
CREATE DATABASE demo;

-- Optional: Create dedicated user
CREATE USER 'todouser'@'localhost' IDENTIFIED BY 'todopass123';
GRANT ALL PRIVILEGES ON demo.* TO 'todouser'@'localhost';
FLUSH PRIVILEGES;

-- Exit MySQL
exit;
```

### 3. Database Tables (Auto-created by Spring Boot)

The application uses **Spring Data JPA** with `hibernate.ddl-auto=update`, which means the tables will be **automatically created** when you first run the application. Here's what tables will be generated:

#### **User Table**
```sql
CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);
```

#### **Todo Table**
```sql
CREATE TABLE todo (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    completed BOOLEAN DEFAULT FALSE,
    created_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE
);
```

> **Note**: You **don't need to create these tables manually**. Spring Boot JPA will automatically create them when the application starts for the first time. The `hibernate.ddl-auto=update` setting ensures that:
> - Tables are created if they don't exist
> - Existing data is preserved when you restart the application
> - Schema changes are applied automatically

## Project Setup

### Method 1: Using IntelliJ IDEA (Recommended)

1. **Clone/Download Project**
   ```bash
   git clone <your-repository-url>
   ```

2. **Open in IntelliJ IDEA**
   - Open IntelliJ IDEA
   - Click "Open" and select the project folder
   - IntelliJ will automatically detect it as a Maven project
   - Wait for Maven to download dependencies (may take a few minutes)

3. **Configure Database**
   - Copy `application.properties.template` to `src/main/resources/application.properties`
   - Edit the file with your database credentials:
   ```properties
   spring.application.name=todospringboot
   spring.datasource.url=jdbc:mysql://localhost:3306/demo
   spring.datasource.username=root
   spring.datasource.password=your_mysql_password
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
   server.port=8080
   ```

4. **Run Application**
   - In IntelliJ: Right-click on `TodospringbootApplication.java` → Run
   - Or use the green play button in the toolbar
   - Or open terminal in IntelliJ and run: `mvn spring-boot:run`

### Method 2: Command Line (Using standalone Maven)

1. **Clone Project**
   ```bash
   git clone <your-repository-url>
   cd todospringboot
   ```

2. **Configure Database**
   ```bash
   # Copy configuration template
   copy application.properties.template src\main\resources\application.properties
   
   # Edit src\main\resources\application.properties with your database settings
   ```

3. **Build Project**
   ```bash
   mvn clean install
   ```

4. **Run Application**
   ```bash
   mvn spring-boot:run
   ```

   Or run the JAR file:
   ```bash
   java -jar target/todospringboot-0.0.1-SNAPSHOT.jar
   ```

## Application Access

1. **Start the application** using one of the methods above
2. **Open your web browser** and navigate to: `http://localhost:8080`
3. **First-time setup:**
   - You'll be redirected to the login page
   - Click "Register" to create a new account
   - Fill in username, email, and password
   - After registration, login with your credentials

## Usage Guide

### User Management
- **Registration**: Create new user accounts
- **Login/Logout**: Secure session management
- **User Isolation**: Each user only sees their own todos

### Todo Operations
- **Add Todo**: Use the form on the main page to create new todos
- **View Todos**: All your todos are displayed on the dashboard
- **Edit Todo**: Click the "Edit" button next to any todo
- **Delete Todo**: Click the "Delete" button to remove todos
- **Mark Complete**: Toggle completion status

## Project Structure

```
todospringboot/
├── src/main/java/com/myprojects/todospringboot/
│   ├── TodospringbootApplication.java    # Main Spring Boot application
│   ├── controller/                       # Web layer
│   │   ├── TodoController.java          # Todo CRUD endpoints
│   │   └── UserController.java          # Authentication endpoints
│   ├── model/                           # Entity classes
│   │   ├── Todo.java                    # Todo entity
│   │   └── User.java                    # User entity
│   ├── repository/                      # Data access layer
│   │   ├── TodoRepository.java          # Todo database operations
│   │   └── UserRepository.java          # User database operations
│   └── services/                        # Business logic layer
│       ├── TodoService.java             # Todo business logic
│       └── UserService.java             # User business logic
├── src/main/resources/
│   ├── application.properties           # Configuration file
│   └── templates/                       # Thymeleaf templates
│       ├── login.html                   # Login page
│       ├── register.html                # Registration page
│       ├── todos.html                   # Main todo dashboard
│       └── edit.html                    # Edit todo page
├── application.properties.template      # Configuration template
├── pom.xml                              # Maven dependencies
└── README.md                            # This file
```

## API Endpoints

| HTTP Method | Endpoint | Description | Access |
|-------------|----------|-------------|--------|
| GET | `/` | Redirect to login | Public |
| GET | `/login` | Login page | Public |
| POST | `/login` | Process login | Public |
| GET | `/register` | Registration page | Public |
| POST | `/register` | Process registration | Public |
| GET | `/todos` | View user's todos | Authenticated |
| POST | `/todos` | Create new todo | Authenticated |
| GET | `/todos/edit/{id}` | Edit todo page | Authenticated |
| POST | `/todos/update` | Update existing todo | Authenticated |
| POST | `/todos/delete/{id}` | Delete todo | Authenticated |
| POST | `/logout` | Logout user | Authenticated |

## Troubleshooting

### Common Issues and Solutions

1. **"Port 8080 already in use"**
   - Change port in `application.properties`: `server.port=8081`
   - Or kill the process using port 8080

2. **Database Connection Failed**
   - Verify MySQL is running
   - Check database credentials in `application.properties`
   - Ensure database `demo` exists
   - Test connection: `mysql -u root -p`

3. **Maven Dependencies Not Downloading**
   - Check internet connection
   - In IntelliJ: File → Invalidate Caches and Restart
   - Command line: `mvn clean install -U`

4. **"JAVA_HOME not set" Error**
   - Set JAVA_HOME environment variable to JDK installation path
   - Restart your IDE/terminal

5. **Application Won't Start**
   - Check application logs in console
   - Verify Java version: `java -version`
   - Ensure no syntax errors in `application.properties`

### IntelliJ IDEA Specific Tips

- **Enable auto-import**: File → Settings → Build → Build Tools → Maven → Import Maven projects automatically
- **Refresh Maven**: Click the Maven refresh icon in the Maven tool window
- **Database connection**: Use Database tool window to connect to MySQL
- **Run configurations**: Create custom run configurations for different profiles

## Development

### Adding New Features
1. Create feature branch: `git checkout -b feature/new-feature`
2. Make changes
3. Test thoroughly
4. Commit: `git commit -m "Add new feature"`
5. Push: `git push origin feature/new-feature`

### Testing
```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=TodoControllerTest
```

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Support

If you encounter any issues:
1. Check this README for troubleshooting steps
2. Review application logs
3. Create an issue in the GitHub repository
4. Include error messages and system information

---

**Happy Coding! 🚀**