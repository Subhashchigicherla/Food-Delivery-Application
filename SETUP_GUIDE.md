# Food Delivery Application - Complete Setup Guide

## System Requirements
- ✅ Java 17 (Already Installed)
- ✅ MySQL 8.0 (Already Installed and Running)
- ❌ Tomcat 10.x (Need to Install)
- MySQL JDBC Driver (Need to Add)

---

## Step 1: Set Up MySQL Database

### Current Status
- MySQL Server 8.0 is running
- Database needs to be created with sample data

### Steps to Execute
1. **Open MySQL Command Prompt:**
   ```bash
   cd "C:\Program Files\MySQL\MySQL Server 8.0\bin"
   mysql -u root
   ```

2. **Run the SQL Script:**
   ```sql
   source d:\Food-Delivery-Application-main\fooddelivery_schema.sql;
   ```

3. **Verify Database Creation:**
   ```sql
   USE fooddelivery;
   SHOW TABLES;
   ```

---

## Step 2: Install Apache Tomcat 10.x

### Option A: Manual Installation
1. Download from: https://tomcat.apache.org/download-10.cgi
2. Extract to: `C:\Program Files\Apache Tomcat 10.x`
3. Set CATALINA_HOME: `C:\Program Files\Apache Tomcat 10.x`

### Option B: Using Chocolatey
```bash
choco install tomcat -y
```

---

## Step 3: Add MySQL JDBC Driver

1. Download: https://dev.mysql.com/downloads/connector/j/
2. Extract mysql-connector-java-8.x.x.jar
3. Copy to:
   - **For Tomcat:** `C:\Program Files\Apache Tomcat 10.x\lib\mysql-connector-java-8.x.x.jar`

---

## Step 4: Project Structure Setup

### Create Project Directory Structure
```
C:\Program Files\Apache Tomcat 10.x\webapps\fooddelivery\
├── WEB-INF/
│   ├── classes/
│   │   └── com/tap/
│   │       ├── model/
│   │       ├── dao/
│   │       ├── servlet/
│   │       └── utility/
│   └── lib/
│       └── mysql-connector-java-8.x.x.jar
├── META-INF/
│   └── context.xml
├── css/
├── images/
└── jsp/
```

---

## Step 5: Compile Java Classes

### Compile All Java Files
```bash
cd d:\Food-Delivery-Application-main

# Create output directory
mkdir compiled_classes

# Compile all Java files
javac -d compiled_classes -cp "C:\Program Files\Apache Tomcat 10.x\lib\*" *.java

# Copy MySQL JDBC driver
copy "C:\Program Files\Apache Tomcat 10.x\lib\mysql-connector-java-8.x.x.jar" compiled_classes\
```

---

## Step 6: Deploy to Tomcat

### Copy Files to Tomcat
1. **Copy Compiled Classes:**
   ```
   Copy: D:\Food-Delivery-Application-main\compiled_classes\com\
   To:   C:\Program Files\Apache Tomcat 10.x\webapps\fooddelivery\WEB-INF\classes\com\
   ```

2. **Copy JSP Files:**
   ```
   Copy all *.jsp files
   To:   C:\Program Files\Apache Tomcat 10.x\webapps\fooddelivery\
   ```

3. **Copy CSS Files:**
   ```
   Copy all *.css files
   To:   C:\Program Files\Apache Tomcat 10.x\webapps\fooddelivery\css\
   ```

4. **Copy Image Files:**
   ```
   Copy all *.jpg files
   To:   C:\Program Files\Apache Tomcat 10.x\webapps\fooddelivery\images\
   ```

5. **Create web.xml:**
   ```
   To:   C:\Program Files\Apache Tomcat 10.x\webapps\fooddelivery\WEB-INF\web.xml
   ```

---

## Step 7: Start Tomcat

```bash
# Windows Command Prompt
C:\Program Files\Apache Tomcat 10.x\bin\catalina.bat start

# OR for debugging
C:\Program Files\Apache Tomcat 10.x\bin\catalina.bat jpda start
```

---

## Step 8: Access the Application

Open your browser and visit:
- **Home Page:** http://localhost:8080/fooddelivery/
- **Login:** http://localhost:8080/fooddelivery/LoginServlet
- **Sign Up:** http://localhost:8080/fooddelivery/SignUp.jsp

---

## Database Credentials (from DBConnection.java)
```
URL: jdbc:mysql://localhost:3306/fooddelivery
Username: root
Password: Root
Driver: com.mysql.cj.jdbc.Driver
```

---

## Sample Test Users (from database)
- **Customer 1:** username: `johndoe`, password: `password123`
- **Customer 2:** username: `janesmith`, password: `password456`
- **Admin:** username: `admin`, password: `admin123`

---

## Troubleshooting

### Connection Issues
- Check if MySQL service is running: `Get-Service MySQL80`
- Check if Tomcat is running: `Get-Process java`
- Check Tomcat logs: `C:\Program Files\Apache Tomcat 10.x\logs\catalina.out`

### Database Connection Failed
- Verify MySQL JDBC jar is in: `$CATALINA_HOME/lib/`
- Check database credentials in DBConnection.java
- Ensure fooddelivery database exists

### Port Conflicts
- Default: Tomcat runs on port 8080
- If in use, change in: `C:\Program Files\Apache Tomcat 10.x\conf\server.xml`

---

## Next Steps
1. Install Tomcat
2. Add MySQL JDBC driver
3. Follow the deployment steps above
4. Start Tomcat and access the application
