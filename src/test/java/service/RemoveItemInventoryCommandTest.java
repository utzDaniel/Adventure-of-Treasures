package service;

import backend.controller.enums.TypeMessage;
import backend.repository.entity.ItemEntity;
import backend.service.command.RemoveItemInventoryCommand;
import backend.service.enums.TypeItem;
import backend.service.interfaces.IEquippable;
import backend.service.model.Inventory;
import backend.service.model.Item;
import backend.service.model.ItemFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class RemoveItemInventoryCommandTest {

    private RemoveItemInventoryCommand cmd;
    private Map<Integer, Item> itens;

    @Before
    public void create() {
        this.itens = new HashMap<>();
        var itensE = new ArrayList<ItemEntity>();
        itensE.add(new ItemEntity(1, "chave", "utilizada para abrir algo", 3, 30, 58, "src/main/resources/image/item/chave.png"));
        itensE.add(new ItemEntity(10, "mochila", "utilizada para carregar mais coisas", 0, 22, 65, "src/main/resources/image/item/mochila.png"));
        itensE.add(new ItemEntity(15, "tesouro", "tesouro lendário dos templários", 3, 24, 62, "src/main/resources/image/item/tesouro.png"));
        itensE.add(new ItemEntity(5, "livro", "livro antigo usado para decifrar escrita antiga", 1, 39, 49, "src/main/resources/image/item/livro.png"));

        itensE.forEach(v -> this.itens.put(v.id(), new ItemFactory().create(v)));
    }

    @Test
    public void validErro1() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.itens.get(15));
        this.cmd = new RemoveItemInventoryCommand(this.itens.get(15), inventory);
        var msg = this.cmd.execute();
        assertEquals(TypeMessage.REMOVE_ITEM_ERRO, msg);
    }

    @Test
    public void validErro2() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.itens.get(10));
        var equible = this.itens.get(10).getSpecialization(TypeItem.EQUIPPABLE);
        equible.ifPresent(specialization -> ((IEquippable) specialization).setEquip(true));
        this.cmd = new RemoveItemInventoryCommand(this.itens.get(10), inventory);
        var msg = this.cmd.execute();
        assertEquals(TypeMessage.REMOVE_ITEM_ERRO_EQUIP, msg);
    }

    @Test
    public void validErro3() {
        var inventory = new Inventory(0, 10);
        this.cmd = new RemoveItemInventoryCommand(this.itens.get(10), inventory);
        var msg = this.cmd.execute();
        assertEquals(TypeMessage.INVENTORY_ITEM_ERRO, msg);
    }

    @Test
    public void validEquippable() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.itens.get(10));
        this.cmd = new RemoveItemInventoryCommand(this.itens.get(10), inventory);
        var msg = this.cmd.execute();
        assertEquals(TypeMessage.REMOVE_ITEM_INVENTORY, msg);
    }

    @Test
    public void validUsable() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.itens.get(1));
        this.cmd = new RemoveItemInventoryCommand(this.itens.get(1), inventory);
        var msg = this.cmd.execute();
        assertEquals(TypeMessage.REMOVE_ITEM_INVENTORY, msg);
    }

    @Test
    public void validCombinable() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.itens.get(5));
        this.cmd = new RemoveItemInventoryCommand(this.itens.get(5), inventory);
        var msg = this.cmd.execute();
        assertEquals(TypeMessage.REMOVE_ITEM_INVENTORY, msg);
    }

    @Test
    public void validUndo() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.itens.get(10));
        this.cmd = new RemoveItemInventoryCommand(this.itens.get(10), inventory);
        this.cmd.execute();
        this.cmd.undo();
        assertEquals(this.itens.get(10), inventory.getItem(10));
    }

}
