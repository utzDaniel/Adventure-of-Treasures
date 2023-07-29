package backend.repository.mapper;

import backend.repository.entity.EquipableEntity;
import backend.repository.interfaces.IEquipableEntity;

import java.util.function.Function;

import static java.lang.Integer.parseInt;

public final class EquipableEntityMapper implements Function<String, IEquipableEntity> {

    @Override
    public IEquipableEntity apply(String l) {
        var dadosLinha = l.split(";");
        return new EquipableEntity(
                stringToInt(dadosLinha[0].trim()),
                stringToInt(dadosLinha[1].trim()),
                stringToInt(dadosLinha[2].trim()));
    }

    private int stringToInt(String dado) {
        return dado.equals("null") ? -1 : parseInt(dado);
    }

}

