package backend.repository.mapper;

import backend.repository.entity.DecorationEntity;
import backend.repository.interfaces.IDecorationEntity;
import backend.repository.interfaces.IMapperEntity;

public final class DecorationEntityMapper implements IMapperEntity<IDecorationEntity> {

    @Override
    public IDecorationEntity apply(String line) {
        var data = split(line);
        return new DecorationEntity(
                Convert.parseInt(data[0]),
                Convert.parseInt(data[1]),
                Convert.parseInt(data[2]),
                Convert.parseString(data[3]));
    }

}

