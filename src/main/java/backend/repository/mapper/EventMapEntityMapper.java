package backend.repository.mapper;

import backend.repository.entity.EventMapEntity;
import backend.repository.interfaces.IEventMapEntity;
import backend.repository.interfaces.IMapperEntity;

public final class EventMapEntityMapper implements IMapperEntity<IEventMapEntity> {

    @Override
    public IEventMapEntity apply(String line) {
        var data = split(line);
        return new EventMapEntity(
                Convert.parseInt(data[0]),
                Convert.parseInt(data[1]),
                Convert.parseInt(data[2]),
                Convert.parseInt(data[3]));
    }

}

