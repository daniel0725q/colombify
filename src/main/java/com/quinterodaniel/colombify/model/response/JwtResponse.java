package com.quinterodaniel.colombify.model.response;

import lombok.Getter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Getter
public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final Map data;

    public JwtResponse(String jwttoken) {
        this.data = new HashMap();
        this.data.put("token", jwttoken);
    }
}
