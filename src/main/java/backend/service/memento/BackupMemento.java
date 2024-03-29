package backend.service.memento;

import java.util.List;

public record BackupMemento(
        List<ItemMemento> itemMementos,
        List<DoorMemento> doorMemento,
        PlayerMemento playerMemento,
        List<MapGameMemento> mapGameMemento) implements IMemento {

    @Override
    public String extrinsic() {
        return """
                %s
                %s
                %s
                %s
                """.formatted(listItemMemento(), listDoorMemento(), this.playerMemento.extrinsic(), listMapGameMemento())
                .replace("\n", "");
    }

    private String listItemMemento() {
        var str = new StringBuilder();
        this.itemMementos.stream().sorted().forEach(v -> str.append(v.extrinsic()));
        return str.toString();
    }

    private String listDoorMemento() {
        var str = new StringBuilder();
        this.doorMemento.stream().sorted().forEach(v -> str.append(v.extrinsic()));
        return str.toString();
    }

    private String listMapGameMemento() {
        var str = new StringBuilder();
        this.mapGameMemento.stream().sorted().forEach(v -> str.append(v.extrinsic()));
        return str.toString();
    }
}
