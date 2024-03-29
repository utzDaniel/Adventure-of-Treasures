package backend.repository.mapper;

import backend.repository.entity.EventMapEntity;
import backend.repository.interfaces.IEventMapEntity;
import backend.repository.interfaces.IMapperEntity;

public final class EventMapEntityMapper implements IMapperEntity<IEventMapEntity> {

    @Override
    public IEventMapEntity apply(String linha) {
        var dados = split(linha);
        return new EventMapEntity(
                Convert.parseInt(dados[0]),
                Convert.parseInt(dados[1]),
                Convert.parseInt(dados[2]),
                Convert.parseString(dados[3]));
    }

}

