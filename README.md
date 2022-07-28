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

Response status code 200 (OK)</br>
Response body:
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
**Example 2:** POST /api/purchase

Request body:
```
{
    "row": 1,
    "column": 1
}
```
Response status code 200 (OK)</br>
Response body:
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

Request body (book same ticket again):
```
{
    "row": 1,
    "column": 1
}
```
Response status code 400 (Bad Request)</br>
Response body:
```
{
    "error": "The seat has been already purchased!"
}
```
**Example 4:** POST /api/return

Request body (wrong token key):
```
{
    "token_XYZ": "5115505f-c2a7-4407-8670-77abc5390957"
}
```
Response status code 400 (Bad Request)</br>
Response body:
```
{
	"error": "The token key or value is wrong!"
}
```
**Example 5:** POST /api/return

Request body (wrong token value):
```
{
    "token": "5115505f-c2a7-4407-8670-77abc5390957_XYZ"
}
```
Response status code 400 (Bad Request)</br>
Response body:
```
{
	"error": "The token key or value is wrong!"
}
```
**Example 6:** POST /api/return

Request body (correct token):
```
{
    "token": "5115505f-c2a7-4407-8670-77abc5390957"
}
```
Response status code 200 (OK)</br>
Response body:
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
**Example 7:** GET /api/stats?password_xyz=super_secret (wrong query param name):

Response status code 400 (Bad Request)</br>
Response body:
```
{
	"error": "Missing query parameter password!"
}
```
**Example 8:** GET /api/stats?password=super_secret_xyz (wrong query param value):

Response status code 401 (Unauthorized)</br>
Response body:
```
{
	"error": "The password value is wrong!"
}
```
**Example 8:** GET /api/stats?password=super_secret (correct query param):

Response status code 200 (OK)</br>
Response body:
```
{
	"current_income": 10,
	"available_seats": 80,
	"sold_tickets": 1
}
```

# Licence
[![License](https://img.shields.io/badge/License-Apache_2.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)