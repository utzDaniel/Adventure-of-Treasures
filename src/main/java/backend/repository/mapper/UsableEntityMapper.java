package backend.repository.mapper;

import backend.repository.entity.UsableEntity;
import backend.repository.interfaces.IMapperEntity;
import backend.repository.interfaces.IUsableEntity;
import backend.repository.util.ValidUtil;

public final class UsableEntityMapper implements IMapperEntity<IUsableEntity> {

    @Override
    public IUsableEntity apply(String linha) {
        var dados = split(linha);
        return new UsableEntity(
                ValidUtil.parseInt(dados[0]),
                ValidUtil.parseInt(dados[1]),
                ValidUtil.parseInt(dados[2]),
                ValidUtil.parseInt(dados[3]),
                ValidUtil.parseInt(dados[4]),
                ValidUtil.parseBoolean(dados[5]));
    }

}

