package backend.repository.mapper;

import backend.repository.entity.MapGameEntity;
import backend.repository.interfaces.IMapGameEntity;
import backend.repository.interfaces.IMapperEntity;
import backend.repository.util.ValidUtil;

public final class MapGameEntityMapper implements IMapperEntity<IMapGameEntity> {

    public static final int LINE_COUNT = 56;
    public static final int COLUMN_COUNT = 78;

    @Override
    public IMapGameEntity apply(String linha) {
        var dados = split(linha);
        return new MapGameEntity(
                ValidUtil.parseInt(dados[0]),
                ValidUtil.parseString(dados[1]),
                ValidUtil.parseString(dados[2]),
                ValidUtil.parseString(dados[3]),
                createLimits(dados[4])
        );
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

