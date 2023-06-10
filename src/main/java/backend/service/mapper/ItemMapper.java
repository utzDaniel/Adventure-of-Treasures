package backend.service.mapper;

import backend.service.model.builder.ItemFactory;
import backend.service.model.builder.Item;

import java.util.Arrays;
import java.util.function.Function;

public final class ItemMapper implements Function<String, Item> {

    private final ItemFactory itemFactory = new ItemFactory();

    @Override
    public Item apply(String l) {
        var dadosLinha = l.split(";");
        var size = dadosLinha.length + 1;
        String[] dadosTratado = new String[size];
        dadosTratado[0] = createCode(dadosLinha);
        for (int i = 0; i < dadosLinha.length; i++) {
            dadosTratado[i + 1] = dadosLinha[i].trim();
        }
        dadosTratado[11] = isNullToBoolean(dadosTratado[11]);
        dadosTratado[12] = isNullToBoolean(dadosTratado[12]);
        return this.itemFactory.create(dadosTratado);
    }

    private String createCode(String[] dados) {
        StringBuilder code = new StringBuilder();
        Arrays.stream(dados).limit(11)
                .forEach(dado -> code.append(dado.equals("null") ? "0" : "1"));
        return code.toString();
    }

    private String isNullToBoolean(String dado) {
        return dado.equals("null") ? "false" : "true";
    }
}
