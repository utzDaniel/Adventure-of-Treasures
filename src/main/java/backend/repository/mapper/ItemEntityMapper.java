package backend.repository.mapper;

import backend.repository.entity.ItemEntity;
import backend.repository.interfaces.IItemEntity;
import backend.repository.interfaces.IMapperEntity;

public final class ItemEntityMapper implements IMapperEntity<IItemEntity> {

    @Override
    public IItemEntity apply(String linha) {
        var dados = split(linha);
        return new ItemEntity(
                Convert.parseInt(dados[0]),
                Convert.parseString(dados[1]),
                Convert.parseString(dados[2]),
                Convert.parseInt(dados[3]),
                Convert.parseInt(dados[4]),
                Convert.parseInt(dados[5]),
                Convert.parseString(dados[6]));

    }
}

