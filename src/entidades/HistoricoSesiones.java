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
public class HistoricoSesiones {
    private int session_id;
    private int user_id;
    private String date;

    public HistoricoSesiones(int session_id, int user_id, String date) {
        this.session_id = session_id;
        this.user_id = user_id;
        this.date = date;
    }

    public int getSession_id() {
        return session_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
    
}
