package backend.repository.mapper;

import backend.repository.entity.ExitEntity;
import backend.repository.interfaces.IExitEntity;

import java.util.function.Function;

import static java.lang.Integer.parseInt;

public final class ExitEntityMapper implements Function<String, IExitEntity> {

    @Override
    public IExitEntity apply(String l) {
        var dadosLinha = l.split(";");
        return new ExitEntity(
                stringToInt(dadosLinha[0].trim()),
                stringToInt(dadosLinha[1].trim()),
                tratarString(dadosLinha[2].trim()),
                tratarString(dadosLinha[3].trim())
        );
    }

    private String tratarString(String dado) {
        return dado.equals("null") ? null : dado;
    }

    private int stringToInt(String dado) {
        return dado.equals("null") ? -1 : parseInt(dado);
    }

}

