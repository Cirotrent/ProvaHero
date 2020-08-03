package com.provahero.provahero.web.dto.utente;

public class UtenteCheAccede {
    private  String user;
    private  String password;

    @Override
    public String toString() {
        return "UtenteCheAccede{" +
                "user='" + user + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
