package project.common.response;

import lombok.Getter;

@Getter
public class ValidationResponse {

    private final String code;

    private final String message;

    public ValidationResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

}