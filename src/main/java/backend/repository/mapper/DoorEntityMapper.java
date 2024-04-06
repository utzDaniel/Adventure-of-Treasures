package backend.repository.mapper;

import backend.repository.entity.DoorEntity;
import backend.repository.interfaces.IDoorEntity;
import backend.repository.interfaces.IMapperEntity;

public final class DoorEntityMapper implements IMapperEntity<IDoorEntity> {

    @Override
    public IDoorEntity apply(String line) {
        var data = split(line);
        return new DoorEntity(
                Convert.parseInt(data[0]),
                Convert.parseInt(data[1]),
                Convert.parseInt(data[2]),
                Convert.parseInt(data[3]),
                Convert.parseInt(data[4]),
                Convert.parseInt(data[5]),
                Convert.parseInt(data[6]),
                Convert.parseBoolean(data[7])
        );
    }

}

