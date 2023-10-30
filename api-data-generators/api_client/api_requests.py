import os
import json
import requests

from dotenv import load_dotenv

load_dotenv()

api_address = os.environ.get("API_ADDRESS")

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
            endpoint = api_address + "/auth/register"

        # make helper functions to get all users
        # maybe think about doing seperate post methods for each data type
        # and then have this function call them depending on the tag
        # posts to /addresses/user/id
        case 'addresses':
            endpoint = api_address + "/api/addresses"
            headers['Authorization'] = f'Bearer {admin_token}'
            users = get_all_request_from_api("users")
            user_ids = [user['id'] for user in users]

        case "addresses/user":
            endpoint = api_address + "/api/addresses/user"
            headers['Authorization'] = f'Bearer {admin_token}'

        case _:
            raise ValueError("Invalid data value")

    total_requests = len(data)

    # Iterate over data points and make POST requests
    for data_point in data:
        try:
            response = requests.post(
                endpoint,
                data=json.dumps(data_point, indent=4),
                headers=headers,
                timeout=10
            )
            response_list.append(response.status_code)
            print(f"Data: {data_point}")
        except requests.exceptions.RequestException as error_code:
            print("Error:", error_code)

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

        if response.status_code == 200:
            data = response.json().get('content')
            if data is not None:
                return data

        print(
            f"Could not get data. Request returned status code: {response.status_code}")

    except requests.exceptions.RequestException as request_error:
        print("Failed to retrieve data:", request_error)

    return None
