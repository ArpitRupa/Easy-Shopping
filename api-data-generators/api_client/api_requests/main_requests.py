import os
import json
import random
import requests

from dotenv import load_dotenv
from .config import api_address
from .address_request import post_address_to_api
from .user_request import register_user


load_dotenv()

admin_login = {
    "username": os.environ.get("ADMIN_USERNAME"),
    "password": os.environ.get("ADMIN_PASSWORD")
}

headers = {'Content-Type': 'application/json'}

# simulate an admin user login; needed for some generators
admin_login = requests.post(
    f"{api_address}/auth/login", data=json.dumps(admin_login, indent=4), headers=headers)

# store the jwt to use for requests
admin_token = admin_login.json()["jwt"]


def send_post_req_to_api(data: list, tag: str) -> list:

    # store the responses for the post requests
    response_list = []

    match tag:
        case 'users':

            for data_point in data:
                response = register_user(data_point, headers)
                response_list.append(response.status_code)

        # make helper functions to get all users
        # maybe think about doing seperate post methods for each data type
        # and then have this function call them depending on the tag
        # posts to /addresses/user/id

        # gnerate addresses for random users
        case 'addresses':
            headers['Authorization'] = f'Bearer {admin_token}'

            # get all users from the backend
            users = get_all_request_from_api("users")

            # get all user ids from the response
            user_ids = [user['id'] for user in users]

            for address in data:
                random_user_id = random.choice(user_ids)
                response = post_address_to_api(
                    address, random_user_id, headers)
                response_list.append(response.status_code)

        # generate addresses for a particular user
        case "addresses/user":
            headers['Authorization'] = f'Bearer {admin_token}'

            if data[0].get('userID') is not None:
                user_id = data[0].get('userID')
                for data_point in data:
                    response = post_address_to_api(
                        data_point, user_id, headers)
                    response_list.append(response.status_code)
        case _:
            raise ValueError("Invalid data value")

    total_requests = len(data)

    # Count successful responses (status codes 200 or 201)
    success_count = response_list.count(200) + response_list.count(201)

    print(f"{success_count} out of {total_requests} datapoints correctly posted.")
    print(f"Responses: {response_list}")

    return response_list


def get_all_request_from_api(tag: str):
    match tag:
        case 'users':
            endpoint = api_address + "/api/users"
        case 'addresses':
            endpoint = api_address + "/api/addresses"
        case _:
            raise ValueError("Invalid data value")

    headers['Authorization'] = f'Bearer {admin_token}'

    try:
        response = requests.get(endpoint, timeout=10, headers=headers)

        if response.status_code == 200 or response.status_code == 201:
            data = response.json()
            if data is not None:
                return data

        print(
            f"Could not get data. Request returned status code: {response.status_code}")

    except requests.exceptions.RequestException as request_error:
        print("Failed to retrieve data:", request_error)

    return None
