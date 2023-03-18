package repository;

public interface Repository<T> {

    T get(String name);
}
