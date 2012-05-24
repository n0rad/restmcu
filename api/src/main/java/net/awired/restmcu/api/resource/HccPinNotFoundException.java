package net.awired.restmcu.api.resource;

public class HccPinNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public HccPinNotFoundException(String message) {
        super(message);
    }
}
