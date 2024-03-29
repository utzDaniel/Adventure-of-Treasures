package backend.repository.mapper;

import backend.repository.entity.CombinableEntity;
import backend.repository.interfaces.ICombinableEntity;
import backend.repository.interfaces.IMapperEntity;

public final class CombinableEntityMapper implements IMapperEntity<ICombinableEntity> {

    @Override
    public ICombinableEntity apply(String linha) {
        var dados = split(linha);
        return new CombinableEntity(
                Convert.parseInt(dados[0]),
                Convert.parseInt(dados[1]),
                Convert.parseInt(dados[2]));
    }

}

