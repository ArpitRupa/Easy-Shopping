import sys
from io import StringIO
from unittest.mock import patch
from main import main, generate_data
import pytest


def test_generate_data_users():
    with patch('sys.argv', ['main.py', 'users', '1']):
        out = StringIO()
        sys.stdout = out
        main()
        output = out.getvalue()

        assert "1 out of 1 datapoints correctly posted." in output


def test_generate_data_users_multiple():
    with patch('sys.argv', ['main.py', 'users', '3']):
        out = StringIO()
        sys.stdout = out
        main()
        output = out.getvalue()

        assert "3 out of 3 datapoints correctly posted." in output


def test_generate_data_addresses_no():
    with patch('builtins.input', side_effect=['no']):
        with patch('sys.argv', ['main.py', 'addresses', '1']):
            out = StringIO()
            sys.stdout = out
            main()
            output = out.getvalue()

            assert "1 out of 1 datapoints correctly posted." in output


def test_generate_data_addresses_no_multiple():
    with patch('builtins.input', side_effect=['no']):
        with patch('sys.argv', ['main.py', 'addresses', '5']):
            out = StringIO()
            sys.stdout = out
            main()
            output = out.getvalue()

            assert "5 out of 5 datapoints correctly posted." in output


def test_generate_data_addresses_yes():
    with patch('builtins.input', side_effect=['yes', '5']):
        with patch('sys.argv', ['main.py', 'addresses', '1']):
            out = StringIO()
            sys.stdout = out
            main()
            output = out.getvalue()

            assert "1 out of 1 datapoints correctly posted." in output


def test_generate_data_addresses_yes_multiple():
    with patch('builtins.input', side_effect=['yes', '1']):
        with patch('sys.argv', ['main.py', 'addresses', '5']):
            out = StringIO()
            sys.stdout = out
            generate_data("addresses", 5)
            output = out.getvalue()

            assert "5 out of 5 datapoints correctly posted." in output


def test_generate_data_invalid_user_id(capsys):
    with patch('builtins.input', side_effect=['yes', 'not_an_integer']):
        with patch('sys.argv', ['main.py', 'addresses', '5']):
            with pytest.raises(SystemExit):
                main()

    output = capsys.readouterr().out
    assert "Invalid user ID. Please provide a valid integer." in output


def test_main_invalid_script_name(capsys):
    with patch('sys.argv', ['main.py', 'invalid_script', '5']):
        with pytest.raises(SystemExit):
            main()

    # Capture the printed output
    captured = capsys.readouterr()

    # Assert the printed error message
    assert "Invalid script name. Choose from: ['users', 'addresses']" in captured.out


def test_main_invalid_integer_arg(capsys):
    with patch('sys.argv', ['main.py', 'users', 'not_an_integer']):
        with pytest.raises(SystemExit):
            main()

    # Capture the printed output
    captured = capsys.readouterr()

    # Assert the printed error message
    assert "Invalid integer argument. Please provide a valid integer." in captured.out


def test_main_invalid_arg_size_too_small(capsys):
    with patch('sys.argv', ['main.py', 'users']):
        with pytest.raises(SystemExit):
            main()

    # Capture the printed output
    captured = capsys.readouterr()

    # Assert the printed error message
    assert "Usage: python script.py <script_name> <integer>" in captured.out


def test_main_invalid_arg_size_too_big(capsys):
    with patch('sys.argv', ['main.py', 'users', 1, 3, 4, 5, 6]):
        with pytest.raises(SystemExit):
            main()

    # Capture the printed output
    captured = capsys.readouterr()

    # Assert the printed error message
    assert "Usage: python script.py <script_name> <integer>" in captured.out
