from faker import Faker

fake = Faker()


fake = Faker()


def generate_user(quantity: int) -> list:
    """
    Generate a list of fake user data.

    Args:
        quantity (int): The number of user data entries to generate.

    Returns:
        list: A list of dictionaries, where each dictionary represents a fake user
              with attributes including 'firstName', 'lastName', 'email', 'username', and
              'password'.
    """
    users = []

    for _ in range(quantity):
        user = {
            "firstName": fake.first_name(),
            "lastName": fake.last_name(),
            "email": fake.email(),
            "username": fake.user_name(),
            "password": fake.password(),
        }
        users.append(user)

    return users
