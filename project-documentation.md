# Inventory Management System Using Advanced Collections & Data Structures

## 1. Project Overview

### Project Title

Inventory Management System Using Advanced Collections & Data Structures

### Objective

The objective of this project is to develop a professional Inventory Management System using Core Java, Maven, Advanced Collections Framework, Comparable, Comparator, Generic Programming, and Data Structure optimization.

The system allows users to:

* Add Products
* Update Product Quantity
* Delete Products
* Search Products
* View Sorted Inventory
* Monitor Low Stock Products
* Track Transaction History
* Generate Inventory Statistics
* Undo Recent Operations

---

## 2. Technologies Used

* Java 17+
* Maven
* Java Collections Framework
* Comparable Interface
* Comparator Interface
* Generic Programming
* OOP Concepts
* IntelliJ IDEA

---

## 3. Package Structure

src/main/java/com/inventory

* model

    * Product.java
    * Transaction.java

* comparators

    * PriceComparator.java
    * NameComparator.java
    * ValueComparator.java

* service

    * InventoryService.java

* utils

    * InventoryUtils.java

* main

    * InventoryManagementSystem.java

---

## 4. Class Description

### Product.java

Represents an inventory product.

Attributes:

* SKU
* Name
* Category
* Price
* Quantity
* Last Updated

Responsibilities:

* Store product information
* Calculate inventory value
* Provide natural sorting using Comparable
* Prevent duplicate products using equals() and hashCode()

---

### Transaction.java

Represents an inventory transaction.

Responsibilities:

* Record inventory operations
* Maintain audit history
* Store transaction details

Examples:

* Add Product
* Update Product
* Delete Product
* Undo Operation

---

### Comparators

#### PriceComparator

Sorts products by price in ascending order.

#### NameComparator

Sorts products alphabetically.

#### ValueComparator

Sorts products by inventory value in descending order.

Inventory Value = Price × Quantity

---

### InventoryUtils

Utility class containing:

* SKU Validation
* Price Validation
* Quantity Validation
* Generic Methods

Example:

public static <T> void printList(List<T> list)

---

### InventoryService

Business layer of the application.

Responsibilities:

* Product Management
* Transaction Management
* Statistics Generation
* Sorting Operations
* Undo Operations
* Low Stock Monitoring

---

### InventoryManagementSystem

Menu-driven console application.

Responsibilities:

* User Interaction
* Menu Display
* Input Handling
* Service Integration

---

## 5. Collections Framework Usage

### HashSet<Product>

Purpose:
Store unique products.

Reason:
SKU should be unique.

Advantages:

* Fast insertion
* Fast deletion
* Prevents duplicate products

Time Complexity:

* Add: O(1)
* Remove: O(1)
* Search: O(1)

---

### TreeSet<Product>

Purpose:
Maintain sorted products automatically.

Reason:
Products are sorted by SKU using Comparable.

Time Complexity:

* Insert: O(log n)
* Remove: O(log n)
* Search: O(log n)

---

### LinkedList<Transaction>

Purpose:
Store transaction history.

Reason:
Frequent insertion of new transactions.

Time Complexity:

* Add First: O(1)
* Add Last: O(1)

---

### Stack<Product>

Purpose:
Implement Undo functionality.

Reason:
Undo follows Last-In-First-Out (LIFO) behavior.

Operations:

* Push
* Pop
* Peek

Time Complexity:

* Push: O(1)
* Pop: O(1)

---

### Queue<Product>

Purpose:
Manage low stock alerts.

Reason:
Alerts are processed in First-In-First-Out (FIFO) order.

Operations:

* Offer
* Poll
* Peek

Time Complexity:

* Offer: O(1)
* Poll: O(1)

---

### HashMap<String, Product>

Purpose:
Fast SKU lookup.

Reason:
Searching by SKU is one of the most frequent operations.

Time Complexity:

* Put: O(1)
* Get: O(1)
* Remove: O(1)

---

## 6. Comparable Usage

Product class implements Comparable<Product>.

Natural Sorting:

Products are sorted by SKU.

Benefits:

* Automatic TreeSet ordering
* Consistent sorting behavior

---

## 7. Comparator Usage

Multiple sorting strategies are required.

Comparators used:

* PriceComparator
* NameComparator
* ValueComparator

Benefits:

* Flexible sorting
* Better maintainability
* Follows Open/Closed Principle

---

## 8. Generic Programming

Generic method:

public static <T> void printList(List<T> list)

Benefits:

* Reusable code
* Type safety
* Better maintainability

---

## 9. Inventory Statistics

The system generates:

* Total Products
* Total Quantity
* Total Inventory Value
* Highest Value Product
* Average Product Price
* Low Stock Product Count

---

## 10. Undo Functionality

Undo is implemented using Stack.

Process:

1. Store previous product state.
2. Push into Stack.
3. Perform update/delete operation.
4. Pop from Stack during Undo.
5. Restore previous state.

Reason:

Stack follows LIFO behavior, which perfectly matches Undo operations.

---

## 11. Design Decisions

### Why HashSet?

To prevent duplicate products and provide fast operations.

### Why TreeSet?

To automatically maintain sorted inventory.

### Why LinkedList?

To efficiently manage transaction history.

### Why Stack?

To implement Undo functionality.

### Why Queue?

To manage low-stock alerts.

### Why HashMap?

To improve SKU search performance from O(n) to O(1).

---

## 12. Conclusion

This project successfully demonstrates the practical use of Java Collections Framework, Generic Programming, Comparable, Comparator, Data Structures, and Object-Oriented Programming principles in a real-world Inventory Management System.

The application is scalable, maintainable, and optimized for inventory operations while following professional Java development practices.
