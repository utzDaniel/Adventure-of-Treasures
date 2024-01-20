package service;

import backend.controller.enums.TypeMessage;
import backend.repository.entity.ItemEntity;
import backend.service.command.RemoveInventoryCommand;
import backend.service.enums.TypeItem;
import backend.service.interfaces.IEquipable;
import backend.service.model.Inventory;
import backend.service.model.Item;
import backend.service.model.ItemFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class RemoveInventoryCommandTest {

    private RemoveInventoryCommand cmd;
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
        var mapaE = new ItemEntity(8, "mapa", "algo está enterrado na praia", 0, 20, 41, "src/main/resources/image/item/mapa.png");
        var mapa = ItemFactory.create(mapaE);
        this.itens.put(mapa.getId(), mapa);
        var tesouroE = new ItemEntity(15, "tesouro", "tesouro lendário dos templários", 3, 24, 62, "src/main/resources/image/item/tesouro.png");
        var tesouro = ItemFactory.create(tesouroE);
        this.itens.put(tesouro.getId(), tesouro);
    }

    @Test
    public void validMochila1() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.itens.get(10));
        this.cmd = new RemoveInventoryCommand(this.itens.get(10), inventory);
        var msg = this.cmd.execute();
        assertEquals(Optional.of(TypeMessage.REMOVE_INVENTORY), msg);
    }

    @Test
    public void validMochila2() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.itens.get(10));
        var equible = this.itens.get(10).getSpecialization(TypeItem.EQUIPABLE);
        equible.ifPresent(specialization -> ((IEquipable) specialization).setEquip(true));
        this.cmd = new RemoveInventoryCommand(this.itens.get(10), inventory);
        var msg = this.cmd.execute();
        assertEquals(Optional.of(TypeMessage.REMOVE_INVENTORY_ERRO_EQUIP), msg);
    }

    @Test
    public void validTocha1() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.itens.get(16));
        this.cmd = new RemoveInventoryCommand(this.itens.get(16), inventory);
        var msg = this.cmd.execute();
        assertEquals(Optional.of(TypeMessage.REMOVE_INVENTORY), msg);
    }

    @Test
    public void validTocha2() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.itens.get(16));
        var equible = this.itens.get(16).getSpecialization(TypeItem.EQUIPABLE);
        equible.ifPresent(specialization -> ((IEquipable) specialization).setEquip(true));
        this.cmd = new RemoveInventoryCommand(this.itens.get(16), inventory);
        var msg = this.cmd.execute();
        assertEquals(Optional.of(TypeMessage.REMOVE_INVENTORY_ERRO_EQUIP), msg);
    }

    @Test
    public void validMapa1() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.itens.get(8));
        this.cmd = new RemoveInventoryCommand(this.itens.get(8), inventory);
        var msg = this.cmd.execute();
        assertEquals(Optional.of(TypeMessage.REMOVE_INVENTORY_ERRO), msg);
    }

    @Test
    public void validTesouro1() {
        var inventory = new Inventory(0, 10);
        inventory.addItem(this.itens.get(15));
        this.cmd = new RemoveInventoryCommand(this.itens.get(15), inventory);
        var msg = this.cmd.execute();
        assertEquals(Optional.of(TypeMessage.REMOVE_INVENTORY_ERRO), msg);
    }
}
