package backend.repository.mapper;

import backend.repository.entity.EventInventoryEntity;
import backend.repository.interfaces.IEventInventoryEntity;
import backend.repository.interfaces.IMapperEntity;
import backend.repository.util.ValidUtil;

public final class EventInventoryEntityMapper implements IMapperEntity<IEventInventoryEntity> {

    @Override
    public IEventInventoryEntity apply(String linha) {
        var dados = split(linha);
        return new EventInventoryEntity(
                ValidUtil.parseInt(dados[0]),
                ValidUtil.parseInt(dados[1]),
                ValidUtil.parseInt(dados[2]));
    }

}

