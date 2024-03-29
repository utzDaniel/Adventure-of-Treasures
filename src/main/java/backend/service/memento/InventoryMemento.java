package backend.service.memento;

import java.util.Set;

public record InventoryMemento(
        int capacity,
        int maxCapacity,
        boolean isInventory,
        Set<Integer> idItens) implements IMemento {

    @Override
    public String extrinsic() {
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
        str.append("[");
        this.idItens.forEach(v -> str.append(v).append(","));
        str.append("]");
        return str.toString();
    }
}
