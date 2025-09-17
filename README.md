# Book API

## Setup

### MySQL Schema
- 
```sql
CREATE DATABASE book_db;
USE book_db;

CREATE TABLE `books`
(
  `id`             bigint(20) NOT NULL AUTO_INCREMENT,
  `author`         varchar(255) DEFAULT NULL,
  `published_date` date         DEFAULT NULL,
  `title`          varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY              `idx_author` (`author`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

```


# Book API Services


## Tech Stack and Tools
- Java version: 17.0.2
- Maven-3.9.9  
- Spring Boot Framework 3.1.0
- Spring Data JPA
- Spring JDBC (JdbcTemplate)
- Hibernate
- MySQL 8
- Lombok

## Modules
- Book Module

## Features

 - Get all books.
 - Save book

  
## Installation
- To run this API server, you should update the database configuration inside the application.properties file which is present in the src/main/resources folder.
- Update the port number, username and password as per your local database configuration.
- server.port=8080
- spring.datasource.url=jdbc:mysql://localhost:3306/book_db?serverTimezone=UTC&rewriteBatchedStatements=true
- spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
- spring.datasource.username=your_username_here
- spring.datasource.password=your_password_here

## How to Run

🚀 1. Run from Your IDE (IntelliJ, VS Code, Eclipse)
If your main class is annotated with @SpringBootApplication, just:
- Right-click the class (e.g. BookApiApplication.java)
- Select Run 'BookApiApplication'
  This launches the embedded server (usually Tomcat) and starts your app.

🧰 2. Run with Maven
If your project uses Maven and is packaged as a JAR:
```
mvn clean compile

mvn spring-boot:run
```

## How to Test Api
1. Use Postman or curl for Manual Testing
   For quick manual checks:

- Get All Book API
```
  curl --location 'http://localhost:8080/api/v1/books?size=10&page=0'
```
expected responses
```
{
"content": [
{
"id": 1,
"title": "Title 30001",
"author": "Author 30001",
"publishedDate": "17/09/2568"
},
{
"id": 2,
"title": "Title 30002",
"author": "Author 30002",
"publishedDate": "17/09/2568"
},
{
"id": 3,
"title": "Title 30003",
"author": "Author 30003",
"publishedDate": "17/09/2568"
},
{
"id": 4,
"title": "Title 30004",
"author": "Author 30004",
"publishedDate": "17/09/2568"
},
{
"id": 5,
"title": "Title 30005",
"author": "Author 30005",
"publishedDate": "17/09/2568"
},
{
"id": 6,
"title": "Title 30006",
"author": "Author 30006",
"publishedDate": "17/09/2568"
},
{
"id": 7,
"title": "Title 30007",
"author": "Author 30007",
"publishedDate": "17/09/2568"
},
{
"id": 8,
"title": "Title 30008",
"author": "Author 30008",
"publishedDate": "17/09/2568"
},
{
"id": 9,
"title": "Title 30009",
"author": "Author 30009",
"publishedDate": "17/09/2568"
},
{
"id": 10,
"title": "Title 30010",
"author": "Author 30010",
"publishedDate": "17/09/2568"
}
],
"pageable": {
"sort": {
"empty": true,
"sorted": false,
"unsorted": true
},
"offset": 0,
"pageNumber": 0,
"pageSize": 10,
"paged": true,
"unpaged": false
},
"last": false,
"totalPages": 10001,
"totalElements": 100001,
"size": 10,
"number": 0,
"sort": {
"empty": true,
"sorted": false,
"unsorted": true
},
"first": true,
"numberOfElements": 10,
"empty": false
}
```
- Find book by author
```
curl --location 'http://localhost:8080/api/v1/books?author=Satib'
```
expected responses
```
{
"content": [
{
"id": 100001,
"title": "Effective Java Satib 1",
"author": "Satib",
"publishedDate": "17/09/2568"
}
],
"pageable": {
"sort": {
"empty": true,
"sorted": false,
"unsorted": true
},
"offset": 0,
"pageSize": 10,
"pageNumber": 0,
"unpaged": false,
"paged": true
},
"last": true,
"totalPages": 1,
"totalElements": 1,
"size": 10,
"number": 0,
"sort": {
"empty": true,
"sorted": false,
"unsorted": true
},
"first": true,
"numberOfElements": 1,
"empty": false
}
```
- Save Book API
```
  curl --location 'http://localhost:8080/api/v1/books' \
--header 'Content-Type: application/json' \
--data '{
    "title": "Effective Java",
    "author": "Baw Satib",
    "publishedDate": "17/09/2568"
}'
```
expected responses
```
{
    "id": 100003,
    "title": "Effective Java Satib 1",
    "author": "Satib",
    "publishedDate": "17/09/2568"
}
```

## How to Run Test

✅ 1. Run Tests via Maven
If you're using Maven, just run:
```
mvn test
```


