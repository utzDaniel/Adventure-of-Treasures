package backend.repository.mapper;

import backend.repository.entity.ItemEntity;
import backend.repository.interfaces.IItemEntity;
import backend.repository.interfaces.IMapperEntity;

public final class ItemEntityMapper implements IMapperEntity<IItemEntity> {

    @Override
    public IItemEntity apply(String line) {
        var data = split(line);
        return new ItemEntity(
                Convert.parseInt(data[0]),
                Convert.parseString(data[1]),
                Convert.parseString(data[2]),
                Convert.parseInt(data[3]),
                Convert.parseInt(data[4]),
                Convert.parseInt(data[5]),
                Convert.parseString(data[6]));

    }
}

