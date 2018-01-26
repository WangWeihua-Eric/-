package com.eric.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AppConfig {

    private final String organization;
    private final String name;
    private final String token;
    private final AppServer server;
    private final Map<String, String> env;

    @JsonCreator
    public AppConfig(@JsonProperty("organization") String organization,
                     @JsonProperty("name") String name,
                     @JsonProperty("token") String token,
                     @JsonProperty("server") AppServer server,
                     @JsonProperty("env") Map<String, String> env) {
        this.organization = organization;
        this.name = name;
        this.token = token;
        this.server = server;
        if (env == null)
            this.env = env;
        else
            this.env = Collections.unmodifiableMap(new HashMap<String, String>(env));
    }

    public String getOrganization() {
        return organization;
    }

    public String getName() {
        return name;
    }

    public String getToken() {
        return token;
    }

    public AppServer getServer() {
        return server;
    }

    public Map<String, String> getEnv() {
        return env;
    }
}