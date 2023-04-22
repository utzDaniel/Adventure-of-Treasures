package backend.model;

import backend.model.builder.item.Item;
import backend.repository.RepositoryFactory;
import rules.service.AddItemInventory;
import rules.service.RemoveItem;

import java.util.*;
import java.util.stream.Collectors;

public final class Inventory {

    private int capacity;
    private int maxCapacity;
    private boolean isInventory;
    private final Map<String, Item> itens;

    public Inventory() {
        this.capacity = 0;
        this.maxCapacity = 10;
        this.isInventory = false;
        this.itens = new HashMap<>();
        RepositoryFactory.getRepositoryItem().getAll().forEach(
                item -> {
                    this.itens.put(item.getName(),item);
                }
        );
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
        return this.itens.get(nameItem);
    }

    public Map<String, Item> getMapItem() {
        return this.itens;
    }

    public List<Item> getItemVisible() {
        return this.itens.values().stream()
                .filter(Item::isVisible)
                .collect(Collectors.toList());
    }

    public void setItemInvisible(Item item) {
        this.itens.put(item.getName(), item);
    }

    public List<Item> getItemInvisible() {
        return this.itens.values().stream()
                .filter(Item::isInvisible)
                .collect(Collectors.toList());
    }

    public boolean openInventory() {
        return isInventory;
    }

    public void setOpenInventory() {
        this.isInventory = !openInventory();
    }

}
