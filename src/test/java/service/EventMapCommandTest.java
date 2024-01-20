package service;

import backend.controller.enums.TypeMessage;
import backend.repository.entity.ItemEntity;
import backend.service.command.EventMapCommand;
import backend.service.model.Item;
import backend.service.model.ItemFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class EventMapCommandTest {

    private EventMapCommand cmd;
    private Map<Integer, Item> itens;


    @Before
    public void create() {
        this.itens = new HashMap<>();
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
    public void validMapTocha1() {
        this.cmd = new EventMapCommand(this.itens.get(16));
        var msg = this.cmd.execute();
        assertEquals(Optional.of(TypeMessage.EVENT_MAP_TORCH), msg);
    }

    @Test
    public void validMapEscada1() {
        this.cmd = new EventMapCommand(this.itens.get(2));
        var msg = this.cmd.execute();
        assertEquals(Optional.of(TypeMessage.EVENT_MAP_LADDER), msg);
    }

    @Test
    public void validMapChave1() {
        this.cmd = new EventMapCommand(this.itens.get(1));
        var msg = this.cmd.execute();
        assertEquals(Optional.of(TypeMessage.EVENT_MAP_KEY), msg);
    }

    @Test
    public void validMapPa1() {
        this.cmd = new EventMapCommand(this.itens.get(11));
        var msg = this.cmd.execute();
        assertEquals(Optional.of(TypeMessage.EVENT_MAP_SHOVEL), msg);
    }

}
