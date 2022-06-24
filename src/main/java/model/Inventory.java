package model;

import service.AddItemInventory;
import service.RemoveItem;

import java.util.*;
import java.util.stream.Collectors;

public final class Inventory {

    private int capacity;
    private int maxCapacity;
    private boolean isInventory;
    private final Map<String, Item> item;

    public Inventory() {
        this.capacity = 0;
        this.maxCapacity = 10;
        this.isInventory = false;
        item = new HashMap<>();
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void updadeCapacity(int weight) {
        this.capacity += weight;
    }

    public int getMaxCapacity() {
        return this.maxCapacity;
    }

    public void updadeMaxCapacity(int updade) {
        this.maxCapacity += updade;
    }

    public void removeItem(Item item) {
        new RemoveItem(this, item).run();
    }

    public void addItem(Item item) {
        new AddItemInventory(this, item).run();
    }

    public Item getItem(String nameItem) {
        return item.get(nameItem);
    }

    public Map<String, Item> getMapItem() {
        return item;
    }

    public List<Item> getItemVisible() {
        List<Item> items = new ArrayList<>(item.values());
        return items.stream()
                .filter(Item::isVisible)
                .collect(Collectors.toList());
    }

    public void setItemInvisible(Item item) {
        this.item.put(item.getName(), item);
    }

    public List<Item> getItemInvisible() {
        List<Item> items = new ArrayList<>(item.values());
        return items.stream()
                .filter(item -> !item.isVisible())
                .collect(Collectors.toList());
    }

    public boolean openInventory() {
        return isInventory;
    }

    public void setOpenInventory() {
        this.isInventory = !isInventory;
    }

}
