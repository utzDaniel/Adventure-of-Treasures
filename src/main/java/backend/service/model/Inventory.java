package backend.service.model;

import backend.repository.singleton.ItemRepository;
import backend.service.model.builder.Item;
import backend.service.model.builder.ItemFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Inventory {

    private int capacity;
    private int maxCapacity;
    private boolean isInventory;
    private final Map<String, Item> itens;
    private List<Integer> itensAtivos;

    public Inventory(int capacity, int maxCapacity) {
        this.capacity = capacity;
        this.maxCapacity = maxCapacity;
        this.isInventory = false;
        this.itens = new HashMap<>();
        this.itensAtivos = new ArrayList<>();
        ItemRepository.getInstance().getAll()
                .stream().map(ItemFactory::create)
                .forEach(item -> itens.put(item.getName(), item));
        itens.get("chave").setVisible(true);
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

    public Item getItem(String nameItem) {
        return this.itens.get(nameItem);
    }

    public void addItem(Item item) {
        this.itens.put(item.getName(), item);
        this.updadeCapacity(item.getWeight());
    }

    public void removeItem(Item item) {
        this.itens.remove(item.getName());
    }

    public List<Item> getItemVisible() {
        return this.itens.values().stream()
                .filter(Item::isVisible)
                .collect(Collectors.toList());
    }

    public void setItemInvisible(Item item) {
        this.itens.put(item.getName(), item);
    }

    public boolean isAtivo(Item item) {
        return this.itensAtivos.stream().anyMatch(id -> id == item.getId());
    }

    public void addItemAtivo(Item item) {
        this.itensAtivos.add(item.getId());
    }

    public void removeItemAtivo(Item item) {
        this.itensAtivos.remove(item.getId());
    }

    public List<Item> getItemInvisible() {
        return this.itens.values().stream()
                .filter(Item::isInvisible)
                .collect(Collectors.toList());
    }

    public boolean openInventory() {
        return this.isInventory;
    }

    public void setOpenInventory() {
        this.isInventory = !openInventory();
    }

}
