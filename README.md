# Blog Application API using Spring Boot and Hibernate

This is a simple example of a blog application API built using Spring Boot and Hibernate. The API provides endpoints for creating, retrieving, updating, and deleting blog posts.

## Technologies Used

- Java
- Spring Boot
- Hibernate
- MySQL

## Getting Started

To get started with the blog application API, follow the steps below:

### Prerequisites

- Java Development Kit (JDK) installed
- MySQL installed and running

### Installation

1. Clone the repository:

   ```shell
   git clone https://github.com/your-username/blog-application.git

2. Navigate to the project directory:

  ```shell
  cd blog-application
    
3. Configure the database connection:

Open src/main/resources/application.properties file.
Update the spring.datasource.url, spring.datasource.username, and spring.datasource.password properties with your MySQL database details.


4. Build and run the application:
  ```shell
 ./mvnw spring-boot:run
  
API Endpoints
The blog application API provides the following endpoints:

GET /api/posts: Retrieves all blog posts.
GET /api/posts/{id}: Retrieves a specific blog post by ID.
POST /api/posts: Creates a new blog post.
PUT /api/posts/{id}: Updates an existing blog post.
DELETE /api/posts/{id}: Deletes a blog post.

API Calls
![image](https://github.com/NehaPatil104/Blog-App-APIs-using-Spring-Boot/assets/71131714/7856d99a-7e61-4070-bdc7-6375c17daa0f)


