

from data_generators.address_data_generator import generate_addresses, generate_addresses_for_user


def test_generate_addresses_valid_quantity():
    quantity = 5
    addresses = generate_addresses(quantity)
    assert len(addresses) == quantity


def test_generate_addresses_for_user_valid_quantity():
    user_id = 1
    quantity = 5
    addresses = generate_addresses_for_user(user_id, quantity)
    assert len(addresses) == quantity


def test_generate_addresses_structure():
    quantity = 1
    addresses = generate_addresses(quantity)
    address = addresses[0]
    assert "shippingAddressLine1" in address
    assert "shippingAddressLine2" in address
    assert "city" in address
    assert "stateName" in address
    assert "postalCode" in address


def test_generate_addresses_for_user_structure():
    user_id = 1
    quantity = 1
    addresses = generate_addresses_for_user(user_id, quantity)
    address = addresses[0]
    assert "userID" in address
    assert "shippingAddressLine1" in address
    assert "shippingAddressLine2" in address
    assert "city" in address
    assert "stateName" in address
    assert "postalCode" in address


def test_generate_addresses_for_user_user_id():
    user_id = 1
    quantity = 1
    addresses = generate_addresses_for_user(user_id, quantity)
    address = addresses[0]
    assert address["userID"] == user_id
