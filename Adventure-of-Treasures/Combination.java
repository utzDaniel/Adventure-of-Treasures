import java.util.ArrayList;

public class Combination {

    private final Player player;

    public Combination(Player player) {
        this.player = player;
    }

    public boolean validItemCombinable(ArrayList<Item> itens) {
        ArrayList<ItemCombinable> itensCombinable = new ArrayList<>();
        for (Item item : itens) {
            if (item instanceof ItemCombinable) {
                itensCombinable.add((ItemCombinable) item);
            } else {
                return false;
            }
        }
        return validCombination(itensCombinable);
    }

    private boolean validCombination(ArrayList<ItemCombinable> itensCombinable) {
        for (int i = 1; i < itensCombinable.size(); i++) {
            if (itensCombinable.get(0).getCombine() != itensCombinable.get(i).getCombine()) {
                return false;
            }
        }
        return validAmountCombine(itensCombinable);
    }

    private boolean validAmountCombine(ArrayList<ItemCombinable> itensCombinable) {
        if (itensCombinable.size() == ItemCombinable.getAmountCombine(itensCombinable.get(0).getCombine())) {
            return itensCombination(itensCombinable);
        }
        return false;
    }

    private boolean itensCombination(ArrayList<ItemCombinable> itensCombinable) {
        Item itemVisible = null;
        if (itensCombinable.get(0).getCombine() == 1) {
            for (Item item : player.getItemInvisible()) {
                if (item.getName().equals("mapa")) {
                    CreateImageMapGame imageMapGame = new CreateImageMapGame();
                    ((ItemNotRemove)item).getMapGame().setImagemIcon(imageMapGame.selectImage(item.getName()));
                    itemVisible = item;
                }
            }
        } else if (itensCombinable.get(0).getCombine() == 2) {
            for (Item item : player.getItemInvisible()) {
                if (item.getName().equals("escada")) {
                    itemVisible = item;
                }
            }
        } else if (itensCombinable.get(0).getCombine() == 3) {
            for (Item item : player.getItemInvisible()) {
                if (item.getName().equals("tocha")) {
                    itemVisible = item;
                }
            }
        } else {
            return false;
        }
        if(itemVisible != null){
            removeItensCombine(itensCombinable.get(0).getCombine());
            itemViseble(itemVisible);
            updadeCapacity(itemVisible);
        }
        return true;
    }

    private void removeItensCombine(int combine) {
        player.removeItensCombine(combine);
    }

    private void itemViseble(Item item) {
        item.setVisible(true);
    }

    private void updadeCapacity(Item item) {
        player.updadeInventory(item);
    }
}
