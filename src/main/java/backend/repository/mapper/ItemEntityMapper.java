package backend.repository.mapper;

import backend.repository.interfaces.IItemEntity;
import backend.repository.entity.ItemEntity;

import java.util.function.Function;

import static java.lang.Integer.parseInt;

public final class ItemEntityMapper implements Function<String, IItemEntity> {

    @Override
    public IItemEntity apply(String l) {
        var dadosLinha = l.split(";");
        return new ItemEntity(
                stringToInt(dadosLinha[0].trim()),
                tratarString(dadosLinha[1].trim()),
                tratarString(dadosLinha[2].trim()),
                stringToInt(dadosLinha[3].trim()),
                stringToInt(dadosLinha[4].trim()),
                stringToInt(dadosLinha[5].trim()),
                tratarString(dadosLinha[6].trim()),
                stringToInt(dadosLinha[7].trim()),
                stringToBoolean(dadosLinha[8].trim()),
                tratarString(dadosLinha[9].trim()),
                tratarString(dadosLinha[10].trim()),
                stringToBoolean(dadosLinha[11].trim()),
                stringToBoolean(dadosLinha[12].trim()),
                tratarString(dadosLinha[13].trim()),
                tratarString(dadosLinha[14].trim()),
                tratarString(dadosLinha[15].trim())
        );
    }

    private String tratarString(String dado) {
        return dado.equals("null") ? null : dado;
    }

    private boolean stringToBoolean(String dado) {
        return dado.equals("true");
    }

    private int stringToInt(String dado) {
        return dado.equals("null") ? -1 : parseInt(dado);
    }

}
