package backend.service.model.builder;

public final class Room extends MapGame {

    public Room () {
        //private
    }

    @Override
    public String toString() {
        return """
                MapGame: %s
                {
                
                }
                """.formatted(super.toString());
    }
}
