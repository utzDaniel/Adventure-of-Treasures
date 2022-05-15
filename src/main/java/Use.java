public class Use {

    private final Player player;

    public Use(Player player) {
        this.player = player;
    }

    public boolean validItemUsable(Item item) {
        if (item instanceof ItemUsable) {
            ItemUsable itemUsable = (ItemUsable) item;
            return validLocalItemUsable(itemUsable);
        } else {
            return false;
        }
    }

    private boolean validLocalItemUsable(ItemUsable itemUsable) {
        if (itemUsable.getLocalUse().equals(player.getCurrentMap().getName())) {
            return useItemUsable(itemUsable);
        } else {
            return false;
        }
    }

    private boolean useItemUsable(ItemUsable itemUsable) {
        if (itemUsable.getName().equals("pa") && player.getItemInventory("mapa") != null) {
            for (Item item : player.getCurrentMap().getItemInvisible()) {
                if (item.getName().equals("chave")) {
                    CreateImageMapGame imageMapGame = new CreateImageMapGame();
                    player.getCurrentMap().setImagemIcon(imageMapGame.selectImage(player.getCurrentMap().getName()));
                    item.setVisible(true);
                }
            }
        } else if (itemUsable.getName().equals("chave")) {
            Door openDoor = player.getCurrentMap().getDoorMap(player.getPositionPlayerX(), player.getPositionPlayerY());
            if (openDoor != null) {
                openDoor.setOpen(true);
            } else {
                return false;
            }
        } else if (itemUsable.getName().equals("escada")) {
            Door openDoor = player.getCurrentMap().getDoorMap(player.getPositionPlayerX(), player.getPositionPlayerY());
            if (openDoor != null) {
                CreateImageMapGame imageMapGame = new CreateImageMapGame();
                player.getCurrentMap().setImagemIcon(imageMapGame.selectImage(itemUsable.getName()));
                openDoor.setOpen(true);
            } else {
                return false;
            }
        } else {
            return false;
        }
        removeItemUsable(itemUsable);
        return true;
    }

    private void removeItemUsable(ItemUsable itemUsable) {
        player.removeItem(itemUsable);
    }
}
