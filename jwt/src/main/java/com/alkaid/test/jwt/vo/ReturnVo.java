package com.alkaid.test.jwt.vo;

public class ReturnVo {

    private String jwt;

    ReturnVo(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public static ReturnVo ok(String jwt) {
        return new ReturnVo(jwt);
    }

    public static ReturnVo error() {
        return new ReturnVo(null);
    }
}
