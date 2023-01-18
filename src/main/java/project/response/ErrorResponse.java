package project.response;

public class ErrorResponse {

    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}