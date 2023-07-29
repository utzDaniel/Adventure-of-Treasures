package backend.repository.mapper;

import backend.repository.entity.UsableEntity;
import backend.repository.interfaces.IUsableEntity;

import java.util.function.Function;

import static java.lang.Integer.parseInt;

public final class UsableEntityMapper implements Function<String, IUsableEntity> {

    @Override
    public IUsableEntity apply(String l) {
        var dadosLinha = l.split(";");
        return new UsableEntity(
                stringToInt(dadosLinha[0].trim()),
                stringToInt(dadosLinha[1].trim()),
                stringToInt(dadosLinha[2].trim()));
    }

    private int stringToInt(String dado) {
        return dado.equals("null") ? -1 : parseInt(dado);
    }

}

