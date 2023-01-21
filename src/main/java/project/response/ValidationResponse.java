package project.response;

public class ValidationResponse {

    private String code;

    private String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public ValidationResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

}