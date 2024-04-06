package backend.repository.mapper;

import backend.repository.entity.NPCEntity;
import backend.repository.interfaces.IMapperEntity;
import backend.repository.interfaces.INPCEntity;

public final class NPCEntityMapper implements IMapperEntity<INPCEntity> {

    @Override
    public INPCEntity apply(String line) {
        var data = split(line);
        return new NPCEntity(
                Convert.parseInt(data[0]),
                Convert.parseInt(data[1]),
                Convert.parseInt(data[2]),
                Convert.parseInt(data[3]),
                Convert.parseInt(data[4]),
                Convert.parseInt(data[5]));
    }

}

