package backend.service.memento;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.function.Function;

public final class BackupMementoMapper implements Function<String, BackupMemento> {

    @Override
    public BackupMemento apply(String extrinsic) {
        var data = extrinsic.split(Separator.ITEM);
        var listItem = createListItem(data);
        var dataDoor = data[data.length - 1].split(Separator.DOOR);
        var listDoor = createListDoor(dataDoor);
        var dataPlayer = dataDoor[dataDoor.length - 1].split(Separator.PLAYER);
        var player = createPlayer(dataPlayer[0].split(Separator.FIELD));
        var dataMap = dataPlayer[1].split(Separator.MAP_GAME);
        var listMapGame = createListMapGame(dataMap);
        return new BackupMemento(listItem, listDoor, player, listMapGame);
    }

    private List<ItemMemento> createListItem(String[] data) {
        var list = new ArrayList<ItemMemento>();
        for (int i = 0; i < data.length - 1; i++) {
            list.add(createItem(data[i].split(Separator.FIELD)));
        }
        return list;
    }

    private ItemMemento createItem(String[] data) {
        var spe = new SpecializationCompositeMemento(Boolean.parseBoolean(data[3]), Boolean.parseBoolean(data[4]));
        return new ItemMemento(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2]), spe);
    }

    private List<DoorMemento> createListDoor(String[] data) {
        var list = new ArrayList<DoorMemento>();
        for (int i = 0; i < data.length - 1; i++) {
            list.add(createDoor(data[i].split(Separator.FIELD)));
        }
        return list;
    }

    private DoorMemento createDoor(String[] data) {
        return new DoorMemento(Integer.parseInt(data[0]), Boolean.parseBoolean(data[1]));
    }

    private PlayerMemento createPlayer(String[] data) {
        var move = createMove(data);
        var inventory = createInventory(data);
        return new PlayerMemento(move, inventory);
    }

    private MoveMemento createMove(String[] data) {
        return new MoveMemento(data[0], Integer.parseInt(data[1]), data[2], Integer.parseInt(data[3]),
                Integer.parseInt(data[4]), data[5]);
    }

    private InventoryMemento createInventory(String[] data) {
        var idItems = new HashSet<Integer>();
        var parts = data[9].substring(1, data[9].length() - 1);
        if (!parts.isEmpty()) {
            var ids = parts.split(Separator.LIST_ID);
            for (String id : ids) {
                idItems.add(Integer.parseInt(id));
            }
        }
        return new InventoryMemento(Integer.parseInt(data[6]), Integer.parseInt(data[7]),
                Boolean.parseBoolean(data[8]), idItems);
    }

    private List<MapGameMemento> createListMapGame(String[] data) {
        var list = new ArrayList<MapGameMemento>();
        for (String value : data) {
            list.add(createMapGame(value.split(Separator.FIELD)));
        }
        return list;
    }

    private MapGameMemento createMapGame(String[] data) {
        var idItems = new HashSet<Integer>();
        var parts = data[2].substring(1, data[2].length() - 1);
        if (!parts.isEmpty()) {
            var ids = parts.split(Separator.LIST_ID);
            for (String id : ids) {
                idItems.add(Integer.parseInt(id));
            }
        }
        return new MapGameMemento(Integer.parseInt(data[0]), Integer.parseInt(data[1]), idItems);
    }

}
