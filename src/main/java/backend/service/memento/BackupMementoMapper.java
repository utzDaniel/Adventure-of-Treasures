package backend.service.memento;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.function.Function;

public final class BackupMementoMapper implements Function<String, BackupMemento> {

    @Override
    public BackupMemento apply(String extrinsic) {
        var dados = extrinsic.split(Separator.ITEM);
        var listItem = createListItem(dados);
        var dadosDoor = dados[dados.length - 1].split(Separator.DOOR);
        var listDoor = createListDoor(dadosDoor);
        var dadosPlayer = dadosDoor[dadosDoor.length - 1].split(Separator.PLAYER);
        var player = createPlayer(dadosPlayer[0].split(Separator.FIELD));
        var dadosMap = dadosPlayer[1].split(Separator.MAP_GAME);
        var listMapGame = createListMapGame(dadosMap);
        return new BackupMemento(listItem, listDoor, player, listMapGame);
    }

    private List<ItemMemento> createListItem(String[] dados) {
        var list = new ArrayList<ItemMemento>();
        for (int i = 0; i < dados.length - 1; i++) {
            list.add(createItem(dados[i].split(Separator.FIELD)));
        }
        return list;
    }

    private ItemMemento createItem(String[] dados) {
        var spe = new SpecializationCompositeMemento(Boolean.parseBoolean(dados[3]), Boolean.parseBoolean(dados[4]));
        return new ItemMemento(Integer.parseInt(dados[0]), Integer.parseInt(dados[1]), Integer.parseInt(dados[2]), spe);
    }

    private List<DoorMemento> createListDoor(String[] dados) {
        var list = new ArrayList<DoorMemento>();
        for (int i = 0; i < dados.length - 1; i++) {
            list.add(createDoor(dados[i].split(Separator.FIELD)));
        }
        return list;
    }

    private DoorMemento createDoor(String[] dados) {
        return new DoorMemento(Integer.parseInt(dados[0]), Boolean.parseBoolean(dados[1]));
    }

    private PlayerMemento createPlayer(String[] dados) {
        var move = createMove(dados);
        var inventory = createInventory(dados);
        return new PlayerMemento(move, inventory);
    }

    private MoveMemento createMove(String[] dados) {
        return new MoveMemento(dados[0], Integer.parseInt(dados[1]), dados[2], Integer.parseInt(dados[3]),
                Integer.parseInt(dados[4]), dados[5]);
    }

    private InventoryMemento createInventory(String[] dados) {
        var idItens = new HashSet<Integer>();
        var parts = dados[9].substring(1, dados[9].length() - 1);
        if (!parts.isEmpty()) {
            var ids = parts.split(Separator.LIST_ID);
            for (String id : ids) {
                idItens.add(Integer.parseInt(id));
            }
        }
        return new InventoryMemento(Integer.parseInt(dados[6]), Integer.parseInt(dados[7]),
                Boolean.parseBoolean(dados[8]), idItens);
    }

    private List<MapGameMemento> createListMapGame(String[] dados) {
        var list = new ArrayList<MapGameMemento>();
        for (String dado : dados) {
            list.add(createMapGame(dado.split(Separator.FIELD)));
        }
        return list;
    }

    private MapGameMemento createMapGame(String[] dados) {
        var idItens = new HashSet<Integer>();
        var parts = dados[2].substring(1, dados[2].length() - 1);
        if (!parts.isEmpty()) {
            var ids = parts.split(Separator.LIST_ID);
            for (String id : ids) {
                idItens.add(Integer.parseInt(id));
            }
        }
        return new MapGameMemento(Integer.parseInt(dados[0]), dados[1], idItens);
    }

}
