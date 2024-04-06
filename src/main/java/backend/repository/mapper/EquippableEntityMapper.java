package backend.repository.mapper;

import backend.repository.entity.EquippableEntity;
import backend.repository.interfaces.IEquippableEntity;
import backend.repository.interfaces.IMapperEntity;

public final class EquippableEntityMapper implements IMapperEntity<IEquippableEntity> {

    @Override
    public IEquippableEntity apply(String line) {
        var data = split(line);
        return new EquippableEntity(
                Convert.parseInt(data[0]),
                Convert.parseInt(data[1]),
                Convert.parseInt(data[2]));
    }

}

