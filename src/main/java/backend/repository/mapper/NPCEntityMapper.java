package backend.repository.mapper;

import backend.repository.entity.NPCEntity;
import backend.repository.interfaces.IMapperEntity;
import backend.repository.interfaces.INPCEntity;
import backend.repository.util.ValidUtil;

public final class NPCEntityMapper implements IMapperEntity<INPCEntity> {

    @Override
    public INPCEntity apply(String linha) {
        var dados = split(linha);
        return new NPCEntity(
                ValidUtil.parseInt(dados[0]),
                ValidUtil.parseInt(dados[1]),
                ValidUtil.parseInt(dados[2]),
                ValidUtil.parseInt(dados[3]));
    }

}

