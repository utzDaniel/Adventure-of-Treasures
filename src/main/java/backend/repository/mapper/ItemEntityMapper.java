package backend.repository.mapper;

import backend.repository.entity.ItemEntity;
import backend.repository.interfaces.IItemEntity;
import backend.repository.interfaces.IMapperEntity;
import backend.repository.util.ValidUtil;

public final class ItemEntityMapper implements IMapperEntity<IItemEntity> {

    @Override
    public IItemEntity apply(String linha) {
        var dados = split(linha);
        return new ItemEntity(
                ValidUtil.parseInt(dados[0]),
                ValidUtil.parseString(dados[1]),
                ValidUtil.parseString(dados[2]),
                ValidUtil.parseInt(dados[3]),
                ValidUtil.parseInt(dados[4]),
                ValidUtil.parseInt(dados[5]),
                ValidUtil.parseString(dados[6]),
                ValidUtil.parseBoolean(dados[7])
        );
    }
}

