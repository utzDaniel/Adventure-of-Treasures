package backend.repository.mapper;

import backend.repository.entity.ExitEntity;
import backend.repository.interfaces.IExitEntity;
import backend.repository.interfaces.IMapperEntity;

public final class ExitEntityMapper implements IMapperEntity<IExitEntity> {

    @Override
    public IExitEntity apply(String line) {
        var data = split(line);
        return new ExitEntity(
                Convert.parseInt(data[0]),
                Convert.parseInt(data[1]),
                Convert.parseString(data[2]),
                Convert.parseInt(data[3])
        );
    }

}

