package backend.repository.mapper;

import backend.repository.entity.CombinableEntity;
import backend.repository.interfaces.ICombinableEntity;
import backend.repository.interfaces.IMapperEntity;
import backend.repository.util.ValidUtil;

public final class CombinableEntityMapper implements IMapperEntity<ICombinableEntity> {

    @Override
    public ICombinableEntity apply(String linha) {
        var dados = split(linha);
        return new CombinableEntity(
                ValidUtil.parseInt(dados[0]),
                ValidUtil.parseInt(dados[1]),
                ValidUtil.parseInt(dados[2]));
    }

}

