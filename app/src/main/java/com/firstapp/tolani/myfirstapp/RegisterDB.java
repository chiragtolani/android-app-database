
package com.firstapp.tolani.myfirstapp;

public class RegisterDB {

    private String _username;
    private String _password;


    public RegisterDB(String username, String password) {
        _username = username;
        _password = password;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    public String get_username() {
        return _username;
    }

    public void set_username(String _username) {
        this._username = _username;
    }
}
