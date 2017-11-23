/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.osmanco.springakka.dto;



/**
 *
 * @author ahmed.anwer
 */
public class AddUserDTO {
    private String msisdn ;
    private String  delegationMsisdn;

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getDelegationMsisdn() {
        return delegationMsisdn;
    }

    public void setDelegationMsisdn(String delegationMsisdn) {
        this.delegationMsisdn = delegationMsisdn;
    }

    @Override
    public String toString() {
        return "AddUserDTO{" + "msisdn=" + msisdn + ", delegationMsisdn=" + delegationMsisdn + '}';
    }
    
}
