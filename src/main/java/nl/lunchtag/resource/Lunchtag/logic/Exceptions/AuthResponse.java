package nl.lunchtag.resource.Lunchtag.logic.Exceptions;

public enum AuthResponse {
    INVALID_PARAMS("Invalid parameters"),
    UNEXPECTED_ERROR("A unexpected error occurred. Try again later"),
    WRONG_CREDENTIALS("Wrong credentials"),
    USER_ALREADY_EXISTS("A user with this email already exists"),
    NO_PREMISSIONS("No Premission to execute"),
    DISABLED("Your account has been disabled");

    private final String text;

    AuthResponse(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
