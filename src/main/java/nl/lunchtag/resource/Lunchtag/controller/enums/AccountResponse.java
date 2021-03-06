package nl.lunchtag.resource.Lunchtag.controller.enums;

public enum AccountResponse {
    INVALID_PARAMS("Invalid parameters"),
    UNEXPECTED_ERROR("A unexpected error occurred. Try again later"),
    WRONG_CREDENTIALS("Wrong credentials"),
    NO_ACCOUNTS("No Accounts were found"),
    NO_ACCOUNT("No Account was found");

    private final String text;

    AccountResponse(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
