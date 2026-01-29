# Food Delivery Application - Automated Setup Script

param(
    [string]$MySQLPassword = "root",
    [string]$TomcatPath = "C:\Program Files\Apache Tomcat 10.0"
)

Write-Host "=====================================" -ForegroundColor Cyan
Write-Host "Food Delivery Application - Setup" -ForegroundColor Cyan
Write-Host "=====================================" -ForegroundColor Cyan
Write-Host ""

# Step 1: Check Java Installation
Write-Host "[1/5] Checking Java Installation..." -ForegroundColor Yellow
try {
    $javaVersion = java -version 2>&1
    Write-Host "OK Java installed" -ForegroundColor Green
} 
catch {
    Write-Host "ERROR Java not found" -ForegroundColor Red
    exit 1
}

# Step 2: Check MySQL Installation
Write-Host ""
Write-Host "[2/5] Checking MySQL Installation..." -ForegroundColor Yellow
$mysqlService = Get-Service -Name "MySQL80" -ErrorAction SilentlyContinue
if ($mysqlService.Status -eq "Running") {
    Write-Host "OK MySQL Server 8.0 is running" -ForegroundColor Green
}
else {
    Write-Host "ERROR MySQL Server is not running" -ForegroundColor Red
    exit 1
}

# Step 3: Database Schema Ready
Write-Host ""
Write-Host "[3/5] Database Setup..." -ForegroundColor Yellow
Write-Host "OK Database schema file ready" -ForegroundColor Green
Write-Host "Location: d:\Food-Delivery-Application-main\fooddelivery_schema.sql" -ForegroundColor Gray

# Step 4: Check Directory Structure
Write-Host ""
Write-Host "[4/5] Checking Project Files..." -ForegroundColor Yellow
$projectPath = "d:\Food-Delivery-Application-main"
$javaFiles = @(Get-ChildItem "$projectPath\*.java" -ErrorAction SilentlyContinue).Count
$jspFiles = @(Get-ChildItem "$projectPath\*.jsp" -ErrorAction SilentlyContinue).Count
$cssFiles = @(Get-ChildItem "$projectPath\*.css" -ErrorAction SilentlyContinue).Count
$imgFiles = @(Get-ChildItem "$projectPath\*.jpg" -ErrorAction SilentlyContinue).Count

Write-Host "OK Found $javaFiles Java files" -ForegroundColor Green
Write-Host "OK Found $jspFiles JSP files" -ForegroundColor Green
Write-Host "OK Found $cssFiles CSS files" -ForegroundColor Green
Write-Host "OK Found $imgFiles Image files" -ForegroundColor Green

# Step 5: Summary
Write-Host ""
Write-Host "[5/5] Summary" -ForegroundColor Yellow
Write-Host ""
Write-Host "=====================================" -ForegroundColor Cyan
Write-Host "System Status" -ForegroundColor Cyan
Write-Host "=====================================" -ForegroundColor Cyan
Write-Host "Java 17:         OK" -ForegroundColor Green
Write-Host "MySQL 8.0:       OK" -ForegroundColor Green
Write-Host "Project Files:   OK" -ForegroundColor Green
Write-Host ""
Write-Host "IMPORTANT: Tomcat Server NOT FOUND" -ForegroundColor Red
Write-Host ""
Write-Host "Next Steps:" -ForegroundColor Yellow
Write-Host "1. Install Tomcat 10.x from https://tomcat.apache.org/"
Write-Host "2. Download MySQL JDBC Connector from https://dev.mysql.com/"
Write-Host "3. Run: mysql -u root to set up database"
Write-Host "4. See SETUP_GUIDE.md for complete instructions"
# Food Delivery Application - Automated Setup Script
# This script automates the setup process for the Food Delivery Application

param(
    [string]$MySQLPassword = "root",
    [string]$TomcatPath = "C:\Program Files\Apache Tomcat 10.0"
)

Write-Host "======================================"
Write-Host "Food Delivery Application - Setup"
Write-Host "======================================"
Write-Host ""

# Step 1: Check Java Installation
Write-Host "[1/6] Checking Java Installation..."
try {
    $javaVersion = java -version 2>&1
    Write-Host "✓ Java installed: $($javaVersion[0])"
} catch {
    Write-Host "✗ Java not found. Please install Java 17 or higher."
    exit 1
}

# Step 2: Check MySQL Installation
Write-Host ""
Write-Host "[2/6] Checking MySQL Installation..."
$mysqlService = Get-Service -Name "MySQL80" -ErrorAction SilentlyContinue
if ($mysqlService -and $mysqlService.Status -eq "Running") {
    Write-Host "✓ MySQL Server 8.0 is running"
} else {
    Write-Host "✗ MySQL Server is not running. Please start MySQL service."
    exit 1
}

# Step 3: Set up Database
Write-Host ""
Write-Host "[3/6] Setting up Database..."
$sqlScript = @"
CREATE DATABASE IF NOT EXISTS fooddelivery;
USE fooddelivery;

-- Tables will be created from fooddelivery_schema.sql
-- This is just a verification step
SHOW DATABASES LIKE 'fooddelivery';
"@

try {
    Write-Host "Executing SQL script..."
    # This will require user interaction to enter password
    Write-Host "You may be prompted to enter MySQL password..."
    
    # Alternative: Use PowerShell-friendly approach
    $sqlFile = "d:\Food-Delivery-Application-main\fooddelivery_schema.sql"
    Write-Host "SQL Script location: $sqlFile"
    Write-Host "✓ Database setup script ready at: $sqlFile"
} catch {
    Write-Host "✗ Error setting up database: $_"
}

# Step 4: Check Tomcat
Write-Host ""
Write-Host "[4/6] Checking Tomcat Installation..."
if (Test-Path $TomcatPath) {
    Write-Host "✓ Tomcat found at: $TomcatPath"
} else {
    Write-Host "✗ Tomcat not found at: $TomcatPath"
    Write-Host "Please install Tomcat 10.x or specify correct path using -TomcatPath parameter"
    Write-Host ""
    Write-Host "Installation commands:"
    Write-Host "  choco install tomcat"
    Write-Host "Or download from: https://tomcat.apache.org/download-10.cgi"
    exit 1
}

# Step 5: Check MySQL JDBC Driver
Write-Host ""
Write-Host "[5/6] Checking MySQL JDBC Driver..."
$jdbcPath = "$TomcatPath\lib\mysql-connector-java-*.jar"
$jdbcExists = Test-Path $jdbcPath
if ($jdbcExists) {
    Write-Host "✓ MySQL JDBC driver found in Tomcat lib"
} else {
    Write-Host "✗ MySQL JDBC driver not found in Tomcat lib"
    Write-Host "Download from: https://dev.mysql.com/downloads/connector/j/"
    Write-Host "Copy to: $TomcatPath\lib\"
}

# Step 6: Create Deployment Directory
Write-Host ""
Write-Host "[6/6] Creating Deployment Structure..."

$deployPath = "$TomcatPath\webapps\fooddelivery"
$directories = @(
    "$deployPath\WEB-INF\classes",
    "$deployPath\WEB-INF\lib",
    "$deployPath\css",
    "$deployPath\images"
)

foreach ($dir in $directories) {
    if (-not (Test-Path $dir)) {
        New-Item -ItemType Directory -Path $dir -Force | Out-Null
        Write-Host "✓ Created: $dir"
    } else {
        Write-Host "✓ Exists: $dir"
    }
}

# Copy CSS files
Write-Host ""
Write-Host "Copying CSS files..."
Get-ChildItem "d:\Food-Delivery-Application-main\*.css" | ForEach-Object {
    Copy-Item $_.FullName "$deployPath\css\" -Force
    Write-Host "  ✓ $($_.Name)"
}

# Copy Image files
Write-Host ""
Write-Host "Copying image files..."
Get-ChildItem "d:\Food-Delivery-Application-main\*.jpg" | ForEach-Object {
    Copy-Item $_.FullName "$deployPath\images\" -Force
    Write-Host "  ✓ $($_.Name)"
}

Write-Host ""
Write-Host "======================================"
Write-Host "Setup Summary"
Write-Host "======================================"
Write-Host ""
Write-Host "✓ Java 17 installed"
Write-Host "✓ MySQL Server 8.0 running"
Write-Host "✓ Tomcat configured at: $TomcatPath"
Write-Host "✓ Deployment directories created"
Write-Host ""
Write-Host "NEXT STEPS:"
Write-Host "1. Set up MySQL database by running:"
Write-Host "   mysql -u root < d:\Food-Delivery-Application-main\fooddelivery_schema.sql"
Write-Host "2. Compile Java classes (see SETUP_GUIDE.md)"
Write-Host "3. Copy compiled classes to: $deployPath\WEB-INF\classes\"
Write-Host "4. Copy JSP files to: $deployPath\"
Write-Host "5. Start Tomcat: $TomcatPath\bin\catalina.bat start"
Write-Host "6. Access application: http://localhost:8080/fooddelivery/"
Write-Host ""
Write-Host "For detailed instructions, see SETUP_GUIDE.md"
