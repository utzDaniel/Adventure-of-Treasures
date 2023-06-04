package frontend.model.vo;

import rules.interfaces.IItem;
import rules.interfaces.IOpenInventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OpenInventoryVO implements IOpenInventory {
    private boolean open;
    private int capacity;
    private int maxCapacity;
    private List<ItemVO> itens;

    public OpenInventoryVO() {
    }

    public OpenInventoryVO(boolean open, int capacity, int maxCapacity, List<IItem> itens) {
        this.open = open;
        this.capacity = capacity;
        this.maxCapacity = maxCapacity;
        if (Objects.nonNull(itens))
            this.itens = new ArrayList<>(itens.stream()
                    .map(item -> new ItemVO(item.getIcon(), item.getCoordinate(), item.getName(), item.getDescription(),
                            item.getEffect(), item.getWeight(), item.getSpecialization(), item.isEquipped()))
                    .toList());
    }

    @Override
    public boolean isOpen() {
        return this.open;
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public int getMaxCapacity() {
        return this.maxCapacity;
    }

    @Override
    public List<IItem> getItens() {
        if (Objects.isNull(this.itens)) return null;
        return new ArrayList<>(this.itens.stream()
                .map(item -> new ItemVO(item.getIcon(), item.getCoordinate(), item.getName(), item.getDescription(),
                        item.getEffect(), item.getWeight(), item.getSpecialization(), item.isEquipped()))
                .toList());
    }
}
