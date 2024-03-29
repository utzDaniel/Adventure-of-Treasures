package backend.service.memento;

import java.util.Set;

public record MapGameMemento(
        int id,
        String image,
        Set<Integer> idItens) implements IMemento {

    @Override
    public String extrinsic() {
        return """
                %d;
                %s;
                %s;mapGame;
                """.formatted(this.id, this.image, this.ids()).replace("\n", "");
    }

    private String ids() {
        var str = new StringBuilder();
        str.append("[");
        this.idItens.forEach(v -> str.append(v).append(","));
        str.append("]");
        return str.toString();
    }
}
