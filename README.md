# JWT Role-Based Authentication API

This project is a **Spring Boot** application that implements **JWT-based authentication** with role-based access control. It provides APIs for user registration, login, and role management.

## Features

- **User Authentication**: Secure login and signup using JWT.
- **Role-Based Access Control**: Assign roles to users (e.g., `ADMIN`, `SUPER_ADMIN`) to control access to resources.
- **Password Encryption**: User passwords are securely hashed using `PasswordEncoder`.
- **Admin Seeder**: Automatically creates a `SUPER_ADMIN` user on application startup if it doesn't exist.

## Technologies Used

- **Java**: Programming language.
- **Spring Boot**: Framework for building the application.
    - Spring Security: For authentication and authorization.
    - Spring Data JPA: For database interaction.
- **JWT**: For secure token-based authentication.
- **Gradle**: Build tool.
- **Mysql Database**: database for development and testing.

## Project Structure

- `controller`: Contains REST controllers for authentication and user management.
- `services`: Business logic for user and authentication operations.
- `entities`: JPA entities for `User` and `Role`.
- `repository`: Interfaces for database operations.
- `bootstrap`: Contains the `AdminSeeder` to initialize roles and admin users.

## Endpoints

### Authentication Endpoints

- **POST** `/auth/signup`: Register a new user.
    - Request Body: `RegisterUserDto`
    - Response: Registered `User` object.
- **POST** `/auth/login`: Authenticate a user and generate a JWT token.
    - Request Body: `LoginUserDto`
    - Response: `LoginResponse` containing the token and expiration time.

## Setup and Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/VenkateshManohar27/jwtroleauth.git
   cd jwtroleauth
   ```

2. **Configure the Database**:
    - Update the database configuration in `application.properties` (e.g., URL, username, password).

3. **Start the mysql DB**:
   ``docker run -d -e MYSQL_ROOT_PASSWORD=secret -e MYSQL_DATABASE=taskdb --name mysqldb -p 3307:3306 mysql:8.0``
4. **Build the Project**:
   ```bash
   ./gradlew build
   ```

4. **Run the Application**:
   ```bash
   ./gradlew bootRun
   ```

5. **Access the API**:
    - The application will run on `http://localhost:8005`.

## Admin Seeder

On application startup, the `AdminSeeder` will:
- Check if a `SUPER_ADMIN` role exists.
- Create a default `SUPER_ADMIN` user if it doesn't exist:
    - **Email**: `super.admin@email.com`
    - **Password**: `123456`

## Testing

docker exec -it mysqldb mysql -u root -p

mysql> use taskdb

mysql> select * from roles;
+----+----------------------------+--------------------------+-------------+----------------------------+
| id | created_at                 | description              | name        | updated_at                 |
+----+----------------------------+--------------------------+-------------+----------------------------+
|  1 | 2025-07-07 20:40:51.441000 | Default user role        | USER        | 2025-07-07 20:40:51.442000 |
|  2 | 2025-07-07 20:40:51.481000 | Administrator role       | ADMIN       | 2025-07-07 20:40:51.481000 |
|  3 | 2025-07-07 20:40:51.494000 | Super Administrator role | SUPER_ADMIN | 2025-07-07 20:40:51.494000 |
+----+----------------------------+--------------------------+-------------+----------------------------+
3 rows in set (0.00 sec)

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.


