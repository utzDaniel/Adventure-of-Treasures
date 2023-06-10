package backend.service.mapper;

import backend.service.model.builder.MapGameFactory;
import backend.service.model.builder.MapGame;

import java.util.Arrays;
import java.util.function.Function;

public final class MapGameMapper implements Function<String, MapGame> {

    private final MapGameFactory mapGameFactory = new MapGameFactory();

    @Override
    public MapGame apply(String l) {
        var dadosLinha = l.split(";");
        var size = dadosLinha.length + 1;
        String[] dadosTratado = new String[size];
        dadosTratado[0] = createCode(dadosLinha);
        for (int i = 0; i < dadosLinha.length; i++) {
            dadosTratado[i + 1] = dadosLinha[i].trim().equals("null") ? null : dadosLinha[i];
        }
        return this.mapGameFactory.create(dadosTratado);
    }

    private String createCode(String[] dados) {
        StringBuilder code = new StringBuilder();
        Arrays.stream(dados)
                .skip(2)
                .limit(4)
                .forEach(dado -> code.append(dado.equals("null") ? "0" : "1"));
        return code.toString();
    }
}

