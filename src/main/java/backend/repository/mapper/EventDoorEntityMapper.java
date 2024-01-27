package backend.repository.mapper;

import backend.repository.entity.EventDoorEntity;
import backend.repository.interfaces.IEventDoorEntity;
import backend.repository.interfaces.IMapperEntity;
import backend.repository.util.ValidUtil;

public final class EventDoorEntityMapper implements IMapperEntity<IEventDoorEntity> {

    @Override
    public IEventDoorEntity apply(String linha) {
        var dados = split(linha);
        return new EventDoorEntity(
                ValidUtil.parseInt(dados[0]),
                ValidUtil.parseInt(dados[1]),
                ValidUtil.parseInt(dados[2]),
                ValidUtil.parseBoolean(dados[3]));
    }

}

