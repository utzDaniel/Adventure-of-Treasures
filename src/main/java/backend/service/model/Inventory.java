package backend.service.model;

import backend.service.component.AddItemInventory;
import backend.service.component.RemoveItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Inventory {

    private int capacity;
    private int maxCapacity;
    private boolean isInventory;
    private final Map<String, IItemDomain> itens;

    public Inventory() {
        this.capacity = 0;
        this.maxCapacity = 10;
        this.isInventory = false;
        this.itens = new HashMap<>();
        // RepositoryFactory.getRepositoryItem().getAll().forEach(item -> itens.put(item.getName(), item));
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

    public void removeItem(IItemDomain item) {
        new RemoveItem(this, item).run();
    }

    public void addItem(IItemDomain item) {
        new AddItemInventory(this, item).run();
    }

    public IItemDomain getItem(String nameItem) {
        return this.itens.get(nameItem);
    }

    public Map<String, IItemDomain> getMapItem() {
        return this.itens;
    }

    public List<IItemDomain> getItemVisible() {
        return this.itens.values().stream()
                .filter(IItemDomain::isVisible)
                .collect(Collectors.toList());
    }

    public void setItemInvisible(IItemDomain item) {
        this.itens.put(item.getName(), item);
    }

    public List<IItemDomain> getItemInvisible() {
        return this.itens.values().stream()
                .filter(IItemDomain::isInvisible)
                .collect(Collectors.toList());
    }

    public boolean openInventory() {
        return isInventory;
    }

    public void setOpenInventory() {
        this.isInventory = !openInventory();
    }

}
