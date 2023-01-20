package project.response;

import lombok.Getter;

@Getter
public class Response<T> {

    private T data;
    private ErrorResponse error;
    private ValidationResponse validation;

    public Response(T data) {
        this.data = data;
    }

    public Response(ErrorResponse error) {
        this.error = error;
    }

    public Response(ValidationResponse validation) {
        this.validation = validation;
    }
}