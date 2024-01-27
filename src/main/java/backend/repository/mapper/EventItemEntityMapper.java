package backend.repository.mapper;

import backend.repository.entity.EventItemEntity;
import backend.repository.interfaces.IEventItemEntity;
import backend.repository.interfaces.IMapperEntity;
import backend.repository.util.ValidUtil;

public final class EventItemEntityMapper implements IMapperEntity<IEventItemEntity> {

    @Override
    public IEventItemEntity apply(String linha) {
        var dados = split(linha);
        return new EventItemEntity(
                ValidUtil.parseInt(dados[0]),
                ValidUtil.parseInt(dados[1]),
                ValidUtil.parseInt(dados[2]));
    }

}

