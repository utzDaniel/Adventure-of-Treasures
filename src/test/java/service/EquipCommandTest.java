package service;

import backend.controller.enums.TypeMessage;
import backend.repository.entity.ItemEntity;
import backend.service.command.EquipableCommand;
import backend.service.model.Inventory;
import backend.service.model.Item;
import backend.service.model.ItemFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class EquipCommandTest {

    private EquipableCommand cmd;
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
        this.cmd = new EquipableCommand(this.itens.get(10), inventory);
        var msg = this.cmd.execute();
        assertEquals(Optional.of(TypeMessage.EQUIP_SCHOOLBAG), msg);
    }

    @Test
    public void validMochila2() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.itens.get(10));
        this.cmd = new EquipableCommand(this.itens.get(10), inventory);
        this.cmd.execute();
        var msg = this.cmd.execute();
        assertEquals(Optional.of(TypeMessage.UNEQUIP_SCHOOLBAG), msg);
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
        assertEquals(Optional.of(TypeMessage.UNEQUIP_ERRO_SCHOOLBAG), msg);
    }

    @Test
    public void validTocha1() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.itens.get(16));
        this.cmd = new EquipableCommand(this.itens.get(16), inventory);
        var msg = this.cmd.execute();
        assertEquals(Optional.of(TypeMessage.EQUIP_TORCH), msg);
    }

    @Test
    public void validTocha2() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.itens.get(16));
        this.cmd = new EquipableCommand(this.itens.get(16), inventory);
        this.cmd.execute();
        var msg = this.cmd.execute();
        assertEquals(Optional.of(TypeMessage.UNEQUIP_TORCH), msg);
    }

}
