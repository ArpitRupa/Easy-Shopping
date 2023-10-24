# Address

## Controller

Provides a set of end points to manage user addresses.
### Notes
- All endpoints expect valid user authentication via JWTs.

### End-points
#### Get All Addresses
- **URL:** `/api/addresses`
- **HTTP Method:** GET
- **Authorization:** Requires the 'ADMIN' role
- **Description:** Returns a list of all addresses.
- **Response:** Returns a list of `Address` objects.

#### Get Address by ID
- **URL:** `/api/addresses/id/{id}`
- **HTTP Method:** GET
- **Authorization:** Requires the 'ADMIN' or 'USER' role
- **Description:** Retrieves an address by its unique ID.
- **Request Parameter:**
    - `id` (int) - The ID of the address to retrieve.
- **Response:** Returns a `ResponseAddress` object.

#### Get Addresses by User ID
- **URL:** `/api/addresses/user/{id}`
- **HTTP Method:** POST
- **Authorization:** Requires the 'ADMIN' or 'USER' role
- **Description:** Retrieves a list of addresses associated with a user.
- **Request Parameter:**
    - `id` (int) - The ID of the user to fetch addresses for.
- **Response:** Returns a list of `ResponseAddress` objects.

#### Get Addresses for User

- **URL:** `/api/addresses/user`
- **HTTP Method:** GET
- **Authorization:** Requires the 'ADMIN' or 'USER' role
- **Description:** Retrieves a list of addresses associated with the authenticated user.
- **Request Header:**
    - `Authorization` - User token for authentication.
- **Response:** Returns a list of `ResponseAddress` objects.

#### Create Address

- **URL:** `/api/addresses/create`
- **HTTP Method:** POST
- **Authorization:** Requires the 'ADMIN' or 'USER' role
- **Description:** Creates a new address for the authenticated user.
- **Request Body:** `CreateAddress` object.
- **Request Header:**
    - `Authorization` - User token for authentication.
- **Response:** Returns a `ResponseAddress` object.

#### Update Address

- **URL:** `/api/addresses/{id}`
- **HTTP Method:** PUT
- **Authorization:** Requires the 'ADMIN' or 'USER' role
- **Description:** Updates an address with the specified ID.
- **Request Parameter:**
    - `id` (int) - The ID of the address to update.
- **Request Body:** `CreateAddress` object.
- **Response:** Returns a `ResponseAddress` object with the updated data.

#### Delete Address

- **URL:** `/api/addresses/{id}`
- **HTTP Method:** DELETE
- **Authorization:** Requires the 'ADMIN' or 'USER' role
- **Description:** Deletes an address with the specified ID.
- **Request Parameter:**
    - `id` (int) - The ID of the address to delete.
- **Response:** Returns a boolean indicating the success of the deletion.

### Utility

#### `getUserLocation`

- **Description:** A utility function to generate the URI for a specific address based on its ID.

## Model

### Notes

- This model class is used to represent shipping addresses associated with user accounts.
- The `User` class is related to the `Address` class through a many-to-one association.

### Fields
- **`addressId` (int)**
    - Description: The unique identifier for the address.
    - notes:
      - `primary key` for the address
      - is auto-incremented via MySQL
        

- **`user` (User)**
    - Description: The user associated with this address.
    - Notes:
        - `Many-to-One`  relationship with the `User` model joined via `user_id` column.
        - `User` can have many address.


- **`shippingAddressLine1` (String) (optional)**
    - Description: The first line of the shipping address.


- **`shippingAddressLine2` (String)**
    - Description: The second line of the shipping address 


- **`city` (String)**
    - Description: The city of the shipping address.


- **`stateName` (String)**
    - Description: The name of the state associated with the shipping address.
   

- **`postalCode` (String)**
    - Description: The postal code of the shipping address.


### Getter and Setter Methods

The class provides getter and setter methods for all fields to access and modify the address attributes.

### `toString()` Method

The `toString()` method generates a string representation of the `Address` object, displaying its attributes.

## Repository

The `AddressRepository` interface defines methods for interacting with the database to perform CRUD (Create, Read, Update, Delete) operations on `Address` entities. It extends the `JpaRepository` interface provided by Spring Data JPA.

### Methods

#### `findAllByUser_Id(int id)`

- Description: Retrieves a list of addresses associated with a user based on the user's ID.
- Parameters:
    - `id` (int): The unique identifier of the user.
- Return Type: `Optional<List<Address>>`
- Notes: Returns an empty `Optional` if no addresses are found for the user.

#### `findAllByUser_Username(String username)`

- Description: Retrieves a list of addresses associated with a user based on the user's username.
- Parameters:
    - `username` (String): The username of the user.
- Return Type: `Optional<List<Address>>`
- Notes: Returns an empty `Optional` if no addresses are found for the user.


## Service

The `AddressService` class is responsible for handling various operations related to user addresses. It provides methods for retrieving, creating, updating, and deleting addresses, as well as performing other address-related tasks. Utilized by the `AddressController` class to help manipulate the database via the endpoints created in the controller.

### Injected Dependencies

- **`addressRepository` (AddressRepository)**
    - Description: A repository used for database operations related to addresses.


- **`userRepository` (UserRepository)**
    - Description: A repository used for database operations related to users.


- **`tokenService` (TokenService)**
    - Description: A service for handling user tokens and authentication.

### Methods

#### `getAllAddresses()`

- Description: Retrieves a list of all addresses.
- Return Type: `List<Address>`
- Notes: This method fetches all addresses from the database.

#### `getAddressById(int id)`

- Description: Retrieves an address by its unique ID.
- Parameters:
    - `id` (int): The ID of the address to retrieve.
- Return Type: `ResponseAddress`
- Notes: This method returns a `ResponseAddress` object and handles cases where the address is not found by throwing an `Exception` with the `HttpStatus` of `404`.

#### `getAddressByUserId(int id)`

- Description: Retrieves a list of addresses associated with a user by the user's ID.
- Parameters:
    - `id` (int): The ID of the user.
- Return Type: `List<ResponseAddress>`
- Notes: This method returns a list of `ResponseAddress` objects and handles cases where the user is not found throwing an `Exception` with the `HttpStatus` of `404`.

#### `createAddress(CreateAddress address, String tokenHeader)`

- Description: Creates a new address for the authenticated user.
- Parameters:
    - `address` (CreateAddress): The address information to create.
    - `tokenHeader` (String): The user's authentication token.
- Return Type: `ResponseAddress`
- Notes: This method creates an address associated with the authenticated user and handles errors when the user or address creation fails throwing an `Exception` with the `HttpStatus` of `400`.

#### `getAddressByUser(String token)`

- Description: Retrieves a list of addresses associated with the authenticated user.
- Parameters:
    - `token` (String): The user's authentication token.
- Return Type: `List<ResponseAddress>`
- Notes: This method returns a list of `ResponseAddress` objects and handles cases where the user is not found throwing an `Exception` with the `HttpStatus` of `404`.

#### `deleteUser(int id)`

- Description: Deletes an address by its ID.
- Parameters:
    - `id` (int): The ID of the address to delete.
- Return Type: `boolean`
- Notes: This method attempts to delete the address and returns `true` if successful, or it handles errors when the address deletion fails throwing an `Exception` with the `HttpStatus` of `404`.

#### `updateAddress(int id, CreateAddress newAddress)`

- Description: Updates an address with the specified ID.
- Parameters:
    - `id` (int): The ID of the address to update.
    - `newAddress` (CreateAddress): The updated address information.
- Return Type: `ResponseAddress`
- Notes: This method updates the address attributes and returns a `ResponseAddress` object or handles errors when the address is not found throwing an `Exception` with the `HttpStatus` of `404`.
