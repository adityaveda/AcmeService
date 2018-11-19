package com.aditya.Acme.model;

//Certificate Entity
public class Certificate {
    private String domain;
    private String uuID;
    private String expiration;


    public Certificate(String domain, String uuID, String expiration) {
        this.domain = domain;
        this.uuID = uuID;
        this.expiration = expiration;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getUuID() {
        return uuID;
    }

    public void setUuID(String uuID) {
        this.uuID = uuID;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }
}
