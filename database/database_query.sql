drop database if exists javafinal;

create database javafinal;
use javafinal;

-- Table for User Accounts
CREATE TABLE Users
(
    UserID       INT PRIMARY KEY AUTO_INCREMENT,
    UserName     VARCHAR(255)                  NOT NULL UNIQUE,
    Email        VARCHAR(255)                  NOT NULL UNIQUE,
    PasswordHash VARCHAR(255)                  NOT NULL,
    UserType     ENUM ('admin', 'salesperson') NOT NULL,
    IsActive     BOOLEAN   DEFAULT TRUE,
    IsLocked     BOOLEAN   DEFAULT FALSE,
    CreatedAt    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt  TIMESTAMP    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LoginLink  varchar(255) DEFAULT NULL,
    ExpiryTime TIMESTAMP    DEFAULT CURRENT_TIMESTAMP
);

-- Table for Employee Profiles
CREATE TABLE EmployeeProfiles
(
    UserID            INT PRIMARY KEY,
    FullName          VARCHAR(255) NOT NULL,
    ProfilePictureURL VARCHAR(255),
    FOREIGN KEY (UserID) REFERENCES Users (UserID)
);

-- Table for Products
CREATE TABLE Products
(
    ProductID   INT PRIMARY KEY AUTO_INCREMENT,
    Barcode     VARCHAR(255)   NOT NULL UNIQUE,
    ProductName VARCHAR(255)   NOT NULL,
    ImportPrice DECIMAL(10, 2) NOT NULL,
    RetailPrice DECIMAL(10, 2) NOT NULL,
    Category    VARCHAR(255)   NOT NULL,
    CreatedAt   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_positive_prices CHECK (ImportPrice >= 0 AND RetailPrice >= 0)
);

-- Table for Customers
CREATE TABLE Customers
(
    CustomerID INT PRIMARY KEY AUTO_INCREMENT,
    Phone      VARCHAR(20)  NOT NULL UNIQUE,
    FullName   VARCHAR(255) NOT NULL,
    Address    VARCHAR(255),
    CreatedAt  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Table for Orders
CREATE TABLE Orders
(
    OrderID        INT PRIMARY KEY AUTO_INCREMENT,
    UserID         INT            NOT NULL,
    CustomerID     INT,
    OrderDate      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    TotalAmount    DECIMAL(10, 2) NOT NULL,
    AmountReceived DECIMAL(10, 2) NOT NULL,
    CONSTRAINT fk_user_order FOREIGN KEY (UserID) REFERENCES Users (UserID),
    CONSTRAINT fk_customer_order FOREIGN KEY (CustomerID) REFERENCES Customers (CustomerID)
);

-- Table for OrderDetails
CREATE TABLE OrderDetails
(
    OrderDetailID INT PRIMARY KEY AUTO_INCREMENT,
    OrderID       INT            NOT NULL,
    ProductID     INT            NOT NULL,
    Quantity      INT            NOT NULL,
    UnitPrice     DECIMAL(10, 2) NOT NULL,
    TotalAmount   DECIMAL(10, 2) NOT NULL,
    CONSTRAINT fk_order_detail FOREIGN KEY (OrderID) REFERENCES Orders (OrderID),
    CONSTRAINT fk_product_detail FOREIGN KEY (ProductID) REFERENCES Products (ProductID),
    CONSTRAINT chk_positive_quantity CHECK (Quantity > 0),
    CONSTRAINT chk_positive_unit_price CHECK (UnitPrice >= 0),
    CONSTRAINT chk_positive_total_amount CHECK (TotalAmount >= 0)
);

INSERT INTO Users (UserName, Email, PasswordHash, UserType)
VALUES ('admin', 'admin@gmail.com',
        'c7ad44cbad762a5da0a452f9e854fdc1e0e7a52a38015f23f3eab1d80b931dd472634dfac71cd34ebc35d16ab7fb8a90c81f975113d6c7538dc69dd8de9077ec',
        'admin');
