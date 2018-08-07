package com.example.syl.grmr.Constructor;

/**
 * Created by SYL on 2018-03-26.
 */

public class Contributor {

    private final String access_token;

    public Contributor (String access_token) {
        this.access_token = access_token;
    }

    public String getToken() {
        return access_token;
    }

}
