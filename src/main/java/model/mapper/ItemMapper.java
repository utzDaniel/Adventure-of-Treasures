package model.mapper;

import model.builder.item.Item;
import model.builder.DirectorItem;

import java.util.function.Function;

public class ItemMapper implements Function<String, Item> {
    @Override
    public Item apply(String l) {
        var dadosLinha = l.split(";");
        var size = dadosLinha.length + 1;
        String[] dadosTratado = new String[size];
        dadosTratado[0] = createCode(dadosLinha);
        for (int i = 0; i < dadosLinha.length; i++) {
            dadosTratado[i + 1] = dadosLinha[i].trim();
        }
        dadosTratado[11] = dadosTratado[11].equals("null") ? "false" : "true";
        return new DirectorItem().create(dadosTratado);
    }

    private String createCode(String[] dados) {
        StringBuilder code = new StringBuilder();
        for (String dado : dados) {
            code.append(dado.equals("null") ? "0" : "1");
        }
        return code.toString();
    }
}

