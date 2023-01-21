package project.response;


public class Response<T> {

    private T data;
    private ErrorResponse error;
    private ValidationResponse validation;

    public T getData() {
        return data;
    }

    public ErrorResponse getError() {
        return error;
    }

    public ValidationResponse getValidation() {
        return validation;
    }

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