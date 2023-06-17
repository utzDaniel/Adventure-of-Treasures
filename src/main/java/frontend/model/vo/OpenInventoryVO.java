package frontend.model.vo;

import backend.controller.interfaces.IItemDTO;
import backend.controller.interfaces.IInventoryResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OpenInventoryVO implements IInventoryResponse {
    private boolean open;
    private int capacity;
    private int maxCapacity;
    private List<ItemVO> itens;

    public OpenInventoryVO() {
    }

    public OpenInventoryVO(boolean open, int capacity, int maxCapacity, List<IItemDTO> itens) {
        this.open = open;
        this.capacity = capacity;
        this.maxCapacity = maxCapacity;
        if (Objects.nonNull(itens))
            this.itens = new ArrayList<>(itens.stream()
                    .map(item -> new ItemVO(item.icon(), item.coordinate(), item.name(), item.description(),
                            item.effect(), item.weight(), item.specialization(), item.isEquipped()))
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
    public List<IItemDTO> getItens() {
        if (Objects.isNull(this.itens)) return null;
        return new ArrayList<>(this.itens.stream()
                .map(item -> new ItemVO(item.icon(), item.coordinate(), item.name(), item.description(),
                        item.effect(), item.weight(), item.specialization(), item.isEquipped()))
                .toList());
    }
}
