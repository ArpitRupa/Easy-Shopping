# Requests

## CreateAddress

- The `CreateAddress` data transfer object (DTO) class is used for creating address-related data from the client. This class encapsulates the information required to create a new address.
- It does not contain business logic and is meant to get data from the client when creating a new Address for the backend.

### Constructors

- **`CreateAddress()`**
    - Description: Default constructor for the `CreateAddress` DTO.


- **`CreateAddress(String shippingAddressLine1, String shippingAddressLine2, String city, String stateName, String postalCode)`**
    - Description: Constructor to create a `CreateAddress` DTO object with the provided information.

### Fields
- **`shippingAddressLine1` (String)**
    - Description: The first line of the shipping address.

- **`shippingAddressLine2` (String) (optional)**
    - Description: The second line of the shipping address.

- **`city` (String)**
    - Description: The city of the shipping address.

- **`stateName` (String)**
    - Description: The name of the state of the shipping address.

- **`postalCode` (String)**
    - Description: The postal code of the shipping address.

### Getter and Setter Methods

The class provides getter and setter methods for all fields to access and modify the address-related attributes.


## LoginRequest

- The `LoginRequest` class is a data transfer object (DTO) used to represent user login request data. It contains the user's username and password for authentication.
- It is a simple DTO class that represents the username and password provided by the user during the login process.
- This class is intended for data transfer and does not contain business logic.



### Fields

- **`username` (String)**
    - Description: The username provided by the user for login authentication.

- **`password` (String)**
    - Description: The password provided by the user for login authentication.

### Constructors

- **`LoginRequest()`**
    - Description: Default constructor for the `LoginRequest` class.

- **`LoginRequest(String username, String password)`**
    - Description: Constructor to create a `LoginRequest` object with the provided username and password.

### Getter Methods

The class provides getter methods for all fields to access attributes.

- **`getUsername()`**
    - Description: Retrieves the username from the `LoginRequest` object.
    - Return Type: `String`

- **`getPassword()`**
    - Description: Retrieves the password from the `LoginRequest` object.
    - Return Type: `String`

## UpdatePasswordDTO
- The `UpdatePasswordDTO` class is a data transfer object (DTO) used to represent a user's request to update their password. It contains the user's current password, new password, and confirmation of the new password.
- The `UpdatePasswordDTO` class is used for capturing user requests to update their password.
- It is a simple DTO class that represents the user's current password, the new password, and its confirmation.
- This class is intended for data transfer and does not contain business logic.

### Fields

- **`currentPassword` (String)**
    - Description: The user's current password to use for authentication and validation.

- **`newPassword` (String)**
    - Description: The new password the user intends to set.

- **`confirmNewPassword` (String)**
    - Description: Confirmation of the new password to ensure accuracy.

### Getter Methods

The class provides getter methods for all fields to access attributes.

- **`getCurrentPassword()`**
    - Description: Retrieves the user's current password from the `UpdatePasswordDTO` object.
    - Return Type: `String`

- **`getNewPassword()`**
    - Description: Retrieves the new password from the `UpdatePasswordDTO` object.
    - Return Type: `String`

- **`getConfirmNewPassword()`**
    - Description: Retrieves the confirmation of the new password from the `UpdatePasswordDTO` object.
    - Return Type: `String`

## UserRegistration

- The `UserRegistration` class class is a data transfer object (DTO) used to represent user registration data. 
- This class contains information about a user's first name, last name, email, username, password, and role, which are typically collected during user registration.
- This class is intended for data transfer and does not contain business logic.

### Fields

- **`firstName` (String)**
    - Description: The user's first name.

- **`lastName` (String)**
    - Description: The user's last name.

- **`email` (String)**
    - Description: The user's email address.

- **`username` (String)**
    - Description: The chosen username for the user's account.

- **`password` (String)**
    - Description: The user's chosen password.

- **`role` (String)**
    - Description: The role or permissions associated with the user (e.g., "ADMIN" or "USER").

### Constructors

- **`UserRegistration()`**
    - Description: Default constructor for the `UserRegistration` class.

- **`UserRegistration(String firstName, String lastName, String email, String username, String password, String role)`**
    - Description: Constructor to create a `UserRegistration` object with the provided user registration information.

### Getter Methods

The class provides getter methods for all fields to access attributes.

- **`getFirstName()`**
    - Description: Retrieves the user's first name from the `UserRegistration` object.
    - Return Type: `String`

- **`getLastName()`**
    - Description: Retrieves the user's last name from the `UserRegistration` object.
    - Return Type: `String`

- **`getEmail()`**
    - Description: Retrieves the user's email address from the `UserRegistration` object.
    - Return Type: `String`

- **`getUsername()`**
    - Description: Retrieves the chosen username from the `UserRegistration` object.
    - Return Type: `String`

- **`getPassword()`**
    - Description: Retrieves the chosen password from the `UserRegistration` object.
    - Return Type: `String`

- **`getRole()`**
    - Description: Retrieves the user's role or permissions from the `UserRegistration` object.
    - Return Type: `String`