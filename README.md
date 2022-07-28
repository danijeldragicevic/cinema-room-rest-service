# Cinema Room REST Service
Backend service who is able to book and return seat in cinema room.
Service is also able to show statistics about number of sold tickets, available seats and earned income.
- All users can see available seats.
- All users can purchase and return a seat.
- Only authenticated users can see statistics about booked seats and sold tickets.

Service use authorization based on URL parameters.

# Technology
- Java 11
- Spring Boot 2.7.1 (Spring Web MVC, Spring Validation, Project Lombok)
- Gradle 7.4

# To run application:
Navigate to the project root directory and run **./gradlew bootRun**

# Exposed endpoints:
By default, service will run on **http://localhost:28852/** <br/>
Following endpoints will be exposed:

| Methods | Urls                            | Actions                                 |
|---------|---------------------------------|-----------------------------------------|
| GET     | api/seats                       | Show all available seats                |
| GET     | api/stats?password=super_secret | Show statistics                         |
| POST    | api/purchase                    | Book a seat                             |
| POST    | api/return                      | Return booked seat                      |

# Examples
**Example 1:** GET /api/seat
```
{
    "rows": 9,
    "columns": 9,
    "available_seats": [
        {
            "row": 1,
            "column": 1,
            "price": 10
        },
        {
            "row": 1,
            "column": 2,
            "price": 10
        },
        .....
        {
            "row": 9,
            "column": 8,
            "price": 8
        },
        {
            "row": 9,
            "column": 9,
            "price": 8
        }
    ]
}
```
Status code: 200 (OK)

**Example 2:** POST /api/purchase
```
{
    "row": 1,
    "column": 1
}
```
Status code: 200 (OK)
```
{
    "token": "5115505f-c2a7-4407-8670-77abc5390957",
    "booked_seats": [
        {
            "row": 1,
            "column": 1,
            "price": 10
        }
    ]
}
```
**Example 3:** POST /api/purchase
```
{
    "row": 1,
    "column": 1
}
```
Status code: 400 (Bad Request)
```
{
    "error": "The seat has been already purchased!"
}
```
**Example 4:** POST /api/return
```
{
    "token_XYZ": "5115505f-c2a7-4407-8670-77abc5390957"
}
```
Status code: 400 (Bad Request)
```
{
	"error": "The token key or value is wrong!"
}
```
**Example 5:** POST /api/return
```
{
    "token": "5115505f-c2a7-4407-8670-77abc5390957_XYZ"
}
```
Status code: 400 (Bad Request)
```
{
	"error": "The token key or value is wrong!"
}
```
**Example 6:** POST /api/return
```
{
    "token": "5115505f-c2a7-4407-8670-77abc5390957"
}
```
Status code 200 (OK)
```
{
    "returned_seats": [
        {
            "row": 1,
            "column": 1,
            "price": 10
        }
    ]
}
```
**Example 7:** GET /api/stats?password_xyz=super_secret
```
{
	"error": "Missing query parameter password!"
}
```
Status code: 400 (Bad Request)

**Example 8:** GET /api/stats?password=super_secret_xyz
```
{
	"error": "The password value is wrong!"
}
```
Status code: 401 (Unauthorized)

**Example 8:** GET /api/stats?password=super_secret
```
{
	"current_income": 10,
	"available_seats": 80,
	"sold_tickets": 1
}
```
Status code: 200 (OK)

# Licence
[![License](https://img.shields.io/badge/License-Apache_2.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)