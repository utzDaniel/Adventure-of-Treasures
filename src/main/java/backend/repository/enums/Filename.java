package backend.repository.enums;

public enum Filename {
    DOOR("door"),
    EQUIPPABLE("equippable"),
    EXIT("exit"),
    ITEM("item"),
    MAP_GAME("map"),
    USABLE("usable"),
    COMBINABLE("combinable"),
    MISSION("mission"),
    ITEM_MAP("item_map"),
    EVENT_MAP("event_map"),
    EVENT_INVENTORY("event_inventory"),
    EVENT_ITEM("event_item"),
    EVENT_DOOR("event_door"),
    DECORATION("decoration"),
    NPC("npc");

    private final String path;

    Filename(String path) {
        this.path = path;
    }

    public String getPath() {
        return String.format("src/main/resources/repository/%s.csv", this.path);
    }

}
