package com.dndy.model;

public class MJwt {

    /**
     * id
     */
    private String jti;

    /**
     * iss
     */
    private String iss;

    /**
     * sub
     */
    private String sub;

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getJti() {
        return jti;
    }

    public void setJti(String jti) {
        this.jti = jti;
    }

    public String getIss() {
        return iss;
    }

    public void setIss(String iss) {
        this.iss = iss;
    }

}
