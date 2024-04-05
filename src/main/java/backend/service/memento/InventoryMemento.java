package backend.service.memento;

import java.util.Set;

public record InventoryMemento(
        int capacity,
        int maxCapacity,
        boolean isInventory,
        Set<Integer> idItems) implements IMemento {

    @Override
    public String extrinsic() {
        return """
                %d%s
                %d%s
                %b%s
                %s
                """.formatted(this.capacity, Separator.FIELD,
                        this.maxCapacity, Separator.FIELD,
                        this.isInventory, Separator.FIELD,
                        this.ids())
                .replace("\n", "");
    }

    private String ids() {
        var str = new StringBuilder();
        str.append(Separator.LIST_START);
        this.idItems.stream().sorted().forEach(v -> str.append(v).append(Separator.LIST_ID));
        str.append(Separator.LIST_END);
        return str.toString();
    }
}
