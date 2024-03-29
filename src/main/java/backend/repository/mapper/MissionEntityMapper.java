package backend.repository.mapper;

import backend.repository.entity.MissionEntity;
import backend.repository.interfaces.IMapperEntity;
import backend.repository.interfaces.IMissionEntity;

public final class MissionEntityMapper implements IMapperEntity<IMissionEntity> {

    @Override
    public IMissionEntity apply(String linha) {
        var dados = split(linha);
        return new MissionEntity(
                Convert.parseInt(dados[0]),
                Convert.parseInt(dados[1]));
    }

}

