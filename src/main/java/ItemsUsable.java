import java.util.Objects;

public enum ItemsUsable {
    PA("pa"){
        @Override
        public boolean use(Player player){
            if (Objects.isNull(player.getItemInventory("mapa")))
                return false;
            for (Item item : player.getCurrentMap().getItemInvisible()) {
                if (item.getName().equals("chave")) {
                    player.getCurrentMap().setImagemIcon(
                            new CreateImageMapGame().selectImage(player.getCurrentMap().getName()));
                    item.setVisible(true);
                    break;
                }
            }
            return true;
        }},
    CHAVE("chave") {
        @Override
        public boolean use(Player player) {
            Door openDoor = player.getCurrentMap().getDoorMap(player.getPositionPlayerX(), player.getPositionPlayerY());
            if (Objects.isNull(openDoor)) return false;
            openDoor.setOpen(true);
            return true;
        }
    },
    ESCADA("escada") {
        @Override
        public boolean use(Player player) {
            Door openDoor = player.getCurrentMap().getDoorMap(player.getPositionPlayerX(), player.getPositionPlayerY());
            if (Objects.isNull(openDoor)) return false;
            openDoor.setOpen(true);
            player.getCurrentMap().setImagemIcon(
                    new CreateImageMapGame().selectImage(ItemsUsable.ESCADA.label));
            return true;
        }
    };

    private final String label;

    ItemsUsable(String label) {
        this.label = label;
    }

    public abstract boolean use(Player player);

    public String getLabel() {
        return label;
    }
}
