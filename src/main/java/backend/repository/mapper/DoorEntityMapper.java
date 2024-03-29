package backend.repository.mapper;

import backend.repository.entity.DoorEntity;
import backend.repository.interfaces.IDoorEntity;
import backend.repository.interfaces.IMapperEntity;

public final class DoorEntityMapper implements IMapperEntity<IDoorEntity> {

    @Override
    public IDoorEntity apply(String linha) {
        var dados = split(linha);
        return new DoorEntity(
                Convert.parseInt(dados[0]),
                Convert.parseInt(dados[1]),
                Convert.parseInt(dados[2]),
                Convert.parseInt(dados[3]),
                Convert.parseInt(dados[4]),
                Convert.parseBoolean(dados[5])
        );
    }

}

