# Security

## Config

### CorsConfig

- The `CorsConfig` class is a configuration class that enables Cross-Origin Resource Sharing (CORS) for a web
  application. It allows defining the origins, methods, and headers that are allowed when making requests to the
  application from different domains.
- The `addCorsMappings` method configures the CORS settings for the application.

#### Methods

- **`addCorsMappings`**
- **Description**: Defines the CORS configuration, specifying the allowed origins, `HTTP` methods, and headers.
- **Configuration Details**:
    - **Allowed Origins**: This configuration allows requests from the `http://localhost:4200` origin; which is the
      location of our front-end running `Angular`.

    - **Allowed Methods**: HTTP methods such as `GET`, `POST`, `PUT`, `DELETE`, and `OPTIONS` are allowed for CORS
      requests.

    - **Allowed Headers**: The `Authorization` for the `bearer` token and `Content-Type` headers are permitted in CORS
      requests.

### WebSecurityConfig

- The `WebSecurityConfig` class defines security rules and settings for the application. It handles user authentication,
  CORS configuration, and OAuth 2.0 resource server settings.
- This configuration class plays a crucial role in securing the application, handling user authentication, and defining
  authorization rules.
- It utilizes JWT (JSON Web Tokens) for authentication and authorization for endpoints in the application.

#### Configuration Details

**Annotations**: `@Configuration`, `@EnableWebSecurity`

- Description: This class contains security configurations and settings for the application. It is enabled for web
  security.

- **Dependencies**: `RSAKeyProperties keys`
    - Description: The RSA key properties used for JWT token validation.

- **Constructor**:
    - **WebSecurityConfig(RSAKeyProperties keys)**
        - Description: `Autowired` Constructor to inject the RSA key properties for JWT token validation.

#### PasswordEncoder

- **Bean Name**: `passwordEncoder()`
- **Description**: Configures the password encoder for user passwords. It uses BCrypt encoding for secure password
  storage.

#### AuthenticationManager

- **Bean Name**: `authenticationManager(UserDetailsService userDetails)`
- **Description**: Configures the `AuthenticationManager` used for user authentication. It utilizes
  a `DaoAuthenticationProvider` to authenticate users via the `UserDetailsService`.

#### SecurityFilterChain

- **Method**: `securityFilterChain(HttpSecurity http)`
- **Description**: Defines the security filter chain, including CORS configuration, CSRF protection, and authorization
  rules.
- The `securityFilterChain` method configures security rules and OAuth 2.0 resource server settings. It specifies
  authorization rules based on request patterns and sets the session creation policy to STATELESS.

#### JWT Configuration

- **Bean Name**: `jwtDecoder()`
- **Description**: Creates a `JwtDecoder` configured to verify the digital signature of JWTs with the public key from
  the RSA key pair.


- **Bean Name**: `jwtEncoder()`
- **Description**: Creates and configures a `JwtEncoder` to sign JWTs using the RSA key pair.


- **Bean Name**: `jwtAuthenticationConverter()`
- **Description**: Creates and configures a `JwtAuthenticationConverter` to handle JWT authentication. It extracts user
  authorities from the JWT.

## Controller

### Authentication Controller

The `AuthenticationController` class is responsible for handling user authentication-related endpoints, such as user
registration, user login, and password updates, which are essential for user authentication and security in the
application.

#### Authorization

**Please note that the authorization requirements are as follows:**

- Register User: Not required (open endpoint).
- Login User: Not required (open endpoint).
- Update Password: Requires a valid JWT token with user authentication. Requires the 'ADMIN' or 'USER' role.

#### Endpoints

##### Register User

- **URL:** `/auth/register`
- **HTTP Method:** POST
- **Authorization:** Not required.
- **Description:** Registers a new user.
- **Request Body:** `UserRegistration` DTO.
- **Response:** Returns a `UserDto` object representing the registered user.

##### Login User

- **URL:** `/auth/login`
- **HTTP Method:** POST
- **Authorization:** Not required.
- **Description:** Logs in a user with the provided credentials.
- **Request Body:** User login details as a `LoginRequest` DTO.
- **Response:** Returns a `LoginResponse` object containing user information and a JWT token.

##### Update Password

- **URL:** `/auth/update-password`
- **HTTP Method:** PUT
- **Authorization:** Requires a valid JWT token in the request header. Requires the 'ADMIN' or 'USER' role.
- **Description:** Updates the user's password.
- **Request Body:** User's current and new password in JSON format.
- **Response:** Returns a `UserDto` object representing the user with the updated password.

## Model

### Security User

The `SecurityUser` class represents a user for authentication via Spring Security and implements the `UserDetails`
interface. It provides user details required for authentication and authorization via Spring Security.

### Fields

- **`username` (String)**
    - Description: The user's username.
- **`password` (String)**
    - Description: The user's password.
- **`role` (Role)**
    - Description: The user's role.

### Constructor

**`SecurityUser(String username, String password, Role role)`**

- Description: Creates a `SecurityUser` instance.

### Methods

- **`getPassword()`**
    - Description: Returns the user's password.
    - Return Type: `String`

- **`getUsername()`**
    - Description: Returns the user's username.
    - Return Type: `String`

- **`getAuthority()`**
    - Description: Returns the user's authority (role) as a `GrantedAuthority` object.
    - Return Type: `GrantedAuthority`

***Methods implemented for Spring Security `UserDetails` interface***

- **`getAuthorities()`**
    - Description: Returns the collection of granted authorities for the user.
    - Return Type: `Collection<? extends GrantedAuthority>`

- **`isAccountNonExpired()`**
    - Description: Checks if the user's account is not expired.
    - Return Type: `boolean`

- **`isAccountNonLocked()`**
    - Description: Checks if the user's account is not locked.
    - Return Type: `boolean`

- **`isCredentialsNonExpired()`**
    - Description: Checks if the user's credentials are not expired.
    - Return Type: `boolean`

- **`isEnabled()`**
    - Description: Checks if the user is enabled.
    - Return Type: `boolean`

## Service

### AuthenticationService

The `AuthenticationService` class provides methods for user authentication, registration, and password update.

#### Constructors

- *
  *`AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenService tokenService)`
  **
    - Description: Constructor to inject dependencies via `Autowired`.

#### Methods

- **`loginUser(String username, String password)`**
    - Description: Authenticate a user and generate a JWT token upon successful login.
    - Parameters:
        - `username` (String): The username provided for login.
        - `password` (String): The password provided for login.
    - Return Type: `LoginResponse`
    - Throws: `ResponseStatusException` via `HttpStatus 401` if authentication fails.


- **`createUser(UserRegistration userRegistration)`**
    - Description: Create a new user based on the provided user registration information.
    - Parameters:
        - `userRegistration` (UserRegistration): The user registration information, including first name, last name,
          email, username, and password.
    - Return Type: `UserDto`
    - Throws: `ResponseStatusException` via `HttpStatus 400` if user registration fails.


- **`updatePassword(UpdatePasswordDTO updatePasswordDTO, String token)`**
    - Description: Update the user's password.
    - Parameters:
        - `updatePasswordDTO` (UpdatePasswordDTO): The update password information, including the current password, new
          password, and confirm new password.
        - `token` (String): The user's authentication token.
    - Return Type: `UserDto`
    - Throws: `ResponseStatusException` via `HttpStatus 400` if the password update fails.

### SecurityUserService

The `SecurityUserService` class is used by Spring Security to retrieve user details for authentication. It implements
the `UserDetailsService` interface and is responsible for querying the database to load user details based on the
username.

#### Constructors

**`SecurityUserService(UserRepository userRepository, PasswordEncoder passwordEncoder)`**

- Description: Constructor to inject dependencies.

#### Methods

**`loadUserByUsername(String username)`**

- Description: Load user details from the database based on the provided username.
- Parameters:
- `username` (String): The username to retrieve from the database.
- Return Type: `SecurityUser`
- Throws: `UsernameNotFoundException` if the provided username is not found in the database.

### TokenService

The `TokenService` class is used to generate and decode JSON Web Tokens (JWTs) for user authentication. It provides
methods for generating JWTs based on user authentication information and extracting the username from a JWT token.

- `jwtEncoder` (JwtEncoder)
    - Description: The JWT encoder for generating JWTs.


- `jwtDecoder` (JwtDecoder)
    - Description: The JWT decoder for decoding JWTs.

#### Constructors

- **`TokenService()`**
    - Description: Default constructor.


- **`TokenService(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder)`**
    - Description: Constructor to inject dependencies.

#### Methods

- **`generateJwt(Authentication authentication)`**
    - Description: Generates a JSON Web Token (JWT) for the provided authentication.
    - Parameters:
        - `authentication` (Authentication): The authentication object representing the user's credentials and
          authorities.
    - Return Type: `String`
    - Generates a JWT string containing user-specific claims, including the user's role and username.


- **`getUsernameFromToken(String jwtTokenWithHeader)`**
    - Description: Extracts the username from a JWT token.
    - Parameters:
        - `jwtTokenWithHeader` (String): The JWT token with the "Bearer" header.
    - Return Type: `String`
    - Returns the username (subject) extracted from the JWT.

## Util

### KeyGenerator

The `KeyGenerator` class provides a method for generating an RSA key pair.

#### Methods

- **`generateRsaKey()`**
    - Description: Generates an RSA key pair.
    - Return Type: `KeyPair`
    - Throws:
        - `NoSuchAlgorithmException`: If the RSA algorithm is not available.
        - `InvalidParameterException`: If the key size is invalid.
    - Generates an RSA key pair, including both the public and private keys. The method initializes the key pair generator with a key size of **2048 bits**.

### RSAKeyProperties

The `RSAKeyProperties` class is responsible for managing RSA key properties, including the public and private keys.

#### Fields
- `publicKey`
  - Description: The RSA public key.
- `privateKey`
  - Description: The RSA private key.

#### Constructors

- **`RSAKeyProperties()`**
    - Description: Initializes the RSA key properties by generating an RSA key pair via the `private` method `initializeKeys()`.

#### Methods

- **`getPublicKey()`**
    - Description: Retrieves the RSA public key.
    - Return Type: `RSAPublicKey`

- **`getPrivateKey()`**
    - Description: Retrieves the RSA private key.
    - Return Type: `RSAPrivateKey`