package project.response;

import lombok.Getter;

@Getter
public class ValidationResponse {

    private String code;

    private String message;

    public ValidationResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

}