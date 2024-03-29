package backend.repository.mapper;

import backend.repository.entity.UsableEntity;
import backend.repository.interfaces.IMapperEntity;
import backend.repository.interfaces.IUsableEntity;

public final class UsableEntityMapper implements IMapperEntity<IUsableEntity> {

    @Override
    public IUsableEntity apply(String linha) {
        var dados = split(linha);
        return new UsableEntity(
                Convert.parseInt(dados[0]),
                Convert.parseInt(dados[1]),
                Convert.parseInt(dados[2]),
                Convert.parseInt(dados[3]),
                Convert.parseInt(dados[4]),
                Convert.parseBoolean(dados[5]));
    }

}

