package backend.repository.mapper;

import backend.repository.entity.EventDoorEntity;
import backend.repository.interfaces.IEventDoorEntity;
import backend.repository.interfaces.IMapperEntity;

public final class EventDoorEntityMapper implements IMapperEntity<IEventDoorEntity> {

    @Override
    public IEventDoorEntity apply(String linha) {
        var dados = split(linha);
        return new EventDoorEntity(
                Convert.parseInt(dados[0]),
                Convert.parseInt(dados[1]),
                Convert.parseInt(dados[2]),
                Convert.parseBoolean(dados[3]));
    }

}

