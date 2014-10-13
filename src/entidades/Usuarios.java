/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author cardel
 */
public class Usuarios {
    private int user_id;
    private String login;
    private String password;
    private String creation_data;
    private char status;

    public Usuarios(int user_id) {
        this.user_id = user_id;
    }

    public Usuarios(int user_id, String login, String password, String creation_data, char status) {
        this.user_id = user_id;
        this.login = login;
        this.password = password;
        this.creation_data = creation_data;
        this.status = status;
    }

    public int getUser_id() {
        return user_id;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreation_data() {
        return creation_data;
    }

    public void setCreation_data(String creation_data) {
        this.creation_data = creation_data;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }


    
}
