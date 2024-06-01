package service;

import backend.controller.enums.TypeMessage;
import backend.service.command.SaveCommand;
import backend.service.memento.*;
import backend.util.FileUtil;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SaveCommandTest {

    @Test
    public void valid01() {
        var filename = "src/test/resources/save/test_save_01.txt";
        var doorMemento = new ArrayList<DoorMemento>();
        doorMemento.add(new DoorMemento(1, false));
        var move = new MoveMemento("RIGHT_FOOT_TOGETHER", 1, "SOUTH", 47, 30, "src/main/resources/image/player");
        var inventory = new InventoryMemento(0, 10, false, new HashSet<>());
        var playerMemento = new PlayerMemento(move, inventory);
        var mapGameMemento = new ArrayList<MapGameMemento>();
        mapGameMemento.add(new MapGameMemento(1, -1, new HashSet<>()));
        var memento = new BackupMemento(new ArrayList<>(), doorMemento, playerMemento, mapGameMemento);
        var save = new SaveCommand(filename, memento);
        var msg = save.execute();
        Assert.assertEquals(TypeMessage.SAVE, msg);
        var list = read(filename);
        Assert.assertEquals(memento.extrinsic(), list.get(list.size() - 1));
    }

    @Test
    public void valid02() {
        var filename = "src/test/resources/save/test_save_02.txt";
        var itemMemento = new ArrayList<ItemMemento>();
        itemMemento.add(new ItemMemento(10, 22, 65, new SpecializationMemento(true, false)));
        var doorMemento = new ArrayList<DoorMemento>();
        doorMemento.add(new DoorMemento(1, false));
        doorMemento.add(new DoorMemento(14, true));
        doorMemento.add(new DoorMemento(15, false));
        var move = new MoveMemento("RIGHT_FOOT_FORWARD", 12, "WEST", 24, 47, "src/main/resources/image/player");
        var inventory = new InventoryMemento(0, 15, false, Set.of(10));
        var playerMemento = new PlayerMemento(move, inventory);
        var mapGameMemento = new ArrayList<MapGameMemento>();
        mapGameMemento.add(new MapGameMemento(1, -1, new HashSet<>()));
        mapGameMemento.add(new MapGameMemento(12, -1, new HashSet<>()));
        var memento = new BackupMemento(itemMemento, doorMemento, playerMemento, mapGameMemento);
        var save = new SaveCommand(filename, memento);
        var msg = save.execute();
        Assert.assertEquals(TypeMessage.SAVE, msg);
        var list = read(filename);
        Assert.assertEquals(memento.extrinsic(), list.get(list.size() - 1));
    }

    @Test
    public void valid03() {
        var filename = "src/test/resources/save/test_save_03.txt";
        var itemMemento = new ArrayList<ItemMemento>();
        itemMemento.add(new ItemMemento(4, 31, 28, new SpecializationMemento(false, false)));
        itemMemento.add(new ItemMemento(7, 8, 64, new SpecializationMemento(false, false)));
        itemMemento.add(new ItemMemento(8, 20, 41, new SpecializationMemento(false, false)));
        itemMemento.add(new ItemMemento(9, 32, 16, new SpecializationMemento(false, false)));
        itemMemento.add(new ItemMemento(11, 28, 20, new SpecializationMemento(false, true)));
        var doorMemento = new ArrayList<DoorMemento>();
        doorMemento.add(new DoorMemento(1, false));
        doorMemento.add(new DoorMemento(2, true));
        doorMemento.add(new DoorMemento(4, true));
        doorMemento.add(new DoorMemento(5, true));
        doorMemento.add(new DoorMemento(6, false));
        doorMemento.add(new DoorMemento(7, true));
        doorMemento.add(new DoorMemento(13, true));
        var move = new MoveMemento("LEFT_FOOT_TOGETHER", 4, "EAST", 15, 5, "src/main/resources/image/player");
        var inventory = new InventoryMemento(3, 10, false, Set.of(8, 11));
        var playerMemento = new PlayerMemento(move, inventory);
        var mapGameMemento = new ArrayList<MapGameMemento>();
        mapGameMemento.add(new MapGameMemento(1, -1, new HashSet<>()));
        mapGameMemento.add(new MapGameMemento(2, -1, new HashSet<>()));
        mapGameMemento.add(new MapGameMemento(4, 1, new HashSet<>()));
        mapGameMemento.add(new MapGameMemento(5, -1, Set.of(7)));
        mapGameMemento.add(new MapGameMemento(6, -1, new HashSet<>()));
        mapGameMemento.add(new MapGameMemento(7, -1, Set.of(9)));
        mapGameMemento.add(new MapGameMemento(11, -1, Set.of(4)));
        var memento = new BackupMemento(itemMemento, doorMemento, playerMemento, mapGameMemento);
        var save = new SaveCommand(filename, memento);
        var msg = save.execute();
        Assert.assertEquals(TypeMessage.SAVE, msg);
        var list = read(filename);
        Assert.assertEquals(memento.extrinsic(), list.get(list.size() - 1));
    }

    @Test
    public void valid04() {
        var filename = "src/test/resources/save/test_save_04.txt";
        var itemMemento = new ArrayList<ItemMemento>();
        itemMemento.add(new ItemMemento(4, 31, 28, new SpecializationMemento(false, false)));
        itemMemento.add(new ItemMemento(6, 20, 41, new SpecializationMemento(false, false)));
        itemMemento.add(new ItemMemento(7, 8, 64, new SpecializationMemento(false, false)));
        itemMemento.add(new ItemMemento(8, 20, 41, new SpecializationMemento(false, false)));
        itemMemento.add(new ItemMemento(9, 32, 16, new SpecializationMemento(false, false)));
        var doorMemento = new ArrayList<DoorMemento>();
        doorMemento.add(new DoorMemento(1, false));
        doorMemento.add(new DoorMemento(2, true));
        doorMemento.add(new DoorMemento(4, true));
        doorMemento.add(new DoorMemento(5, true));
        doorMemento.add(new DoorMemento(6, true));
        doorMemento.add(new DoorMemento(7, true));
        doorMemento.add(new DoorMemento(8, true));
        doorMemento.add(new DoorMemento(9, false));
        doorMemento.add(new DoorMemento(10, false));
        doorMemento.add(new DoorMemento(13, true));
        var move = new MoveMemento("LEFT_FOOT_FORWARD", 8, "NORTH", 53, 38, "src/main/resources/image/player");
        var inventory = new InventoryMemento(0, 10, false, Set.of(8));
        var playerMemento = new PlayerMemento(move, inventory);
        var mapGameMemento = new ArrayList<MapGameMemento>();
        mapGameMemento.add(new MapGameMemento(1, -1, new HashSet<>()));
        mapGameMemento.add(new MapGameMemento(2, -1, new HashSet<>()));
        mapGameMemento.add(new MapGameMemento(4, -1, new HashSet<>()));
        mapGameMemento.add(new MapGameMemento(5, -1, Set.of(7)));
        mapGameMemento.add(new MapGameMemento(6, -1, new HashSet<>()));
        mapGameMemento.add(new MapGameMemento(7, -1, Set.of(9)));
        mapGameMemento.add(new MapGameMemento(8, -1, Set.of(6)));
        mapGameMemento.add(new MapGameMemento(11, -1, Set.of(4)));
        var memento = new BackupMemento(itemMemento, doorMemento, playerMemento, mapGameMemento);
        var save = new SaveCommand(filename, memento);
        var msg = save.execute();
        Assert.assertEquals(TypeMessage.SAVE, msg);
        var list = read(filename);
        Assert.assertEquals(memento.extrinsic(), list.get(list.size() - 1));
    }

    private List<String> read(String filename) {
        try {
            var fileUtil = new FileUtil<BackupMemento>(filename);
            var mementos = fileUtil.readFile(new BackupMementoMapper(), 0);
            return mementos.stream()
                    .map(BackupMemento::extrinsic)
                    .toList();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}
