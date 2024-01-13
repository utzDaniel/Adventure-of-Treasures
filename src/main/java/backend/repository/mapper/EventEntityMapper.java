package backend.repository.mapper;

import backend.repository.entity.EventEntity;
import backend.repository.interfaces.IEventEntity;
import backend.repository.interfaces.IMapperEntity;
import backend.repository.util.ValidUtil;

public final class EventEntityMapper implements IMapperEntity<IEventEntity> {

    @Override
    public IEventEntity apply(String linha) {
        var dados = split(linha);
        return new EventEntity(
                ValidUtil.parseInt(dados[0]),
                ValidUtil.parseInt(dados[1]),
                ValidUtil.parseInt(dados[2]),
                ValidUtil.parseInt(dados[3]));
    }

}

