package backend.repository.mapper;

import backend.repository.entity.EventInventoryEntity;
import backend.repository.interfaces.IEventInventoryEntity;
import backend.repository.interfaces.IMapperEntity;

public final class EventInventoryEntityMapper implements IMapperEntity<IEventInventoryEntity> {

    @Override
    public IEventInventoryEntity apply(String linha) {
        var dados = split(linha);
        return new EventInventoryEntity(
                Convert.parseInt(dados[0]),
                Convert.parseInt(dados[1]),
                Convert.parseInt(dados[2]));
    }

}

