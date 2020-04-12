package com.alkaid.test.jwt.vo;

import io.jsonwebtoken.Claims;

public class CheckResult {

    public static final String JWT_ERRCODE_EXPIRE = "expire";
    public static final String JWT_ERRCODE_FAIL = "fail";

    private boolean success;
    private String errCode;
    private Claims claims;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public Claims getClaims() {
        return claims;
    }

    public void setClaims(Claims claims) {
        this.claims = claims;
    }
}
