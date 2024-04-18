package service;

import backend.controller.enums.TypeMessage;
import backend.repository.entity.ItemEntity;
import backend.service.command.UsableCommand;
import backend.service.enums.TypeItem;
import backend.service.handler.UsableCoordinateHandler;
import backend.service.handler.UsableEnableHandler;
import backend.service.handler.UsableMapHandler;
import backend.service.handler.UsableSpecializationHandler;
import backend.service.interfaces.ICoordinate;
import backend.service.interfaces.IUsable;
import backend.service.model.Inventory;
import backend.service.model.Item;
import backend.service.model.ItemFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class UsableCommandTest {

    private UsableCommand cmd;
    private Map<Integer, Item> items;


    @Before
    public void create() {
        this.items = new HashMap<>();
        var itemsE = new ArrayList<ItemEntity>();
        itemsE.add(new ItemEntity(1, "chave", "utilizada para abrir algo", 3, 30, 58, "src/main/resources/image/item/chave.png"));
        itemsE.add(new ItemEntity(2, "escada", "utilizada para subir em algum lugar", 5, 20, 41, "src/main/resources/image/item/escada.png"));
        itemsE.add(new ItemEntity(11, "pa", "ferramenta usada para cavar", 3, 28, 20, "src/main/resources/image/item/pa.png"));
        itemsE.add(new ItemEntity(5, "livro", "livro antigo usado para decifrar escrita antiga", 1, 39, 49, "src/main/resources/image/item/livro.png"));
        itemsE.forEach(v -> this.items.put(v.id(), new ItemFactory().create(v)));
    }

    @Test
    public void validError1() {
        var idMap = 0;
        var coordinate = ICoordinate.getInstance(0, 0);
        var usableHandler = new UsableSpecializationHandler();
        usableHandler
                .setNextHandler(new UsableMapHandler(idMap))
                .setNextHandler(new UsableCoordinateHandler(coordinate))
                .setNextHandler(new UsableEnableHandler());
        this.cmd = new UsableCommand(this.items.get(5), new Inventory(0, 10), usableHandler);
        var msg = this.cmd.execute();
        assertEquals(TypeMessage.ITEM_ERROR_USABLE, msg);
    }

    @Test
    public void validError2() {
        var idMap = 1;
        var coordinate = ICoordinate.getInstance(15, 37);
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.items.get(1));
        var usableHandler = new UsableSpecializationHandler();
        usableHandler
                .setNextHandler(new UsableMapHandler(idMap))
                .setNextHandler(new UsableCoordinateHandler(coordinate))
                .setNextHandler(new UsableEnableHandler());
        this.cmd = new UsableCommand(this.items.get(1), inventory, usableHandler);
        var msg = this.cmd.execute();
        assertEquals(TypeMessage.USABLE_ERROR_MAP, msg);
    }

    @Test
    public void validError3() {
        var idMap = 6;
        var coordinate = ICoordinate.getInstance(10, 37);
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.items.get(1));
        var usableHandler = new UsableSpecializationHandler();
        usableHandler
                .setNextHandler(new UsableMapHandler(idMap))
                .setNextHandler(new UsableCoordinateHandler(coordinate))
                .setNextHandler(new UsableEnableHandler());
        this.cmd = new UsableCommand(this.items.get(1), inventory, usableHandler);
        var msg = this.cmd.execute();
        assertEquals(TypeMessage.USABLE_ERROR_COORDINATE, msg);
    }

    @Test
    public void validError4() {
        var usable = this.items.get(11).getSpecialization(TypeItem.USABLE);
        usable.ifPresent(specialization -> ((IUsable) specialization).setEnabled(false));
        var idMap = 4;
        var coordinate = ICoordinate.getInstance(29, 56);
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.items.get(11));
        var usableHandler = new UsableSpecializationHandler();
        usableHandler
                .setNextHandler(new UsableMapHandler(idMap))
                .setNextHandler(new UsableCoordinateHandler(coordinate))
                .setNextHandler(new UsableEnableHandler());
        this.cmd = new UsableCommand(this.items.get(11), inventory, usableHandler);
        var msg = this.cmd.execute();
        assertEquals(TypeMessage.USABLE_ERROR_ENABLE, msg);
    }

    @Test
    public void validChave() {
        var idMap = 6;
        var coordinate = ICoordinate.getInstance(15, 37);
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.items.get(1));
        var usableHandler = new UsableSpecializationHandler();
        usableHandler
                .setNextHandler(new UsableMapHandler(idMap))
                .setNextHandler(new UsableCoordinateHandler(coordinate))
                .setNextHandler(new UsableEnableHandler());
        this.cmd = new UsableCommand(this.items.get(1), inventory, usableHandler);
        var msg = this.cmd.execute();
        assertEquals(TypeMessage.USABLE_KEY, msg);
    }

    @Test
    public void validEscada() {
        var idMap = 8;
        var coordinate = ICoordinate.getInstance(19, 26);
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.items.get(2));
        var usableHandler = new UsableSpecializationHandler();
        usableHandler
                .setNextHandler(new UsableMapHandler(idMap))
                .setNextHandler(new UsableCoordinateHandler(coordinate))
                .setNextHandler(new UsableEnableHandler());
        this.cmd = new UsableCommand(this.items.get(2), inventory, usableHandler);
        var msg = this.cmd.execute();
        assertEquals(TypeMessage.USABLE_LADDER, msg);
    }

    @Test
    public void validPa() {
        var usable = this.items.get(11).getSpecialization(TypeItem.USABLE);
        usable.ifPresent(specialization -> ((IUsable) specialization).setEnabled(true));
        var idMap = 4;
        var coordinate = ICoordinate.getInstance(29, 56);
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.items.get(11));
        var usableHandler = new UsableSpecializationHandler();
        usableHandler
                .setNextHandler(new UsableMapHandler(idMap))
                .setNextHandler(new UsableCoordinateHandler(coordinate))
                .setNextHandler(new UsableEnableHandler());
        this.cmd = new UsableCommand(this.items.get(11), inventory, usableHandler);
        var msg = this.cmd.execute();
        assertEquals(TypeMessage.USABLE_SHOVEL, msg);
    }

    @Test
    public void validUndo() {
        var idMap = 6;
        var coordinate = ICoordinate.getInstance(15, 37);
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.items.get(1));
        var usableHandler = new UsableSpecializationHandler();
        usableHandler
                .setNextHandler(new UsableMapHandler(idMap))
                .setNextHandler(new UsableCoordinateHandler(coordinate))
                .setNextHandler(new UsableEnableHandler());
        this.cmd = new UsableCommand(this.items.get(1), inventory, usableHandler);
        this.cmd.execute();
        this.cmd.undo();
        assertEquals(this.items.get(1), inventory.getItem(1));
    }
}
