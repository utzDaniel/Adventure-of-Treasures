package backend.repository.mapper;

import backend.repository.entity.EquipableEntity;
import backend.repository.interfaces.IEquipableEntity;
import backend.repository.interfaces.IMapperEntity;
import backend.repository.util.ValidUtil;

public final class EquipableEntityMapper implements IMapperEntity<IEquipableEntity> {

    @Override
    public IEquipableEntity apply(String linha) {
        var dados = split(linha);
        return new EquipableEntity(
                ValidUtil.parseInt(dados[0]),
                ValidUtil.parseInt(dados[1]),
                ValidUtil.parseInt(dados[2]));
    }

}

