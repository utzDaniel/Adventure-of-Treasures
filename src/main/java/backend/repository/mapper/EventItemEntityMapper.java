package backend.repository.mapper;

import backend.repository.entity.EventItemEntity;
import backend.repository.interfaces.IEventItemEntity;
import backend.repository.interfaces.IMapperEntity;

public final class EventItemEntityMapper implements IMapperEntity<IEventItemEntity> {

    @Override
    public IEventItemEntity apply(String line) {
        var data = split(line);
        return new EventItemEntity(
                Convert.parseInt(data[0]),
                Convert.parseInt(data[1]),
                Convert.parseInt(data[2]));
    }

}

