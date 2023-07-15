package backend.repository.mapper;

import backend.repository.entity.MapGameEntity;
import backend.repository.interfaces.IMapGameEntity;

import java.util.function.Function;

import static java.lang.Integer.parseInt;

public final class MapGameEntityMapper implements Function<String, IMapGameEntity> {

    public static final int LINE_COUNT = 56;
    public static final int COLUMN_COUNT = 78;

    @Override
    public IMapGameEntity apply(String l) {
        var dadosLinha = l.split(";");
        return new MapGameEntity(
                stringToInt(dadosLinha[0].trim()),
                tratarString(dadosLinha[1].trim()),
                tratarString(dadosLinha[2].trim()),
                tratarString(dadosLinha[3].trim()),
                createLimits(dadosLinha[4].trim())
        );
    }

    private String tratarString(String dado) {
        return dado.equals("null") ? null : dado;
    }

    private int stringToInt(String dado) {
        return dado.equals("null") ? -1 : parseInt(dado);
    }

    private byte[][] createLimits(String dados) {
        byte[][] limits = new byte[LINE_COUNT][COLUMN_COUNT];
        int line = 0;
        int column = 0;
        for (byte b : dados.getBytes()) {
            limits[line][column] = b;
            column++;
            if (nextLine(column)) {
                column = 0;
                line++;
            }
        }
        return limits;
    }

    private static boolean nextLine(int column) {
        return column == COLUMN_COUNT;
    }
}

