
package com.osmanco.springakka.dto;

/**
 *
 * @author ahmed.anwer
 */
public class ResponseDTO {
    private String statusCode;
    private String eDescription;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String geteDescription() {
        return eDescription;
    }

    public void seteDescription(String eDescription) {
        this.eDescription = eDescription;
    }
    
}
