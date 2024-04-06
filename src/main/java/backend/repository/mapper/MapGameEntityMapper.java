package backend.repository.mapper;

import backend.repository.entity.MapGameEntity;
import backend.repository.interfaces.IMapGameEntity;
import backend.repository.interfaces.IMapperEntity;

public final class MapGameEntityMapper implements IMapperEntity<IMapGameEntity> {

    public static final int LINE_COUNT = 56;
    public static final int COLUMN_COUNT = 78;

    @Override
    public IMapGameEntity apply(String line) {
        var data = split(line);
        return new MapGameEntity(
                Convert.parseInt(data[0]),
                Convert.parseString(data[1]),
                Convert.parseString(data[2]),
                Convert.parseString(data[3]),
                createLimits(data[4])
        );
    }

    private byte[][] createLimits(String data) {
        byte[][] limits = new byte[LINE_COUNT][COLUMN_COUNT];
        int line = 0;
        int column = 0;
        for (byte b : data.getBytes()) {
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

