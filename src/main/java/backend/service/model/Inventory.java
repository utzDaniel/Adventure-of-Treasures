package backend.service.model;

import backend.service.infra.CacheService;
import backend.service.interfaces.IBackup;
import backend.service.memento.InventoryMemento;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class Inventory implements IBackup<InventoryMemento> {

    private int capacity;
    private int maxCapacity;
    private boolean isInventory;
    private final Map<Integer, Item> items;

    public Inventory(int capacity, int maxCapacity) {
        this.capacity = capacity;
        this.maxCapacity = maxCapacity;
        this.isInventory = false;
        this.items = new HashMap<>();
    }

    public int getCapacity() {
        return this.capacity;
    }

    public boolean hasSpace(int weight) {
        return weight + this.getCapacity() <= this.getMaxCapacity();
    }

    private void updateCapacity(int weight) {
        this.capacity += weight;
    }

    public int getMaxCapacity() {
        return this.maxCapacity;
    }

    public boolean updateMaxCapacity(int update) {
        if (update < 0) {
            var newCapacity = this.getMaxCapacity() + update;
            if (this.getCapacity() > newCapacity) return false;
        }
        this.maxCapacity += update;
        return true;
    }

    public Item getItem(int id) {
        return this.items.get(id);
    }

    public void addItem(Item item) {
        if (this.hasSpace(item.getWeight())) {
            this.items.put(item.getId(), item);
            this.updateCapacity(item.getWeight());
        }
    }

    public void removeItem(Item item) {
        if (Objects.nonNull(this.items.remove(item.getId())))
            this.updateCapacity(-item.getWeight());
    }

    public List<Item> getItems() {
        return this.items.values().stream().toList();
    }

    public boolean openInventory() {
        return this.isInventory;
    }

    public void setOpenInventory() {
        this.isInventory = !openInventory();
    }

    @Override
    public InventoryMemento save() {
        return new InventoryMemento(this.capacity, this.maxCapacity, this.isInventory, this.items.keySet());
    }

    @Override
    public void restore(InventoryMemento memento) {
        this.capacity = memento.capacity();
        this.maxCapacity = memento.maxCapacity();
        this.isInventory = memento.isInventory();
        this.items.clear();
        memento.idItems()
                .stream()
                .map(id -> CacheService.getItem(id).orElse(null))
                .filter(Objects::nonNull)
                .forEach(v -> this.items.put(v.getId(), v));

    }

}
