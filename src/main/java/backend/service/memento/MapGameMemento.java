package backend.service.memento;

import java.util.Set;

public record MapGameMemento(
        int id,
        String image,
        Set<Integer> idItens) implements IMemento, Comparable<MapGameMemento> {

    @Override
    public String extrinsic() {
        return """
                %d%s
                %s%s
                %s%s
                """.formatted(this.id, Separator.FIELD,
                this.image, Separator.FIELD,
                this.ids(), Separator.MAP_GAME
        ).replace("\n", "");
    }

    private String ids() {
        var str = new StringBuilder();
        str.append(Separator.LIST_START);
        this.idItens.stream().sorted().forEach(v -> str.append(v).append(Separator.LIST_ID));
        str.append(Separator.LIST_END);
        return str.toString();
    }

    @Override
    public int compareTo(MapGameMemento o) {
        return Integer.compare(this.id, o.id);
    }
}
