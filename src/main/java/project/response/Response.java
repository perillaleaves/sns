package project.response;

import lombok.Getter;

@Getter
public class Response<T> {

    private T data;

    public Response(T data) {
        this.data = data;
    }

}