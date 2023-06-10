package backend.repository.interfaces;

import java.io.Serializable;
import java.util.List;

public interface IRepository<T, I extends Serializable> {

    T get(I id);

    List<T> getAll();
}
