package backend.repository.mapper;

import backend.repository.entity.CombinableEntity;
import backend.repository.interfaces.ICombinableEntity;
import backend.repository.interfaces.IMapperEntity;

public final class CombinableEntityMapper implements IMapperEntity<ICombinableEntity> {

    @Override
    public ICombinableEntity apply(String line) {
        var data = split(line);
        return new CombinableEntity(
                Convert.parseInt(data[0]),
                Convert.parseInt(data[1]),
                Convert.parseInt(data[2]));
    }

}

