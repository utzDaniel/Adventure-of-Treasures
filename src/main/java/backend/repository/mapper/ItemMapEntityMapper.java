package backend.repository.mapper;

import backend.repository.entity.ItemMapEntity;
import backend.repository.interfaces.IItemMapEntity;
import backend.repository.interfaces.IMapperEntity;

public final class ItemMapEntityMapper implements IMapperEntity<IItemMapEntity> {

    @Override
    public IItemMapEntity apply(String linha) {
        var dados = split(linha);
        return new ItemMapEntity(
                Convert.parseInt(dados[0]),
                Convert.parseInt(dados[1]),
                Convert.parseInt(dados[2]));
    }

}

