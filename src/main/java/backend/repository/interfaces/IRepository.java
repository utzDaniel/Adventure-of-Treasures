package backend.repository.interfaces;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface IRepository<T, I extends Serializable> {

    Optional<T> getById(I id);

    List<T> getAll();

}
