package nl.lunchtag.resource.Lunchtag.controller.enums;

public enum LunchResponse {
    INVALID_PARAMS("Invalid parameters"),
    UNEXPECTED_ERROR("A unexpected error occurred. Try again later"),
    WRONG_CREDENTIALS("Wrong credentials"),
    NO_LUNCHES("No Lunches were found"),
    NO_LUNCH("No Lunch was found");

    private final String text;

    LunchResponse(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}