package service;

import backend.controller.enums.TypeMessage;
import backend.repository.entity.ItemEntity;
import backend.service.command.EquipableCommand;
import backend.service.model.Inventory;
import backend.service.model.Item;
import backend.service.model.ItemFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class EquipCommandTest {

    private EquipableCommand cmd;
    private Map<Integer, Item> itens;


    @Before
    public void create() {
        this.itens = new HashMap<>();
        var itensE = new ArrayList<ItemEntity>();
        itensE.add(new ItemEntity(10, "mochila", "utilizada para carregar mais coisas", 0, 22, 65, "src/main/resources/image/item/mochila.png"));
        itensE.add(new ItemEntity(16, "tocha", "utilizado para iluminar", 5, 20, 41, "src/main/resources/image/item/tocha.png"));
        itensE.add(new ItemEntity(5, "livro", "livro antigo usado para decifrar escrita antiga", 1, 39, 49, "src/main/resources/image/item/livro.png"));
        itensE.forEach(v -> this.itens.put(v.id(), ItemFactory.create(v)));
    }

    @Test
    public void validErro() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.itens.get(5));
        this.cmd = new EquipableCommand(this.itens.get(5), inventory);
        var msg = this.cmd.execute();
        assertEquals(TypeMessage.ITEM_NOT_EQUIPABLE, msg);
    }

    @Test
    public void validMochila1() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.itens.get(10));
        this.cmd = new EquipableCommand(this.itens.get(10), inventory);
        var msg = this.cmd.execute();
        assertEquals(TypeMessage.EQUIP_SCHOOLBAG, msg);
    }

    @Test
    public void validMochila2() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.itens.get(10));
        this.cmd = new EquipableCommand(this.itens.get(10), inventory);
        this.cmd.execute();
        var msg = this.cmd.execute();
        assertEquals(TypeMessage.UNEQUIP_SCHOOLBAG, msg);
    }

    @Test
    public void validMochila3() {
        var item = new ItemEntity(7, "madeiras", "madeira para construir algo", 15, 8, 64, "src/main/resources/image/item/madeiras.png");
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.itens.get(10));
        this.cmd = new EquipableCommand(this.itens.get(10), inventory);
        this.cmd.execute();
        inventory.addItem(ItemFactory.create(item));
        var msg = this.cmd.execute();
        assertEquals(TypeMessage.UNEQUIP_ERRO_SCHOOLBAG, msg);
    }

    @Test
    public void validTocha1() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.itens.get(16));
        this.cmd = new EquipableCommand(this.itens.get(16), inventory);
        var msg = this.cmd.execute();
        assertEquals(TypeMessage.EQUIP_TORCH, msg);
    }

    @Test
    public void validTocha2() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.itens.get(16));
        this.cmd = new EquipableCommand(this.itens.get(16), inventory);
        this.cmd.execute();
        var msg = this.cmd.execute();
        assertEquals(TypeMessage.UNEQUIP_TORCH, msg);
    }

}
