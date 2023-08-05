package backend.repository.mapper;

import backend.repository.entity.ExitEntity;
import backend.repository.interfaces.IExitEntity;
import backend.repository.interfaces.IMapperEntity;
import backend.repository.util.ValidUtil;

public final class ExitEntityMapper implements IMapperEntity<IExitEntity> {

    @Override
    public IExitEntity apply(String linha) {
        var dados = split(linha);
        return new ExitEntity(
                ValidUtil.parseInt(dados[0]),
                ValidUtil.parseInt(dados[1]),
                ValidUtil.parseString(dados[2]),
                ValidUtil.parseInt(dados[3])
        );
    }

}

