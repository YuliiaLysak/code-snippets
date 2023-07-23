package edu.lysak.generics.reflection.bridge;

class Data<T> {
    private T data;

    public T get() {
        return data;
    }

    public void set(T data) {
        this.data = data;
    }
}
