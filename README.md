

# 🎥 Genie's Video Store Management System

This project implements a **Video Store Management System** for Genie's video rental shop. The application is developed in **JavaFX** to manage the store's inventory, rental process, and customer accounts. The system is designed to automate operations such as renting out movies, games, and DVDs, returning items, promoting customers, and managing the stock.

## 📋 Project Overview

- **Client**: Genie's Video Store, a local shop transitioning from paper-based management to a digital system.
- **Programming Language**: Java
- **Framework**: JavaFX
- **Database**: Text files (`customers.txt`, `items.txt`)
- **Platform**: Works on Windows, macOS, and Linux.

## 🛠️ Features

### Store Management

- Add, update, and delete video items (Movies, DVDs, Games).
- Add, update, and delete customer accounts (Guest, Regular, VIP).
- Increase the number of available copies for existing items.
- Manage rentals: rent and return items.
- Promote customers based on rental history (e.g., Guest to Regular, Regular to VIP).
- Search for customers or items by name or ID.

### Customer Management

- **Customer Types**: 
  - **Guest**: Can rent up to 2 items.
  - **Regular**: Can rent unlimited items.
  - **VIP**: Unlimited rentals and can earn reward points.
- VIP customers earn 10 points per rental and get free rentals once they reach 100 points.
- Automatic promotion system for customer upgrades based on rental history.

### Inventory Management

- Display items sorted by title or ID.
- Display out-of-stock items.
- View items by genre: Action, Horror, Drama, Comedy.
- Manage rental periods (2-day or 1-week loans).

### System Utilities

- Data is read from and saved to disk on startup and shutdown.
- A user-friendly GUI for all operations.
- Admin and customer login functionality.

## 🔍 Search Capabilities

- Search items by title or ID.
- Search customers by name or ID.
- Display sorted lists of customers and items.
- View customers by membership level (Guest, Regular, VIP).

## 🧑‍💻 Technical Specifications

- **Object-Oriented Design**: Demonstrates use of classes, inheritance, polymorphism, function overloading/overriding.
- **JavaFX**: User-friendly graphical interface for easy interaction with the system.
- **Data Persistence**: Saves and loads data from text files to ensure updates are reflected.

## 🚀 Setup & Installation

1. Clone the repository.
2. Run the project in **IntelliJ IDEA** or any Java IDE with JavaFX support.
3. Make sure the provided `customers.txt` and `items.txt` files are in the correct location for data loading.
4. Log in with the provided admin credentials (`admin` user).

