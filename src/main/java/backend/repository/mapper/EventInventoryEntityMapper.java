package backend.repository.mapper;

import backend.repository.entity.EventInventoryEntity;
import backend.repository.interfaces.IEventInventoryEntity;
import backend.repository.interfaces.IMapperEntity;

public final class EventInventoryEntityMapper implements IMapperEntity<IEventInventoryEntity> {

    @Override
    public IEventInventoryEntity apply(String line) {
        var data = split(line);
        return new EventInventoryEntity(
                Convert.parseInt(data[0]),
                Convert.parseInt(data[1]),
                Convert.parseInt(data[2]));
    }

}

