package model;

import java.util.ArrayList;
import java.util.List;

public final class Inventory {

    private int capacity;
    private int maxCapacity;
    private boolean isInventory;
    private final List<Item> item;//alterar para HasMap

    public Inventory() {
        this.capacity = 0;
        this.maxCapacity = 10;
        this.isInventory = false;
        item = new ArrayList<>();
    }

    public boolean openInventory() {
        return isInventory;
    }

    public void setOpenInventory() {
        this.isInventory = !isInventory;
    }

    private void setCapacity(int weight) {
        this.capacity += weight;
    }

    public void setMaxCapacity(int upgradeCapacity) {
        this.maxCapacity += upgradeCapacity;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public int getMaxCapacity() {
        return this.maxCapacity;
    }
    
    public void setItemInvisible(Item item) {
        this.item.add(item);
    }

    public boolean setItem(Item item) {
        if (!(checkCapacity(item))) return false;
        this.item.add(item);
        this.setCapacity(item.getWeight());
        return true;
    }

    private boolean checkCapacity(Item item) {
        return item.getWeight() + this.capacity <= this.maxCapacity;
    }

    public Item getItemInventory(String nameItem) {
        for (Item itemInventory : item) {
            if (itemInventory.getName().equals(nameItem)) {
                return itemInventory;
            }
        }
        return null;
    }

    //private pois deve ter um tratamento para remover o item
    public boolean removeItem(Item item) {
        if (item instanceof ItemNotRemove) return false;
        int weight = item.getWeight();
        this.item.remove(item);
        this.setCapacity(-weight);
        return true;
    }

    public int removeItensCombine(int combine) {
        int remove = 0;
        for (int i = 0; i < item.size(); i++) {
            if (item.get(i) instanceof ItemCombinable) {
                if (((ItemCombinable) item.get(i)).getCombine() == combine) {
                    removeItem(item.get(i));
                    remove++;
                    i = -1; //reset
                }
            }
        }
        return remove;
    }

    public List<Item> getItemVisible() {
        List<Item> listItensVisible = new ArrayList<>();
        for (Item item : this.item) {
            if (item.isVisible()) {
                listItensVisible.add(item);
            }
        }
        return listItensVisible;
    }

    public List<Item> getItemInvisible() {
        List<Item> listItensInvisible = new ArrayList<>();
        for (Item item : this.item) {
            if (!item.isVisible()) {
                listItensInvisible.add(item);
            }
        }
        return listItensInvisible;
    }

    public void updadeInventory(Item item) {
        this.setCapacity(item.getWeight());
    }
}
