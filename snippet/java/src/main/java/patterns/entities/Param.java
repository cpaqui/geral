package patterns.entities;

import java.util.Optional;

public class Param<T> {

    private Optional<T> data;

    public Param(Optional<T> data) {
        this.data = data;
    }

    public Optional<T> getData() {
        return data;
    }

    public void setData(Optional<T> data) {
        this.data = data;
    }
}
