# Responses

## LoginResponse

- The `LoginResponse` data transfer object (DTO) class is used to represent a response containing user login information, including the username, user role, and a JSON Web Token (JWT).
- The `LoginResponse` DTO class is used to send login-related information in responses.
- It is typically utilized in authentication services and controllers.
- This class is intended for data transfer and does not contain business logic.

### Fields

- **`username` (String)**
    - Description: The username of the authenticated user.

- **`role` (Role)**
    - Description: The role or permissions associated with the user ['USER' or 'ADMIN' via `Role`enum].

- **`jwt` (String)**
    - Description: The JSON Web Token (JWT) generated for user authentication.

### Constructors

- **`LoginResponse()`**
    - Description: Default constructor for the `LoginResponse` DTO.

- **`LoginResponse(String username, Role role, String jwt)`**
    - Description: Constructor to create a `LoginResponse` object with the provided data.

### Getter Methods

The class provides getter methods for all fields to access attributes.

- **`getUsername()`**
    - Description: Retrieves the username from the `LoginResponse` object.
    - Return Type: `String`

- **`getRole()`**
    - Description: Retrieves the user's role from the `LoginResponse` object.
    - Return Type: `Role`

- **`getJwt()`**
    - Description: Retrieves the JSON Web Token (JWT) from the `LoginResponse` object.
    - Return Type: `String`

## ResponseAddress

- The `ResponseAddress` DTO class is used to represent a response containing address information. It extends the `CreateAddress` request class and includes additional fields such as `addressId` and `userID`.
- The `ResponseAddress` DTO class is used to send address-related information in responses.
- It extends the `CreateAddress` class and includes additional fields such as address ID and user ID.
- This class is intended for data transfer and does not contain business logic.

### Fields

- **`addressId` (int)**
    - Description: The unique identifier for the address.

- **`userID` (int)**
    - Description: The unique identifier of the user associated with the address.

### Constructors

- **`ResponseAddress(String shippingAddressLine1, String shippingAddressLine2, String city, String stateName, String postalCode, int addressId, int userID)`**
- Description: Constructor to create a `ResponseAddress` object with the provided address information, address ID, and user ID.


- **`ResponseAddress(Address address)`**
    - Description: Constructor to create a `ResponseAddress` object based on an `Address` object.


### Getter Methods

The class provides getter methods for all fields to access attributes.

- **`getAddressId()`**
    - Description: Retrieves the unique identifier for the address from the `ResponseAddress` object.
    - Return Type: `int`

- **`getUserID()`**
    - Description: Retrieves the unique identifier of the user associated with the address from the `ResponseAddress` object.
    - Return Type: `int`


## UserDTO

- The `UserDto` DTO class is used to represent user data to be exposed in responses. It includes information about the user's ID, first name, last name, email, username, and role.
- The `UserDto` DTO class is commonly used to expose user-related information in response objects.
- It is useful in user-related services and controllers.
- This class is intended for data transfer and does not contain business logic.


### Fields

- **`id` (int)**
    - Description: The unique identifier for the user.

- **`firstName` (String)**
    - Description: The first name of the user.

- **`lastName` (String)**
    - Description: The last name of the user.

- **`email` (String)**
    - Description: The email address of the user.

- **`username` (String)**
    - Description: The username of the user.

- **`role` (Role)**
    - Description: The role or permissions associated with the user.

### Constructors

- **`UserDto()`**
    - Description: Default constructor for the `UserDto` DTO.

- **`UserDto(User user)`**
    - Description: Constructor to create a `UserDto` object based on a `User` object. It extracts relevant user information and populates the DTO fields.

### Getter and Setter Methods

The class provides getter and setter methods for all fields to access and modify attributes.

