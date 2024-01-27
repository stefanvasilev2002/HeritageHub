package com.finki.heritagehub.model;
import java.util.UUID;
public class ConfirmationTokenGenerator {
    // TODO: CHANGE BASE URL AFTER HOST
    //public static final String BASE_URL = "http://localhost:7070/confirmation/confirm?token=";
    public static final String BASE_URL = "https://heritage-hub-mk-deb79ca264f9.herokuapp.com/confirmation/confirm?token=";

    public static String generateToken() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
