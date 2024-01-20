package service;

import backend.controller.enums.TypeMessage;
import backend.repository.entity.ItemEntity;
import backend.service.command.CommandTool;
import backend.service.enums.ActionItem;
import backend.service.enums.TypeItem;
import backend.service.interfaces.ICoordinate;
import backend.service.interfaces.IUsable;
import backend.service.model.Inventory;
import backend.service.model.Item;
import backend.service.model.ItemFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class CommandToolTest {

    private CommandTool tool;
    private Map<Integer, Item> itens;


    @Before
    public void create() {
        this.itens = new HashMap<>();
        var mochilaE = new ItemEntity(10, "mochila", "utilizada para carregar mais coisas", 0, 22, 65, "src/main/resources/image/item/mochila.png");
        var mochila = ItemFactory.create(mochilaE);
        this.itens.put(mochila.getId(), mochila);
        var tochaE = new ItemEntity(16, "tocha", "utilizado para iluminar", 5, 20, 41, "src/main/resources/image/item/tocha.png");
        var tocha = ItemFactory.create(tochaE);
        this.itens.put(tocha.getId(), tocha);
        var chaveE = new ItemEntity(1, "chave", "utilizada para abrir algo", 3, 30, 58, "src/main/resources/image/item/chave.png");
        var chave = ItemFactory.create(chaveE);
        this.itens.put(chave.getId(), chave);
        var escadaE = new ItemEntity(2, "escada", "utilizada para subir em algum lugar", 5, 20, 41, "src/main/resources/image/item/escada.png");
        var escada = ItemFactory.create(escadaE);
        this.itens.put(escada.getId(), escada);
        var paE = new ItemEntity(11, "pa", "ferramenta usada para cavar", 3, 28, 20, "src/main/resources/image/item/pa.png");
        var pa = ItemFactory.create(paE);
        this.itens.put(pa.getId(), pa);
    }

    @Test
    public void EQUIP_validMochila1() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.itens.get(10));
        this.tool = new CommandTool(ActionItem.EQUIP.getCommands(), this.itens.get(10), inventory, 0, ICoordinate.getInstance(0, 0));
        var msg = this.tool.execute();
        assertEquals(TypeMessage.EQUIP_SCHOOLBAG, msg);
    }

    @Test
    public void EQUIP_validMochila2() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.itens.get(10));
        this.tool = new CommandTool(ActionItem.EQUIP.getCommands(), this.itens.get(10), inventory, 0, ICoordinate.getInstance(0, 0));
        this.tool.execute();
        var msg = this.tool.execute();
        assertEquals(TypeMessage.UNEQUIP_SCHOOLBAG, msg);
    }

    @Test
    public void EQUIP_validMochila3() {
        var item = new ItemEntity(7, "madeiras", "madeira para construir algo", 15, 8, 64, "src/main/resources/image/item/madeiras.png");
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.itens.get(10));
        this.tool = new CommandTool(ActionItem.EQUIP.getCommands(), this.itens.get(10), inventory, 0, ICoordinate.getInstance(0, 0));
        this.tool.execute();
        inventory.addItem(ItemFactory.create(item));
        var msg = this.tool.execute();
        assertEquals(TypeMessage.UNEQUIP_ERRO_SCHOOLBAG, msg);
    }

    @Test
    public void EQUIP_validTocha1() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.itens.get(16));
        this.tool = new CommandTool(ActionItem.EQUIP.getCommands(), this.itens.get(16), inventory, 0, ICoordinate.getInstance(0, 0));
        var msg = this.tool.execute();
        assertEquals(TypeMessage.EQUIP_EVENT_MAP_TORCH, msg);
    }

    @Test
    public void EQUIP_validTocha2() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.itens.get(16));
        this.tool = new CommandTool(ActionItem.EQUIP.getCommands(), this.itens.get(16), inventory, 0, ICoordinate.getInstance(0, 0));
        this.tool.execute();
        var msg = this.tool.execute();
        assertEquals(TypeMessage.UNEQUIP_TORCH, msg);
    }

    @Test
    public void USABLE_validChave1() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.itens.get(1));
        var idMap = 6;
        var coordinate = ICoordinate.getInstance(15, 37);
        this.tool = new CommandTool(ActionItem.USE.getCommands(), this.itens.get(1), inventory, idMap, coordinate);
        var msg = this.tool.execute();
        assertEquals(TypeMessage.EVENT_MAP_REMOVE_USABLE_KEY, msg);
    }

    @Test
    public void USABLE_validEscada1() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.itens.get(2));
        var idMap = 8;
        var coordinate = ICoordinate.getInstance(19, 26);
        this.tool = new CommandTool(ActionItem.USE.getCommands(), this.itens.get(2), inventory, idMap, coordinate);
        var msg = this.tool.execute();
        assertEquals(TypeMessage.EVENT_MAP_REMOVE_USABLE_LADDER, msg);
    }

    @Test
    public void USABLE_validPa1() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.itens.get(11));
        var usable = this.itens.get(11).getSpecialization(TypeItem.USABLE);
        usable.ifPresent(specialization -> ((IUsable) specialization).setEnabled(true));
        var idMap = 4;
        var coordinate = ICoordinate.getInstance(29, 56);
        this.tool = new CommandTool(ActionItem.USE.getCommands(), this.itens.get(11), inventory, idMap, coordinate);
        var msg = this.tool.execute();
        assertEquals(TypeMessage.EVENT_INVENTORY_EVENT_MAP_REMOVE_USABLE_SHOVEL, msg);
    }

}
