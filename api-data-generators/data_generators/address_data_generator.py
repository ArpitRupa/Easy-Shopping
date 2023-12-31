from faker import Faker

fake = Faker()


def generate_addresses(quantity: int) -> list:
    """
    Generate a list of fake address data.

    Args:
        quantity (int): The number of address data entries to generate.

    Returns:
        list: A list of dictionaries, where each dictionary represents a fake address
              with attributes including 'shippingAddressLine1', 'shippingAddressLine2', 'city', 'stateName', 
              and 'postalCode'.
    """

    addresses = []
    for _ in range(quantity):
        address = {
            "shippingAddressLine1": fake.street_address(),
            "shippingAddressLine2": fake.secondary_address(),
            "city": fake.city(),
            "stateName": fake.state(),
            "postalCode": fake.zipcode(),
        }
        addresses.append(address)

    return addresses


def generate_addresses_for_user(user_id: int, quantity: int) -> list:
    """
    Generate a list of fake address data for a particular user.

    Args:
        user_id (int): The ID of the user to generate addresses for.
        quantity (int): The number of address data entries to generate.

    Returns:
        list: A list of dictionaries, where each dictionary represents a fake address
              with attributes including 'userId', including 'shippingAddressLine1', 
              'shippingAddressLine2', 'city', 'stateName', and 'postalCode'.
    """

    addresses = generate_addresses(quantity)

    for address in addresses:
        address["userID"] = user_id

    return addresses
