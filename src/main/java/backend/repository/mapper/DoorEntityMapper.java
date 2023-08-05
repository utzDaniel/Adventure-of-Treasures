package backend.repository.mapper;

import backend.repository.entity.DoorEntity;
import backend.repository.interfaces.IDoorEntity;
import backend.repository.interfaces.IMapperEntity;
import backend.repository.util.ValidUtil;

public final class DoorEntityMapper implements IMapperEntity<IDoorEntity> {

    @Override
    public IDoorEntity apply(String linha) {
        var dados = split(linha);
        return new DoorEntity(
                ValidUtil.parseInt(dados[0]),
                ValidUtil.parseInt(dados[1]),
                ValidUtil.parseInt(dados[2]),
                ValidUtil.parseInt(dados[3]),
                ValidUtil.parseInt(dados[4]),
                ValidUtil.parseBoolean(dados[5])
        );
    }

}

