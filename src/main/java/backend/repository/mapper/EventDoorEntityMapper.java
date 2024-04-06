package backend.repository.mapper;

import backend.repository.entity.EventDoorEntity;
import backend.repository.interfaces.IEventDoorEntity;
import backend.repository.interfaces.IMapperEntity;

public final class EventDoorEntityMapper implements IMapperEntity<IEventDoorEntity> {

    @Override
    public IEventDoorEntity apply(String line) {
        var data = split(line);
        return new EventDoorEntity(
                Convert.parseInt(data[0]),
                Convert.parseInt(data[1]),
                Convert.parseInt(data[2]),
                Convert.parseBoolean(data[3]));
    }

}

