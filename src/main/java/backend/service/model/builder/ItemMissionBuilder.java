package backend.service.model.builder;

public final class ItemMissionBuilder extends ItemBuilder {

    private final ItemMission itemMission;

    private ItemMissionBuilder() {
        this.itemMission = new ItemMission();
        super.item = this.itemMission;
    }

    public static ItemMissionBuilder builder() {
        return new ItemMissionBuilder();
    }

    public ItemMissionBuilder mapGame(String name) {
        this.itemMission.setMapGame(name);
        return this;
    }

    @Override
    public ItemMission build() {
        return this.itemMission;
    }
}
