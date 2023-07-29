package backend.service.model.builder;

import java.util.HashMap;

public final class RoomBuilder extends MapGameBuilder {

    private final Room room;
    private RoomBuilder() {
        this.room = new Room();
        super.mapGame = this.room;
        super.doors = new HashMap<>();
        super.itens = new HashMap<>();
    }

    public static RoomBuilder builder() {
        return new RoomBuilder();
    }

    @Override
    public Room build() {
        super.build();
        return this.room;
    }
}
