import json
import requests
from config import api_address


def register_user(user: dict, headers: dict) -> dict:

    endpoint = api_address + "/auth/register"

    try:
        response = requests.post(
            endpoint,
            data=json.dumps(user, indent=4),
            headers=headers,
            timeout=10
        )

        # print user in event of success
        if response.status_code == 200 or response.status_code == 201:
            print(f"Data: {user}")
            return response.json()
        # print error code and user in event of error
        else:
            response.raise_for_status()
            print(f"Data: {user} - {response.status_code}")
    except requests.exceptions.RequestException as error_code:
        print("Error:", error_code)
