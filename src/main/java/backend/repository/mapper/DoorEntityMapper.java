package backend.repository.mapper;

import backend.repository.entity.DoorEntity;
import backend.repository.interfaces.IDoorEntity;

import java.util.function.Function;

import static java.lang.Integer.parseInt;

public final class DoorEntityMapper implements Function<String, IDoorEntity> {

    @Override
    public IDoorEntity apply(String l) {
        var dadosLinha = l.split(";");
        return new DoorEntity(
                stringToInt(dadosLinha[0].trim()),
                stringToInt(dadosLinha[1].trim()),
                stringToInt(dadosLinha[2].trim()),
                stringToInt(dadosLinha[3].trim()),
                stringToInt(dadosLinha[4].trim()),
                stringToBoolean(dadosLinha[5].trim())
        );
    }

    private boolean stringToBoolean(String dado) {
        return dado.equals("true");
    }

    private int stringToInt(String dado) {
        return dado.equals("null") ? -1 : parseInt(dado);
    }

}

