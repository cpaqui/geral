package patterns.entities;

import java.util.Optional;

public class Param<T> {

    private T data;

    public Param(T data) {
        this.data = data;
    }

    public Optional<T> getData() {
        return Optional.of(data);
    }

    public void setData(T data) {
        this.data = data;
    }
}
