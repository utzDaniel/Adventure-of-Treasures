package backend.repository.mapper;

import backend.repository.entity.ExitEntity;
import backend.repository.interfaces.IExitEntity;
import backend.repository.interfaces.IMapperEntity;

public final class ExitEntityMapper implements IMapperEntity<IExitEntity> {

    @Override
    public IExitEntity apply(String linha) {
        var dados = split(linha);
        return new ExitEntity(
                Convert.parseInt(dados[0]),
                Convert.parseInt(dados[1]),
                Convert.parseString(dados[2]),
                Convert.parseInt(dados[3])
        );
    }

}

