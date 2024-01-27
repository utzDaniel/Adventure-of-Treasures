package backend.repository.mapper;

import backend.repository.entity.EventMapEntity;
import backend.repository.interfaces.IEventMapEntity;
import backend.repository.interfaces.IMapperEntity;
import backend.repository.util.ValidUtil;

public final class EventMapEntityMapper implements IMapperEntity<IEventMapEntity> {

    @Override
    public IEventMapEntity apply(String linha) {
        var dados = split(linha);
        return new EventMapEntity(
                ValidUtil.parseInt(dados[0]),
                ValidUtil.parseInt(dados[1]),
                ValidUtil.parseInt(dados[2]),
                ValidUtil.parseString(dados[3]));
    }

}

