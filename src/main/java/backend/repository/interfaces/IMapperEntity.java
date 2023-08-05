package backend.repository.interfaces;

import java.util.function.Function;

public interface IMapperEntity<T extends IEntity> extends Function<String, T> {

    default String[] split(String linha) {
        return linha.split(";");
    }
}
