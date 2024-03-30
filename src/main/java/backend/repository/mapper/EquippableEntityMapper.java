package backend.repository.mapper;

import backend.repository.entity.EquippableEntity;
import backend.repository.interfaces.IEquippableEntity;
import backend.repository.interfaces.IMapperEntity;

public final class EquippableEntityMapper implements IMapperEntity<IEquippableEntity> {

    @Override
    public IEquippableEntity apply(String linha) {
        var dados = split(linha);
        return new EquippableEntity(
                Convert.parseInt(dados[0]),
                Convert.parseInt(dados[1]),
                Convert.parseInt(dados[2]));
    }

}

