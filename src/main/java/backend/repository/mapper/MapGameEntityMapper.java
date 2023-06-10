package backend.repository.mapper;

import backend.repository.entity.MapGameEntity;
import backend.repository.interfaces.IMapGameEntity;

import java.util.function.Function;

import static java.lang.Integer.parseInt;

public final class MapGameEntityMapper implements Function<String, IMapGameEntity> {

    @Override
    public IMapGameEntity apply(String l) {
        var dadosLinha = l.split(";");
        return new MapGameEntity(
                tratarString(dadosLinha[0].trim()),
                tratarString(dadosLinha[1].trim()),
                stringToInt(dadosLinha[2].trim()),
                stringToInt(dadosLinha[3].trim()),
                stringToInt(dadosLinha[4].trim()),
                tratarString(dadosLinha[5].trim()),
                createLimits(dadosLinha[6].trim())
        );
    }

    private String tratarString(String dado) {
        return dado.equals("null") ? null : dado;
    }

    private int stringToInt(String dado) {
        return dado.equals("null") ? -1 : parseInt(dado);
    }

    private int[][] createLimits(String dados) {
        int[][] limits = new int[56][78];
        int j = 0, k = 0;
        for (int i = 0; i < dados.length(); i++) {
            if (i != 0 && i % 78 == 0) {
                j++;
                k = 0;
            }
            limits[j][k] = stringToInt(String.valueOf(dados.charAt(i)));
            k++;
        }
        return limits;
    }
}

