package backend.repository.mapper;

import backend.repository.entity.MissionEntity;
import backend.repository.interfaces.IMapperEntity;
import backend.repository.interfaces.IMissionEntity;
import backend.repository.util.ValidUtil;

public final class MissionEntityMapper implements IMapperEntity<IMissionEntity> {

    @Override
    public IMissionEntity apply(String linha) {
        var dados = split(linha);
        return new MissionEntity(
                ValidUtil.parseInt(dados[0]),
                ValidUtil.parseInt(dados[1]));
    }

}

