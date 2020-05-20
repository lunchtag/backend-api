package nl.lunchtag.resource.Lunchtag.controller.enums;

public enum LogResponse {
    NO_LOGS("No logs were found");


    private final String text;
    LogResponse(final String text){
        this.text = text;
    }

    @Override
    public String toString(){
        return text;
    }
}
