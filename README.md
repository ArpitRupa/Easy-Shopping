# Easy Shopping Application

## Introduction

Welcome to my personal full-stack shopping application! This project is designed to provide a comprehensive shopping
experience for users, allowing them to browse, buy products, and even create their own listings to sell their products.

## Features

- User Authentication: Users can create accounts, log in, and manage their profiles.
- Product Catalog: Display a list of products with details, images, and pricing.
- Shopping Cart: Add and remove items from the shopping cart.
- Checkout and Payment: Users can complete purchases securely.
- User Listings: Allow users to create their own product listings to sell.
- Order History: Keep track of past orders.
- Admin Panel: Manage product listings and user accounts.
- Search and Filters: Allow users to search for products and apply filters.

## Technologies Used

- Frontend:
    - HTML, CSS, TypeScript
    - [Angular](https://angular.io/) for building the user interface.
    - [Material-UI](https://material.angular.io/) for pre-made color schemes and components.
- Backend:
    - [Java Spring Boot](https://spring.io/projects/spring-boot) for the server.
    - [MySQL](https://www.mysql.com/) for the database.
    - [JWT](https://jwt.io/) for authentication.
- Deployment:
    - TBD

## Table of Contents
- [User Experience](#user-experience)
  - [Creating a New User](#creating-a-new-user)
  - [Login](#login)
  - [Navigation](#navigation)
  - [Shopping](#shopping)
  - [Checking Out](#checking-out)
  - [Account Management](#account-management)
    - [Overview](#overview)
    - [Dashboard](#dashboard)
    - [Addresses](#addresses)
      - [Add Address](#add-address)
      - [Edit Address](#edit-address)
      - [Delete Address](#delete-address)
    - [Update Information](#update-information)
    - [Change Password](#change-password)
    - [Orders](#orders)
  - [Posting a Listing](#posting-a-listing)
- [Admin Experience](#admin-experience)
  - [Registration](#registration)
  - [Login](#login-1)
  - [Managing Users](#managing-users)
    - [Overview](#overview-1)
    - [Admin Panel](#admin-panel)
    - [Managing Listings](#managing-listings)


## User Experience

Here is the outline of the user experience along with screenshots of the process.

### Creating a New User

When a user wishes to create a new account, they are presented with the following page at the `/register` URL:

<div align="center">
  <img src="./images/auth/user-registration.png" alt="user_register" width="500">
</div>


The form requires that all fields are filled while meeting specific validations for each field.

The `Password` and `Confirm Password` fields must match to ensure the user is providing an intended password.

The `email` field must provide a valid email format.

Both the `Username` and `Email` provided must be unique (cannot already exist in the backend).

Upon clicking `Submit`, if all the validation parameters are met, a new user will be created in the backend.

### Login

When a user wishes to login they are presented with the following page at the `/login` URL:

<div align="center">
  <img src="./images/auth/user-login.png" alt="user_login" width="500">
</div>

The user must provide the `Username` and `Password` of the account they are trying to access.

If the User does not have an account, they can click `Register` which will forward them to the `/register` URL to create a new user.

Upon providing the correct `Username` and `Password` the client will be provided with a `JWT` and mark the user as authenticated and forward them to the home page.


### Navigation

The navigation is handled in the header of the application. 

The `nav-bar` is a dynamic component that will render either text for navigation or a button that will display a drop-down of options once clicked.

The `nav-bar` also renders different options depending on if the user is logged in and what role they possess.

The style of the `nav-bar` displayed to the user depends on the screen size of the user's device.

<div align="center">
  <img src="./images/nav/nav-expanded.png" alt="nav_expanded" width="500">
  <div>
Expanded nav-bar for desktop sized screens.
</div>
</div>

<div align="center">
  <img src="./images/nav/nav-collapsed.png" alt="nav_collapsed" width="500">
<div>
Collapsed nav-bar for mobile sized screens.
</div>
</div>

<div align="center">
  <img src="./images/nav/nav-elements.png" alt="nav_collapsed" width="100">
<div>
Drop down options from mobile nav-bar's collapsed button.
</div>
</div>


### Shopping

    <place holder>

### Checking Out

    <place holder>  

### Account Management

#### Overview

The accounts panel is where users can do things like:

- Update their information
- Change their password
- See their past orders
- Make changes to their addresses

##### Dashboard

When a user initially gets to the accounts panel, they will see the following:
<div align="center">
<img src="./images/account/account-dashboard.png" alt="account_dashboard" width="500">
</div>

The information presented is as follows:

  - The **username** in bold
  - The user's **full name**
  - The user's **email**
  - The user's **current role/authorization**

The user is also presented with buttons they can click to navigate to pages where where can perform various actions on their account.

##### Addresses

Clicking on the `Addresses` button, renders a children-route component (`/account/addresses`) of the `/account` route. 

This will render the following:

<div align="center">
<img src="./images/account/account-addresses.png" alt="account_addresses" width="350">
</div>

In this route, all the user's associated addresses are rendered in individual `cards`. 

Users can add a new address to their account by clicking the green "`+`" button. 

Users can also edit or delete specific addresses by clicking on the blue or red buttons respectively. 

###### Add Address

<div align="center">
<img src="./images/account/account-add-address.png" alt="account_add_address" width="300">
</div>

Upon clicking the green "+" button, the `address-form-component` is rendered as a `dialog`. 

The form handles validation and will not allow users to input invalid characters, or submit and invalid form.

Upon clicking "Submit" the valid `address` will be added to the database and the window will refresh, displaying the new `address`. 

###### Edit Address

<div align="center">
<img src="./images/account/account-edit-address.png" alt="account_edit_address" width="300">
</div>

Similar to adding an address, upon clicking the blue button of any card, will render a `address-form-component` prefilled with the specific address's information.

The form handles validation and will not allow users to input invalid characters, or submit and invalid form.

Upon clicking "Submit" the valid `address` will be edited and snet to the database and the window will refresh, displaying the edited `address`.


###### Delete Address


<div align="center">
<img src="./images/account/account-confirm-delete.png" alt="account_confirm_delete" width="300">
</div>

Upon clicking the delete button for any `address` card will render a `dialog` that will ask if the user is sure they want to delete the address.

Clicking "No" will close the `dialog` and clicking `Yes` will delete the address in the database and reload the window.


##### Update Information

Clicking on the `Update Information` button, renders a children-route component (`/account/updateInfo/{id}`) of the `/account` route.

This will render the following:


<div align="center">
<img src="./images/account/account-update-info.png" alt="account_update_info" width="300">
</div>

In this route, the user is presented pre-populated form that contains their current information.

Users can update the form, and provided they submit valid information in the fields, upon clicking "Submit" their information will be updated in the database.

##### Change Password

Clicking on the `Update Information` button, renders a children-route component (`/account/changePassword/{id}`) of the `/account` route.

This will render the following:

<div align="center">
  <img src="./images/account/account-change-password.png" alt="account-change-password" width="400">
</div>

In this route, the user is with an `changePassword` component that renders a form for the user to change their password.

Clicking the eyes on the right of each field will toggle the "show password" feature so users can see their inputs into the fields should they choose to do so.

The "Current Password" field will be authenticated by the backend, and the input for the "New Password" and "Confirm New Password" fields must match for the request to reach to backend.


##### Orders

    <place holder> 

### Posting a Listing

    <place holder> 

## Admin Experience

Here is the outline of the admin experience along with screenshots of the process.

### Registration

Admins cannot register for admin accounts directly.

Admins must be upgraded to admins from the admin panel by a current admin.


### Login

Admins log in through the same portal as normal users. 


<div align="center">
  <img src="./images/auth/user-login.png" alt="user_login" width="500">
</div>

### Managing Users

#### Overview
Admins are given to ability to manage users of the application.


#### Admin Panel

When an admin accesses the admin panel, they are greeted with the following page:

<div align="center">
  <img src="./images/admin/admin-panel.png" alt="admin_panel" width="400">
</div>

Upon clicking on any user's button the admin will be given options to manage the user.

Admins can manage user roles, or delete users from the system.

<div align="center">
  <img src="./images/admin/admin-edit-user.png" alt="admin_edit_user" width="400">
</div>

Upon picking any option, the application will ask the admin to confirm their action before sending the edits to the database on the backend.

<div align="center">
  <img src="./images/admin/admin-confirm.png" alt="admin_confirm" width="400">
</div>

#### Managing Listings

    <place holder> 

