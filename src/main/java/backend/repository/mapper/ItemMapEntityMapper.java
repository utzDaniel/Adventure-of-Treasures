package backend.repository.mapper;

import backend.repository.entity.ItemMapEntity;
import backend.repository.interfaces.IItemMapEntity;
import backend.repository.interfaces.IMapperEntity;
import backend.repository.util.ValidUtil;

public final class ItemMapEntityMapper implements IMapperEntity<IItemMapEntity> {

    @Override
    public IItemMapEntity apply(String linha) {
        var dados = split(linha);
        return new ItemMapEntity(
                ValidUtil.parseInt(dados[0]),
                ValidUtil.parseInt(dados[1]),
                ValidUtil.parseInt(dados[2]));
    }

}

