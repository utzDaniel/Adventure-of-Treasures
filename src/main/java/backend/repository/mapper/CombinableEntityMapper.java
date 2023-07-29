package backend.repository.mapper;

import backend.repository.entity.CombinableEntity;
import backend.repository.interfaces.ICombinableEntity;

import java.util.function.Function;

import static java.lang.Integer.parseInt;

public final class CombinableEntityMapper implements Function<String, ICombinableEntity> {

    @Override
    public ICombinableEntity apply(String l) {
        var dadosLinha = l.split(";");
        return new CombinableEntity(
                stringToInt(dadosLinha[0].trim()),
                stringToInt(dadosLinha[1].trim()),
                stringToInt(dadosLinha[2].trim()));
    }

    private int stringToInt(String dado) {
        return dado.equals("null") ? -1 : parseInt(dado);
    }

}

