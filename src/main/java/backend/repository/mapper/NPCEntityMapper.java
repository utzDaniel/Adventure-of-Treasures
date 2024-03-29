package backend.repository.mapper;

import backend.repository.entity.NPCEntity;
import backend.repository.interfaces.IMapperEntity;
import backend.repository.interfaces.INPCEntity;

public final class NPCEntityMapper implements IMapperEntity<INPCEntity> {

    @Override
    public INPCEntity apply(String linha) {
        var dados = split(linha);
        return new NPCEntity(
                Convert.parseInt(dados[0]),
                Convert.parseInt(dados[1]),
                Convert.parseInt(dados[2]),
                Convert.parseInt(dados[3]),
                Convert.parseInt(dados[4]),
                Convert.parseInt(dados[5]));
    }

}

