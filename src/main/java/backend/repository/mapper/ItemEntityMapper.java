package backend.repository.mapper;

import backend.repository.entity.ItemEntity;
import backend.repository.interfaces.IItemEntity;
import backend.service.enums.TypeItem;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static java.lang.Integer.parseInt;

public final class ItemEntityMapper implements Function<String, IItemEntity> {

    @Override
    public IItemEntity apply(String l) {
        var dadosLinha = l.split(";");
        return new ItemEntity(
                stringToInt(dadosLinha[0].trim()),
                stringToInt(dadosLinha[1].trim()),
                tratarString(dadosLinha[2].trim()),
                tratarString(dadosLinha[3].trim()),
                stringToInt(dadosLinha[4].trim()),
                stringToInt(dadosLinha[5].trim()),
                stringToInt(dadosLinha[6].trim()),
                tratarString(dadosLinha[7].trim()),
                getListTypeItem(dadosLinha),
                stringToBoolean(dadosLinha[12].trim())
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

    private List<TypeItem> getListTypeItem(String[] dados) {
        var list = new ArrayList<TypeItem>();
        if (stringToBoolean(dados[8].trim()))
            list.add(TypeItem.COMBINABLE);
        if (stringToBoolean(dados[9].trim()))
            list.add(TypeItem.EQUIPABLE);
        if (stringToBoolean(dados[10].trim()))
            list.add(TypeItem.USABLE);
        if (stringToBoolean(dados[11].trim()))
            list.add(TypeItem.MISSION);
        return list;
    }

}

