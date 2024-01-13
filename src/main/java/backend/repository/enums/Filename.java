package backend.repository.enums;

public enum Filename {
    DOOR("door"),

    EQUIPABLE("equipable"),

    EXIT("exit"),

    ITEM("item"),

    MAPGAME("map"),

    USABLE("usable"),

    COMBINABLE("combinable"),

    MISSION("mission"),

    ITEM_MAP("item_map"),
    EVENT("event");

    private final String path;

    Filename(String path) {
        this.path = path;
    }

    public String getPath() {
        return String.format("repository/%s.csv", this.path);
    }

}
