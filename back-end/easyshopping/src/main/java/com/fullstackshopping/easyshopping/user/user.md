# User

## Controller

### Admin Controller
- The `AdminController` class is a Spring Boot controller responsible controller responsible for checking authorization for "ADMIN" roles. It serves as a RESTful web service accessible under the "/admin" endpoint.
- Used primarily in testing the authorization of users.

#### Endpoints

- **URL:** `/admin`
- **HTTP Method:** GET
- **Authorization:** ADMIN
- **Description:** This endpoint returns a message indicating the presence of the Admin Console.

You can access the Admin Console by sending a GET request to the `/admin` endpoint. The response will be a simple string message indicating "Admin Console."

## UserApiController

Provides a set of endpoints to manage user-related operations.

### Endpoints

#### Get All Users
- **URL:** `/api/users`
- **HTTP Method:** GET
- **Authorization:** Requires the 'ADMIN' role
- **Description:** Returns a list of all users.
- **Response:** Returns a list of `UserDto` objects.

#### Get User by Token
- **URL:** `/api/users/user`
- **HTTP Method:** GET
- **Authorization:** Requires the 'ADMIN' or 'USER' role
- **Description:** Returns user information based on the provided JWT token.
- **Response:** Returns a `UserDto` object.

#### Get User by ID
- **URL:** `/api/users/id/{id}`
- **HTTP Method:** GET
- **Authorization:** Requires the 'ADMIN' or 'USER' role
- **Description:** Returns user information by the specified user ID.
- **Response:** Returns a `UserDto` object.

#### Get User by Email
- **URL:** `/api/users/email/{email}`
- **HTTP Method:** GET
- **Authorization:** Requires the 'ADMIN' or 'USER' role
- **Description:** Returns user information by the specified email address.
- **Response:** Returns a `UserDto` object.

#### Get User by Username
- **URL:** `/api/users/username/{username}`
- **HTTP Method:** GET
- **Authorization:** Requires the 'ADMIN' role
- **Description:** Returns user information by the specified username.
- **Response:** Returns a `UserDto` object.

#### Update User Information
- **URL:** `/api/users/{id}`
- **HTTP Method:** PUT
- **Authorization:** Requires the 'ADMIN' or 'USER' role
- **Description:** Updates user information based on the provided user ID.
- **Request:** Expects a `UserRegistration` object in the request body.
- **Response:** Returns a `UserDto` object.

#### Update User Role
- **URL:** `/api/users/{id}/role`
- **HTTP Method:** PUT
- **Authorization:** Requires the 'ADMIN' role
- **Description:** Updates the user's role based on the provided user ID.
- **Request:** Expects a string containing the new role in the request body.
- **Response:** Returns a `UserDto` object.

#### Update User Password
- **URL:** `/api/users/{id}/password`
- **HTTP Method:** PUT
- **Authorization:** Requires the 'ADMIN'
- **Description:** Updates the user's password based on the provided user ID. Useful for password resets by the admin.
- **Request:** Expects a string containing the new password in the request body.
- **Response:** Returns a `UserDto` object.

#### Delete User by ID
- **URL:** `/api/users/{id}`
- **HTTP Method:** DELETE
- **Authorization:** Requires the 'ADMIN' role
- **Description:** Deletes the user based on the provided user ID.
- **Response:** Returns `true` if the user was successfully deleted.


### User Controller
- The `UserController` class is a Spring Boot controller responsible for checking authorization for "USER" roles. It serves as a RESTful web service accessible under the "`/user`" endpoint.
- Used primarily in testing the authorization of users.

#### Endpoints

- **URL:** `/user`
- **HTTP Method:** GET
- **Authorization:** ADMIN or USER
- **Description:** This endpoint returns a message indicating the presence of the "User Page".

You can access the User Page by sending a GET request to the `/user` endpoint. The response will be a simple string message indicating "User Page".

## Model

### Notes
- This model class is used to represent user accounts in the application.
- Users are associated with shipping addresses through a one-to-many relationship with the `Address` class.

### Fields

**`id` (int)**
- Description: The unique identifier for the user.
  - Notes:
    - This field is a primary key for the user.
    - The value is auto-generated using the database's auto-increment strategy.
    - It cannot be updated once set.

- **`firstName` (String)**
    - Description: The user's first name.
    - Constraints: This field is required and can have a maximum length of 50 characters.

- **`lastName` (String)**
    - Description: The user's last name.
    - Constraints: This field is required and can have a maximum length of 50 characters.

- **`email` (String)**
    - Description: The user's email address.
    - Constraints: This field is required, unique, and can have a maximum length of 320 characters.
    - Notes: The email is stored in lowercase to ensure case-insensitive uniqueness.

- **`username` (String)**
    - Description: The user's username.
    - Constraints: This field is required, unique, and can have a maximum length of 25 characters.
    - Notes: The username is stored in lowercase to ensure case-insensitive uniqueness.

- **`password` (String)**
    - Description: The user's password.
    - Constraints: This field is required and can have a maximum length of 100 characters.

- **`role` (`Role`)**
    - Description: The user's role in the application.
    - Constraints: This field is required and stores an enumerated value from the `Role` enum.

- **`addresses` (List<`Address`>)**
    - Description: A list of shipping addresses associated with the user.
    - Constraints: This field represents a one-to-many relationship with the `Address` class, allowing multiple addresses to be associated with a single user.

### Constructors

- **`User()` (Default Constructor)**
    - Description: Initializes a new user instance.

- **`User(String firstName, String lastName, String email, String username, String password, Role role)`**
    - Description: Initializes a new user instance with the provided details.


### Methods

- **Getters and Setters:** Provide access to the class's fields and allow modification where necessary.


- **`addAddress(Address address)`**
    - Description: Adds a shipping address to the user's list of addresses.


- **`toString()`**
    - Description: Overrides the default `toString()` method to provide a string representation of the user object.

    - Returns: A string representation of the user object, including its fields.

## Repository

The `UserRepository` interface is responsible for defining methods to interact with the user data stored in the database. It extends the `JpaRepository` interface, which provides basic CRUD (Create, Read, Update, Delete) operations for the `User` entity.

### Methods


- **`Optional<User> findByUsername(String username)`**
    - Description: Finds a user by their username.
    - Parameters:
        - `username` (String): The username to use in the query to find the user.
    - Returns: An `Optional<User>` object obtained from the database. It may or may not contain a user based on the provided username.

- **`Optional<User> findByEmail(String email)`**
    - Description: Finds a user by their email address.
    - Parameters:
        - `email` (String): The email address to use in the query to find the user.
    - Returns: An `Optional<User>` object obtained from the database. It may or may not contain a user based on the provided email.

- **`boolean existsByUsername(String username)`**
    - Description: Checks if a user with the given username exists in the database.
    - Parameters:
        - `username` (String): The username to check for existence.
    - Returns: `true` if a user with the provided username exists in the database; otherwise, `false`.

- **`boolean existsByEmail(String email)`**
    - Description: Checks if a user with the given email address exists in the database.
    - Parameters:
        - `email` (String): The email address to check for existence.
    - Returns: `true` if a user with the provided email exists in the database; otherwise, `false`.

## Role

The `Role` enum represents the possible roles for user accounts in the application. It defines two roles: '`USER`' and '`ADMIN`'.

### Enum Values

- **`USER`**
    - Description: Represents a standard user role.
    - Value: `"USER"`


- **`ADMIN`**
    - Description: Represents an administrator role.
    - Value: `"ADMIN"`

### Constructor

- **`Role(String roleName)`**
    - Description: Initializes a role with the provided role name.
    - Parameters:
        - `roleName` (String): The name of the role.

### Methods

- **`getRoleName()`**
    - Description: Returns the name of the role.
    - Returns: The role name as a string.


## Service

### User Service

The `UserService` class is responsible for managing user-related operations and interactions within the application.

### Dependencies

- **`userRepository` (UserRepository)**
    - Description: A repository interface for accessing user data stored in the database.

- **`passwordEncoder` (PasswordEncoder)**
    - Description: A service for encoding and decoding user passwords.

- **`tokenService` (TokenService)**
    - Description: A service for working with authentication tokens.

### Constructors

- **`UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService)`**
    - Description: Initializes the `UserService` with required dependencies.

### Methods

- **`getAllUsers()`**
    - Description: Retrieves a list of all users in the system.
    - Returns: A list of `UserDto` objects, each representing a user.


- **`getUserById(int id)`**
    - Description: Retrieves a user by their ID.
    - Parameters:
        - `id` (int): The ID of the user to retrieve.
    - Returns: The user information as a `UserDto`.
    - Throws: `ResponseStatusException` of code `404` if the user with the specified ID is not found.


- **`getUserByUsername(String username)`**
    - Description: Retrieves a user by their username.
    - Parameters:
        - `username` (String): The username of the user to retrieve.
    - Returns: The user information as a `UserDto`.
    - Throws: `ResponseStatusException` of code `404` if the user with the specified username is not found.


- **`getUserByEmail(String email)`**
    - Description: Retrieves a user by their email.
    - Parameters:
        - `email` (String): The email of the user to retrieve.
    - Returns: The user information as a `UserDto`.
    - Throws: `ResponseStatusException` of code `404` if the user with the specified email is not found.


- **`deleteUser(int id)`**
    - Description: Deletes a user by their ID.
    - Parameters:
        - `id` (int): The ID of the user to delete.
    - Returns: `true` if the user was found and deleted, `false` if the user was not found.


- **`updateUser(int id, UserRegistration updatedUser)`**
    - Description: Updates an existing user's information.
    - Parameters:
        - `id` (int): The ID of the user to update.
        - `updatedUser` (UserRegistration): The updated user information.
    - Returns: The updated user's information as a `UserDto`.
    - Throws: `ResponseStatusException` of code `404` if the user with the specified ID is not found or code `400` if there are conflicts with the updated username or email.


- **`updateUserPassword(int id, String password)`**
    - Description: Updates the password of a user identified by their ID.
    - Parameters:
        - `id` (int): The ID of the user to update.
        - `password` (String): The new password to set for the user.
    - Returns: A `UserDto` representing the updated user with the new password.
    - Throws: `ResponseStatusException` of code `404` if the user with the given ID is not found.


- **`updateUserRole(int id, String role)`**
    - Description: Updates the role of a user identified by their ID.
    - Parameters:
        - `id` (int): The ID of the user to update.
        - `role` (String): The new role for the user.
    - Returns: A `UserDto` representing the updated user with the new role.
    - Throws: `ResponseStatusException` of code `404` if the user with the given ID is not found.


- **`getRoleFromString(String registrationRole)`**
    - Description: Converts a role string to a `Role` enum.
    - Parameters:
        - `registrationRole` (String): The role string to be converted.
    - Returns: The corresponding `Role` enum value.
    - Throws: `ResponseStatusException` of code `400` if an invalid role is provided.


- **`getUser(String token)`**
    - Description: Retrieves a user based on an authentication token.
    - Parameters:
        - `token` (String): The authentication token.
    - Returns: A `UserDto` representing the user associated with the token.
    - Throws: `ResponseStatusException` of code `404` if the user with the specified username is not found.