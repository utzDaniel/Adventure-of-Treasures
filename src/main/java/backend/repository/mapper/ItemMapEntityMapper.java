package backend.repository.mapper;

import backend.repository.entity.ItemMapEntity;
import backend.repository.interfaces.IItemMapEntity;
import backend.repository.interfaces.IMapperEntity;

public final class ItemMapEntityMapper implements IMapperEntity<IItemMapEntity> {

    @Override
    public IItemMapEntity apply(String line) {
        var data = split(line);
        return new ItemMapEntity(
                Convert.parseInt(data[0]),
                Convert.parseInt(data[1]),
                Convert.parseInt(data[2]));
    }

}

