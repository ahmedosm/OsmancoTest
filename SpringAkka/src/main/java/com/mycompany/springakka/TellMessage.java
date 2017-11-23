/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.springakka;

import java.io.Serializable;

/**
 *
 * @author ahmed.anwer
 */
public class TellMessage  implements Serializable{
    String message;
    public  TellMessage(String message){
        this.message=message;
    }

    @Override
    public String toString() {
        return "TellMessage{" + "message=" + message + '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
