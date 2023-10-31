import json
import requests

from config import api_address


def post_address_to_api(address: dict, user_id: int, headers: dict) -> dict:

    url = api_address + "/api/addresses/" + str(user_id)

    try:
        response = requests.post(
            url, data=json.dumps(address), headers=headers,)

        if response.status_code == 200 or response.status_code == 201:
            return response.json()
        else:
            response.raise_for_status()

    except requests.exceptions.RequestException as request_error:
        print("Failed to retrieve data:", request_error)
