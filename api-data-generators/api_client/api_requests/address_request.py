import json
import requests

from .config import api_address


def post_address_to_api(address: dict, user_id: int, headers: dict) -> dict:

    endpoint = api_address + "/api/addresses/user/" + str(user_id)

    try:
        response = requests.post(
            endpoint,
            data=json.dumps(address, indent=4),
            headers=headers,
            timeout=10
        )

        if response.status_code == 200 or response.status_code == 201:
            print(response.json())
            return response
        else:
            response.raise_for_status()

    except requests.exceptions.RequestException as request_error:
        print("Failed to post data:", request_error)
