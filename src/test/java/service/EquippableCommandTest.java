package service;

import backend.controller.enums.TypeMessage;
import backend.repository.entity.ItemEntity;
import backend.service.command.EquippableCommand;
import backend.service.model.Inventory;
import backend.service.model.Item;
import backend.service.model.ItemFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class EquippableCommandTest {

    private EquippableCommand cmd;
    private Map<Integer, Item> itens;


    @Before
    public void create() {
        var factory = new ItemFactory();
        this.itens = new HashMap<>();
        var itensE = new ArrayList<ItemEntity>();
        itensE.add(new ItemEntity(10, "mochila", "utilizada para carregar mais coisas", 0, 22, 65, "src/main/resources/image/item/mochila.png"));
        itensE.add(new ItemEntity(16, "tocha", "utilizado para iluminar", 5, 20, 41, "src/main/resources/image/item/tocha.png"));
        itensE.add(new ItemEntity(5, "livro", "livro antigo usado para decifrar escrita antiga", 1, 39, 49, "src/main/resources/image/item/livro.png"));
        itensE.forEach(v -> this.itens.put(v.id(), factory.create(v)));
    }

    @Test
    public void validErro() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.itens.get(5));
        this.cmd = new EquippableCommand(this.itens.get(5), inventory);
        var msg = this.cmd.execute();
        assertEquals(TypeMessage.ITEM_ERRO_EQUIPPABLE, msg);
    }

    @Test
    public void validMochila1() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.itens.get(10));
        this.cmd = new EquippableCommand(this.itens.get(10), inventory);
        var msg = this.cmd.execute();
        assertEquals(TypeMessage.EQUIP_SCHOOLBAG, msg);
    }

    @Test
    public void validMochila2() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.itens.get(10));
        this.cmd = new EquippableCommand(this.itens.get(10), inventory);
        this.cmd.execute();
        var msg = this.cmd.execute();
        assertEquals(TypeMessage.UNEQUIP_SCHOOLBAG, msg);
    }

    @Test
    public void validMochila3() {
        var factory = new ItemFactory();
        var item = new ItemEntity(7, "madeiras", "madeira para construir algo", 15, 8, 64, "src/main/resources/image/item/madeiras.png");
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.itens.get(10));
        this.cmd = new EquippableCommand(this.itens.get(10), inventory);
        this.cmd.execute();
        inventory.addItem(factory.create(item));
        var msg = this.cmd.execute();
        assertEquals(TypeMessage.UNEQUIP_ERRO_SCHOOLBAG, msg);
    }

    @Test
    public void validTocha1() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.itens.get(16));
        this.cmd = new EquippableCommand(this.itens.get(16), inventory);
        var msg = this.cmd.execute();
        assertEquals(TypeMessage.EQUIP_TORCH, msg);
    }

    @Test
    public void validTocha2() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.itens.get(16));
        this.cmd = new EquippableCommand(this.itens.get(16), inventory);
        this.cmd.execute();
        var msg = this.cmd.execute();
        assertEquals(TypeMessage.UNEQUIP_TORCH, msg);
    }

    @Test
    public void validUndo1() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.itens.get(10));
        this.cmd = new EquippableCommand(this.itens.get(10), inventory);
        this.cmd.execute();
        this.cmd.undo();
        assertEquals(10, inventory.getMaxCapacity());
    }

    @Test
    public void validUndo2() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.itens.get(10));
        this.cmd = new EquippableCommand(this.itens.get(10), inventory);
        this.cmd.execute();
        this.cmd.execute();
        this.cmd.undo();
        assertEquals(15, inventory.getMaxCapacity());
    }

}