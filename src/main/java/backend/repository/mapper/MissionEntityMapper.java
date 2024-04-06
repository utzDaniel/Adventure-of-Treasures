package backend.repository.mapper;

import backend.repository.entity.MissionEntity;
import backend.repository.interfaces.IMapperEntity;
import backend.repository.interfaces.IMissionEntity;

public final class MissionEntityMapper implements IMapperEntity<IMissionEntity> {

    @Override
    public IMissionEntity apply(String line) {
        var data = split(line);
        return new MissionEntity(
                Convert.parseInt(data[0]),
                Convert.parseInt(data[1]));
    }

}

