package model;

import service.AddItem;
import service.RemoveItem;

import java.util.*;
import java.util.stream.Collectors;

public final class Inventory {

    private int capacity;
    private int maxCapacity;
    private boolean isInventory;
    private final Map<String, Item> item;//alterar para HasMap

    public Inventory() {
        this.capacity = 0;
        this.maxCapacity = 10;
        this.isInventory = false;
        item = new HashMap<>();
    }

    public boolean openInventory() {
        return isInventory;
    }

    public void setOpenInventory() {
        this.isInventory = !isInventory;
    }

    public void setCapacity(int weight) {
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
        this.item.put(item.getName(), item);
    }

    public void addItem(Item item) {
        this.item.put(item.getName(), item);
    }

    public void removerItem(Item item) {
        this.item.remove(item.getName());
    }

    public boolean removeItem(Item item) {
        return new RemoveItem(this, item).run();
    }

    public boolean setItem(Item item) {
        return new AddItem(this, item).run();
    }

    public Item getItemInventory(String nameItem) {
        return item.get(nameItem);
    }

    public int removeItensCombine(int combine) {
        int remove = 0;
            for (Item value : item.values().stream().toList()) {
                if (value instanceof ICombinable) {
                    if (((ICombinable) value).getCombine() == combine) {
                        removeItem(value);
                        remove++;
                    }
            }
        }
        return remove;
    }

    public List<Item> getItemVisible() {
        List<Item> items = new ArrayList<>(item.values());
        return items.stream()
                .filter(Item::isVisible)
                .collect(Collectors.toList());
    }

    public List<Item> getItemInvisible() {
        List<Item> items = new ArrayList<>(item.values());
        return items.stream()
                .filter(item -> !item.isVisible())
                .collect(Collectors.toList());
    }

    public void updadeInventory(Item item) {
        this.setCapacity(item.getWeight());
    }

    public List<Item> getItem() {
        return new ArrayList<>(item.values());
    }
}
