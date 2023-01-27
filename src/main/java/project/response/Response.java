package project.response;

import lombok.Getter;

@Getter
public class Response<T> {

    private T data;
    private ErrorResponse error;

    public Response(T data) {
        this.data = data;
    }

    public Response(ErrorResponse error) {
        this.error = error;
    }


}