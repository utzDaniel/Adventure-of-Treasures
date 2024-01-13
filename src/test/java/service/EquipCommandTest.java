package service;

import backend.controller.enums.TypeMessage;
import backend.repository.entity.ItemEntity;
import backend.service.command.CommandTool;
import backend.service.command.EventCommand;
import backend.service.command.TypeMessageCombination;
import backend.service.enums.ActionItem;
import backend.service.enums.Commands;
import backend.service.model.Inventory;
import backend.service.model.Item;
import backend.service.model.ItemFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class EquipCommandTest {

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
    }

    @Test
    public void validMochila1() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.itens.get(10));
        this.tool = new CommandTool(new Commands[]{Commands.EQUIPAR}, this.itens.get(10), inventory);
        var msg = this.tool.execute();
        assertEquals(TypeMessage.EQUIP_SCHOOLBAG, msg);
    }

    @Test
    public void validMochila2() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.itens.get(10));
        this.tool = new CommandTool(new Commands[]{Commands.EQUIPAR}, this.itens.get(10), inventory);
        this.tool.execute();
        var msg = this.tool.execute();
        assertEquals(TypeMessage.UNEQUIP_SCHOOLBAG, msg);
    }

    @Test
    public void validMochila3() {
        var item = new ItemEntity(7, "madeiras", "madeira para construir algo", 15, 8, 64, "src/main/resources/image/item/madeiras.png");
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.itens.get(10));
        this.tool = new CommandTool(new Commands[]{Commands.EQUIPAR}, this.itens.get(10), inventory);
        this.tool.execute();
        inventory.addItem(ItemFactory.create(item));
        var msg = this.tool.execute();
        assertEquals(TypeMessage.UNEQUIP_ERRO_SCHOOLBAG, msg);
    }

    @Test
    public void validMochila4() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.itens.get(10));
        this.tool = new CommandTool(ActionItem.EQUIP.getCommands(), this.itens.get(10), inventory);
        var msg = this.tool.execute();
        assertEquals(TypeMessage.EQUIP_SCHOOLBAG, msg);
    }

    @Test
    public void validMochila5() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.itens.get(10));
        this.tool = new CommandTool(ActionItem.EQUIP.getCommands(), this.itens.get(10), inventory);
        this.tool.execute();
        var msg = this.tool.execute();
        assertEquals(TypeMessage.UNEQUIP_SCHOOLBAG, msg);
    }

    @Test
    public void validMochila6() {
        var item = new ItemEntity(7, "madeiras", "madeira para construir algo", 15, 8, 64, "src/main/resources/image/item/madeiras.png");
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.itens.get(10));
        this.tool = new CommandTool(ActionItem.EQUIP.getCommands(), this.itens.get(10), inventory);
        this.tool.execute();
        inventory.addItem(ItemFactory.create(item));
        var msg = this.tool.execute();
        assertEquals(TypeMessage.UNEQUIP_ERRO_SCHOOLBAG, msg);
    }

    @Test
    public void validTocha1() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.itens.get(16));
        this.tool = new CommandTool(new Commands[]{Commands.EQUIPAR}, this.itens.get(16), inventory);
        var msg = this.tool.execute();
        assertEquals(TypeMessage.EQUIP_TORCH, msg);
    }

    @Test
    public void validTocha2() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.itens.get(16));
        this.tool = new CommandTool(new Commands[]{Commands.EQUIPAR}, this.itens.get(16), inventory);
        this.tool.execute();
        var msg = this.tool.execute();
        assertEquals(TypeMessage.UNEQUIP_TORCH, msg);
    }

    @Test
    public void validTocha3() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.itens.get(16));
        this.tool = new CommandTool(new Commands[]{Commands.EVENTO}, this.itens.get(16), inventory);
        var msg = this.tool.execute();
        assertEquals(TypeMessage.EVENT_TORCH, msg);
    }

    @Test
    public void validTocha4() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.itens.get(16));
        this.tool = new CommandTool(ActionItem.EQUIP.getCommands(), this.itens.get(16), inventory);
        var msg = this.tool.execute();
        assertEquals(TypeMessage.EQUIP_EVENT_TORCH, msg);
    }



    @Test
    public void validTocha5() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.itens.get(16));
        this.tool = new CommandTool(ActionItem.EQUIP.getCommands(), this.itens.get(16), inventory);
        this.tool.execute();
        var msg = this.tool.execute();
        assertEquals(TypeMessage.UNEQUIP_TORCH, msg);
    }

}
