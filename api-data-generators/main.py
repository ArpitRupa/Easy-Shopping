import sys

from data_generators import user_data_generator, address_data_generator
from api_client import api_requests


def generate_data(script_name, integer_arg):
    if script_name == "users":
        data = user_data_generator.generate_users(integer_arg)
        tag = "users"
    elif script_name == "addresses":
        create_addresses = input(
            "Do you want to create addresses for a particular user? (yes/no): ")
        if create_addresses.lower() == "yes" or create_addresses.lower() == "y":
            try:
                user_id = int(input("Enter the user ID: "))
                data = address_data_generator.generate_addresses_for_user(
                    user_id, integer_arg)
                tag = "addresses/user"
            except ValueError:
                print("Invalid user ID. Please provide a valid integer.")
                sys.exit(1)
        else:
            data = address_data_generator.generate_addresses(integer_arg)
            tag = "addresses"

    api_requests.send_post_req_to_api(data, tag)


def main():
    valid_scripts = ["users", "addresses"]

    if len(sys.argv) != 3:
        print("Usage: python script.py <script_name> <integer>")
        sys.exit(1)

    script_name = sys.argv[1]
    integer_arg = sys.argv[2]

    if script_name not in valid_scripts:
        print("Invalid script name. Choose from:", valid_scripts)
        sys.exit(1)

    try:
        integer_arg = int(integer_arg)
    except ValueError:
        print("Invalid integer argument. Please provide a valid integer.")
        sys.exit(1)

    data = generate_data(script_name, integer_arg)


if __name__ == "__main__":
    main()
