# My Shoe Store App 

A Java-based console application for managing a shoe store relying on a SQL database backend.

## Features
- **User Authentication**: Secure login for customers.
- **Product Browsing**: View available shoes with current stock.
- **Shopping Cart**: Add items to cart via database stored procedures.

## Database Mockup
```mermaid
erDiagram
    BRAND ||--o{ PRODUCT : "has"
    COLOR ||--o{ PRODUCT : "comes in"
    SIZE ||--o{ PRODUCT : "available in"
    CATEGORY ||--o{ PRODUCT_CATEGORY : "contains"
    PRODUCT ||--o{ PRODUCT_CATEGORY : "belongs to"
    PRODUCT ||--o{ ORDER_ITEM : "included in"
    ORDERS ||--o{ ORDER_ITEM : "consists of"
    CUSTOMER ||--o{ ORDERS : "places"
    PRODUCT ||--o{ OUT_OF_STOCK : "logs"

    BRAND {
        int brand_id PK
        string name
    }

    COLOR {
        int color_id PK
        string name
    }

    SIZE {
        int size_id PK
        int eu_size
    }

    CATEGORY {
        int category_id PK
        string name
    }

    CUSTOMER {
        int customer_id PK
        string username
        string password
        string first_name
        string last_name
        string city
    }

    PRODUCT {
        int product_id PK
        int brand_id FK
        int color_id FK
        int size_id FK
        string model_name
        decimal price
        int stock
    }

    ORDERS {
        int order_id PK
        int customer_id FK
        datetime order_date
        string status
    }

    ORDER_ITEM {
        int order_id PK_FK
        int product_id PK_FK
        int quantity
        decimal unit_price
    }

    PRODUCT_CATEGORY {
        int product_id PK_FK
        int category_id PK_FK
    }

    OUT_OF_STOCK {
        int outofstock_id PK
        int product_id FK
        datetime out_time
    }
```

## Requirements
- Java Development Kit (JDK) 21
- PostgreSQL / MySQL Database (configure in `.env`)
- IntelliJ IDEA (recommended)

## Setup
1. Clone the repository.
2. Configure your database connection in `.env`.
3. Run `src/Main.java`.
