from data_generators.user_data_generator import generate_users


def test_generate_users_valid_quantity():
    quantity = 5
    users = generate_users(quantity)
    assert len(users) == quantity


def test_generate_users_structure():
    quantity = 1
    users = generate_users(quantity)
    user = users[0]
    assert "firstName" in user
    assert "lastName" in user
    assert "email" in user
    assert "username" in user
    assert "password" in user
