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
    EVENT_MAP("event_map"),
    EVENT_INVENTORY("event_inventory"),
    EVENT_ITEM("event_item"),
    EVENT_DOOR("event_door"),
    NPC("npc");

    private final String path;

    Filename(String path) {
        this.path = path;
    }

    public String getPath() {
        return String.format("repository/%s.csv", this.path);
    }

}
