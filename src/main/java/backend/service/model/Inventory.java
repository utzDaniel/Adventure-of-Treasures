package backend.service.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class Inventory {

    private int capacity;
    private int maxCapacity;
    private boolean isInventory;
    private final Map<Integer, Item> itens;

    public Inventory(int capacity, int maxCapacity) {
        this.capacity = capacity;
        this.maxCapacity = maxCapacity;
        this.isInventory = false;
        this.itens = new HashMap<>();
    }

    public Inventory(int capacity, int maxCapacity, boolean isInventory, List<Item> itens) {
        this.capacity = capacity;
        this.maxCapacity = maxCapacity;
        this.isInventory = isInventory;
        this.itens = new HashMap<>();
        itens.forEach(v -> this.itens.put(v.getId(), v));
    }

    public int getCapacity() {
        return this.capacity;
    }

    public boolean hasSpace(int weight) {
        return weight + this.getCapacity() <= this.getMaxCapacity();
    }

    private void updadeCapacity(int weight) {
        this.capacity += weight;
    }

    public int getMaxCapacity() {
        return this.maxCapacity;
    }

    public boolean updadeMaxCapacity(int updade) {
        if (updade < 0) {
            var newCapacity = this.getMaxCapacity() + updade;
            if (this.getCapacity() > newCapacity) return false;
        }
        this.maxCapacity += updade;
        return true;
    }

    public Item getItem(int id) {
        return this.itens.get(id);
    }

    public void addItem(Item item) {
        if (this.hasSpace(item.getWeight())) {
            this.itens.put(item.getId(), item);
            this.updadeCapacity(item.getWeight());
        }
    }

    public void removeItem(Item item) {
        if (Objects.nonNull(this.itens.remove(item.getId())))
            this.updadeCapacity(-item.getWeight());
    }

    public List<Item> getItens() {
        return this.itens.values().stream().toList();
    }

    public boolean openInventory() {
        return this.isInventory;
    }

    public void setOpenInventory() {
        this.isInventory = !openInventory();
    }


    public String extrinsecos() {
        return """
                %d;
                %d;
                %b;
                %s;
                """.formatted(this.capacity, this.maxCapacity, this.isInventory, this.ids())
                .replace("\n", "");
    }

    private String ids() {
        var str = new StringBuilder();
        this.itens.values().forEach(v -> str.append(v.id()).append(";"));
        return str.toString();
    }

}
