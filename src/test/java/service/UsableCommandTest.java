package service;

import backend.controller.enums.TypeMessage;
import backend.repository.entity.ItemEntity;
import backend.service.command.UsableCommand;
import backend.service.enums.TypeItem;
import backend.service.interfaces.ICoordinate;
import backend.service.interfaces.IUsable;
import backend.service.model.Item;
import backend.service.model.ItemFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class UsableCommandTest {

    private UsableCommand cmd;
    private Map<Integer, Item> itens;


    @Before
    public void create() {
        this.itens = new HashMap<>();
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
    public void validChave1() {
        var idMap = 6;
        var coordinate = ICoordinate.getInstance(15, 37);
        this.cmd = new UsableCommand(this.itens.get(1), idMap, coordinate);
        var msg = this.cmd.execute();
        assertEquals(Optional.of(TypeMessage.USABLE_KEY), msg);
    }

    @Test
    public void validChave2() {
        var idMap = 1;
        var coordinate = ICoordinate.getInstance(15, 37);
        this.cmd = new UsableCommand(this.itens.get(1), idMap, coordinate);
        var msg = this.cmd.execute();
        assertEquals(Optional.of(TypeMessage.USABLE_ERRO_MAP), msg);
    }

    @Test
    public void validChave3() {
        var idMap = 6;
        var coordinate = ICoordinate.getInstance(10, 37);
        this.cmd = new UsableCommand(this.itens.get(1), idMap, coordinate);
        var msg = this.cmd.execute();
        assertEquals(Optional.of(TypeMessage.USABLE_ERRO_COORDINATE), msg);
    }


    @Test
    public void validEscada1() {
        var idMap = 8;
        var coordinate = ICoordinate.getInstance(19, 26);
        this.cmd = new UsableCommand(this.itens.get(2), idMap, coordinate);
        var msg = this.cmd.execute();
        assertEquals(Optional.of(TypeMessage.USABLE_LADDER), msg);
    }

    @Test
    public void validEscada2() {
        var idMap = 1;
        var coordinate = ICoordinate.getInstance(19, 26);
        this.cmd = new UsableCommand(this.itens.get(2), idMap, coordinate);
        var msg = this.cmd.execute();
        assertEquals(Optional.of(TypeMessage.USABLE_ERRO_MAP), msg);
    }

    @Test
    public void validEscada3() {
        var idMap = 8;
        var coordinate = ICoordinate.getInstance(10, 26);
        this.cmd = new UsableCommand(this.itens.get(2), idMap, coordinate);
        var msg = this.cmd.execute();
        assertEquals(Optional.of(TypeMessage.USABLE_ERRO_COORDINATE), msg);
    }

    @Test
    public void validPa1() {
        var usable = this.itens.get(11).getSpecialization(TypeItem.USABLE);
        usable.ifPresent(specialization -> ((IUsable) specialization).setEnabled(true));
        var idMap = 4;
        var coordinate = ICoordinate.getInstance(29, 56);
        this.cmd = new UsableCommand(this.itens.get(11), idMap, coordinate);
        var msg = this.cmd.execute();
        assertEquals(Optional.of(TypeMessage.USABLE_SHOVEL), msg);
    }

    @Test
    public void validPa2() {
        var usable = this.itens.get(11).getSpecialization(TypeItem.USABLE);
        usable.ifPresent(specialization -> ((IUsable) specialization).setEnabled(true));
        var idMap = 1;
        var coordinate = ICoordinate.getInstance(29, 56);
        this.cmd = new UsableCommand(this.itens.get(11), idMap, coordinate);
        var msg = this.cmd.execute();
        assertEquals(Optional.of(TypeMessage.USABLE_ERRO_MAP), msg);
    }

    @Test
    public void validPa3() {
        var usable = this.itens.get(11).getSpecialization(TypeItem.USABLE);
        usable.ifPresent(specialization -> ((IUsable) specialization).setEnabled(true));
        var idMap = 4;
        var coordinate = ICoordinate.getInstance(10, 56);
        this.cmd = new UsableCommand(this.itens.get(11), idMap, coordinate);
        var msg = this.cmd.execute();
        assertEquals(Optional.of(TypeMessage.USABLE_ERRO_COORDINATE), msg);
    }

    @Test
    public void validPa4() {
        var usable = this.itens.get(11).getSpecialization(TypeItem.USABLE);
        usable.ifPresent(specialization -> ((IUsable) specialization).setEnabled(false));
        var idMap = 4;
        var coordinate = ICoordinate.getInstance(29, 56);
        this.cmd = new UsableCommand(this.itens.get(11), idMap, coordinate);
        var msg = this.cmd.execute();
        assertEquals(Optional.of(TypeMessage.USABLE_ERRO_ENABLE), msg);
    }

}
