package backend.repository.mapper;

import backend.repository.entity.UsableEntity;
import backend.repository.interfaces.IMapperEntity;
import backend.repository.interfaces.IUsableEntity;

public final class UsableEntityMapper implements IMapperEntity<IUsableEntity> {

    @Override
    public IUsableEntity apply(String line) {
        var data = split(line);
        return new UsableEntity(
                Convert.parseInt(data[0]),
                Convert.parseInt(data[1]),
                Convert.parseInt(data[2]),
                Convert.parseInt(data[3]),
                Convert.parseInt(data[4]),
                Convert.parseBoolean(data[5]));
    }

}

