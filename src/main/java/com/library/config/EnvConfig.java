package com.library.config;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvConfig {
    private static final Dotenv dotenv = Dotenv.load();

    public static String getAuth0Domain() {
        return dotenv.get("AUTH0_DOMAIN");
    }

    public static String getAuth0ClientId() {
        return dotenv.get("AUTH0_CLIENT_ID");
    }

    public static String getAuth0ClientSecret() {
        return dotenv.get("AUTH0_CLIENT_SECRET");
    }
}
