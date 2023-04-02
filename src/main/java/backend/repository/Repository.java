package backend.repository;

import java.util.List;

public interface Repository<T> {

    T get(String name);
    List<T> getAll();
}
