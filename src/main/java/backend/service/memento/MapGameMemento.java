package backend.service.memento;

import java.util.Set;

public record MapGameMemento(
        int id,
        int idDecoration,
        Set<Integer> idItems) implements IMemento, Comparable<MapGameMemento> {

    @Override
    public String extrinsic() {
        return """
                %d%s
                %d%s
                %s%s
                """.formatted(this.id, Separator.FIELD,
                this.idDecoration, Separator.FIELD,
                this.ids(), Separator.MAP_GAME
        ).replace("\n", "");
    }

    private String ids() {
        var str = new StringBuilder();
        str.append(Separator.LIST_START);
        this.idItems.stream().sorted().forEach(v -> str.append(v).append(Separator.LIST_ID));
        str.append(Separator.LIST_END);
        return str.toString();
    }

    @Override
    public int compareTo(MapGameMemento o) {
        return Integer.compare(this.id, o.id);
    }
}
