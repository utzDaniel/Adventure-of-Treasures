package backend.repository.mapper;

import backend.repository.entity.EquipableEntity;
import backend.repository.interfaces.IEquipableEntity;
import backend.repository.interfaces.IMapperEntity;

public final class EquipableEntityMapper implements IMapperEntity<IEquipableEntity> {

    @Override
    public IEquipableEntity apply(String linha) {
        var dados = split(linha);
        return new EquipableEntity(
                Convert.parseInt(dados[0]),
                Convert.parseInt(dados[1]),
                Convert.parseInt(dados[2]));
    }

}

