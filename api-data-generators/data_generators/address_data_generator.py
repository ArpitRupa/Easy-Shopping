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
