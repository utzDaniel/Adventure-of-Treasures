package backend.service.model;

import backend.repository.singleton.ItemRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Inventory {

    private int capacity;
    private int maxCapacity;
    private boolean isInventory;
    private final Map<Integer, Item> itens;
    private final Map<Integer, Item> itensAtivos;

    public Inventory(int capacity, int maxCapacity) {
        this.capacity = capacity;
        this.maxCapacity = maxCapacity;
        this.isInventory = false;
        this.itens = new HashMap<>();
        this.itensAtivos =  new HashMap<>();
        ItemRepository.getInstance().getAll()
                .stream().map(ItemFactory::create)
                .forEach(item -> itens.put(item.getId(), item));
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

    public Item getItem(int id) {
        return this.itens.get(id);
    }

    public void addItem(Item item) {
        this.itens.put(item.getId(), item);
        this.updadeCapacity(item.getWeight());
    }

    public void removeItem(Item item) {
        this.itens.remove(item.getId());
    }

    public List<Item> getItens() {
        return this.itens.values().stream().toList();
    }

    public boolean isAtivo(Item item) {
        return this.itensAtivos.keySet().stream().anyMatch(id -> id == item.getId());
    }

    public void addItemAtivo(Item item) {
        this.itensAtivos.put(item.getId(), item);
    }

    public void removeItemAtivo(Item item) {
        this.itensAtivos.remove(item.getId());
    }

    public boolean openInventory() {
        return this.isInventory;
    }

    public void setOpenInventory() {
        this.isInventory = !openInventory();
    }

}
