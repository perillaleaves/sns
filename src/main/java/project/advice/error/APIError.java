package project.advice.error;

public class APIError extends Error {

    private final String code;

    public String getCode() {
        return code;
    }

    public APIError(String code, String message) {
        super(message);
        this.code = code;
    }

}