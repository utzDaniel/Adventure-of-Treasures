package backend.repository.mapper;

import backend.repository.entity.EventItemEntity;
import backend.repository.interfaces.IEventItemEntity;
import backend.repository.interfaces.IMapperEntity;

public final class EventItemEntityMapper implements IMapperEntity<IEventItemEntity> {

    @Override
    public IEventItemEntity apply(String linha) {
        var dados = split(linha);
        return new EventItemEntity(
                Convert.parseInt(dados[0]),
                Convert.parseInt(dados[1]),
                Convert.parseInt(dados[2]));
    }

}

