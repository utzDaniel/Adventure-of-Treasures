package service;

import backend.controller.enums.TypeMessage;
import backend.repository.entity.ItemEntity;
import backend.service.command.CombinationCommand;
import backend.service.model.Item;
import backend.service.model.ItemFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class CombinationCommandTest {

    private CombinationCommand cmd;
    private Map<Integer, Item> itens;


    @Before
    public void create() {
        this.itens = new HashMap<>();
        var itensE = new ArrayList<ItemEntity>();
        itensE.add(new ItemEntity(5, "livro", "livro antigo usado para decifrar escrita antiga", 1, 39, 49, "src/main/resources/image/item/livro.png"));
        itensE.add(new ItemEntity(12, "papel", "papel escrito em lingua antiga", 2, 32, 51, "src/main/resources/image/item/papel.png"));

        itensE.add(new ItemEntity(7, "madeiras", "madeira para construir algo", 5, 8, 64, "src/main/resources/image/item/madeiras.png"));
        itensE.add(new ItemEntity(9, "martelo", "utilizado para construir algo", 4, 32, 16, "src/main/resources/image/item/martelo.png"));
        itensE.add(new ItemEntity(14, "pregos", "utilizado para construir algo", 3, 40, 46, "src/main/resources/image/item/pregos.png"));

        itensE.add(new ItemEntity(3, "faca", "serve para cortar algo", 3, 13, 42, "src/main/resources/image/item/faca.png"));
        itensE.add(new ItemEntity(4, "frasco", "contêm algum líquido inflamável", 3, 31, 28, "src/main/resources/image/item/frasco.png"));
        itensE.add(new ItemEntity(6, "madeira", "cabo de madeira velho", 5, 20, 41, "src/main/resources/image/item/madeira.png"));
        itensE.add(new ItemEntity(13, "pederneira", "item específico para fazer fogo", 2, 20, 44, "src/main/resources/image/item/pederneira.png"));

        itensE.forEach(v -> this.itens.put(v.id(), ItemFactory.create(v)));
    }

    @Test
    public void validMap1() {
        var list = new ArrayList<Item>();
        list.add(this.itens.get(5));
        list.add(this.itens.get(12));
        this.cmd = new CombinationCommand(list);
        var msg = this.cmd.execute();
        assertEquals(Optional.of(TypeMessage.COMBINE_MAP), msg);
    }

    @Test
    public void validMap2() {
        var item1 = new ItemEntity(10, "mochila", "utilizada para carregar mais coisas", 0, 22, 65, "src/main/resources/image/item/mochila.png");
        var item = ItemFactory.create(item1);
        var list = new ArrayList<Item>();
        list.add(this.itens.get(5));
        list.add(item);
        this.cmd = new CombinationCommand(list);
        var msg = this.cmd.execute();
        assertEquals(Optional.of(TypeMessage.COMBINE_ERRO_ALL), msg);
    }

    @Test
    public void validMap3() {
        var list = new ArrayList<Item>();
        list.add(this.itens.get(5));
        this.cmd = new CombinationCommand(list);
        var msg = this.cmd.execute();
        assertEquals(Optional.of(TypeMessage.COMBINE_ERRO_INCOMPLETE), msg);
    }

    @Test
    public void validMap4() {
        var list = new ArrayList<Item>();
        list.add(this.itens.get(5));
        list.add(this.itens.get(7));
        this.cmd = new CombinationCommand(list);
        var msg = this.cmd.execute();
        assertEquals(Optional.of(TypeMessage.COMBINE_ERRO_COMBINABLE), msg);
    }

    @Test
    public void validMap5() {
        var list = new ArrayList<Item>();
        list.add(this.itens.get(5));
        list.add(this.itens.get(12));
        list.add(this.itens.get(7));
        this.cmd = new CombinationCommand(list);
        var msg = this.cmd.execute();
        assertEquals(Optional.of(TypeMessage.COMBINE_ERRO_INVALID), msg);
    }

    @Test
    public void validEscada1() {
        var list = new ArrayList<Item>();
        list.add(this.itens.get(7));
        list.add(this.itens.get(9));
        list.add(this.itens.get(14));
        this.cmd = new CombinationCommand(list);
        var msg = this.cmd.execute();
        assertEquals(Optional.of(TypeMessage.COMBINE_LADDER), msg);
    }

    @Test
    public void validEscada2() {
        var item1 = new ItemEntity(10, "mochila", "utilizada para carregar mais coisas", 0, 22, 65, "src/main/resources/image/item/mochila.png");
        var item = ItemFactory.create(item1);
        var list = new ArrayList<Item>();
        list.add(this.itens.get(7));
        list.add(this.itens.get(9));
        list.add(item);
        this.cmd = new CombinationCommand(list);
        var msg = this.cmd.execute();
        assertEquals(Optional.of(TypeMessage.COMBINE_ERRO_ALL), msg);
    }

    @Test
    public void validEscada3() {
        var list = new ArrayList<Item>();
        list.add(this.itens.get(7));
        list.add(this.itens.get(9));
        this.cmd = new CombinationCommand(list);
        var msg = this.cmd.execute();
        assertEquals(Optional.of(TypeMessage.COMBINE_ERRO_INCOMPLETE), msg);
    }

    @Test
    public void validEscada4() {
        var list = new ArrayList<Item>();
        list.add(this.itens.get(7));
        list.add(this.itens.get(9));
        list.add(this.itens.get(3));
        this.cmd = new CombinationCommand(list);
        var msg = this.cmd.execute();
        assertEquals(Optional.of(TypeMessage.COMBINE_ERRO_COMBINABLE), msg);
    }

    @Test
    public void validEscada5() {
        var list = new ArrayList<Item>();
        list.add(this.itens.get(7));
        list.add(this.itens.get(9));
        list.add(this.itens.get(14));
        list.add(this.itens.get(3));
        this.cmd = new CombinationCommand(list);
        var msg = this.cmd.execute();
        assertEquals(Optional.of(TypeMessage.COMBINE_ERRO_INVALID), msg);
    }

    @Test
    public void validTocha1() {
        var list = new ArrayList<Item>();
        list.add(this.itens.get(3));
        list.add(this.itens.get(4));
        list.add(this.itens.get(6));
        list.add(this.itens.get(13));
        this.cmd = new CombinationCommand(list);
        var msg = this.cmd.execute();
        assertEquals(Optional.of(TypeMessage.COMBINE_TORCH), msg);
    }

    @Test
    public void validTocha2() {
        var item1 = new ItemEntity(10, "mochila", "utilizada para carregar mais coisas", 0, 22, 65, "src/main/resources/image/item/mochila.png");
        var item = ItemFactory.create(item1);
        var list = new ArrayList<Item>();
        list.add(this.itens.get(3));
        list.add(this.itens.get(4));
        list.add(this.itens.get(6));
        list.add(item);
        this.cmd = new CombinationCommand(list);
        var msg = this.cmd.execute();
        assertEquals(Optional.of(TypeMessage.COMBINE_ERRO_ALL), msg);
    }

    @Test
    public void validTocha3() {
        var list = new ArrayList<Item>();
        list.add(this.itens.get(3));
        list.add(this.itens.get(4));
        list.add(this.itens.get(6));
        this.cmd = new CombinationCommand(list);
        var msg = this.cmd.execute();
        assertEquals(Optional.of(TypeMessage.COMBINE_ERRO_INCOMPLETE), msg);
    }

    @Test
    public void validTocha4() {
        var list = new ArrayList<Item>();
        list.add(this.itens.get(3));
        list.add(this.itens.get(4));
        list.add(this.itens.get(6));
        list.add(this.itens.get(7));
        this.cmd = new CombinationCommand(list);
        var msg = this.cmd.execute();
        assertEquals(Optional.of(TypeMessage.COMBINE_ERRO_COMBINABLE), msg);
    }

    @Test
    public void validTocha5() {
        var list = new ArrayList<Item>();
        list.add(this.itens.get(3));
        list.add(this.itens.get(4));
        list.add(this.itens.get(6));
        list.add(this.itens.get(13));
        list.add(this.itens.get(7));
        this.cmd = new CombinationCommand(list);
        var msg = this.cmd.execute();
        assertEquals(Optional.of(TypeMessage.COMBINE_ERRO_INVALID), msg);
    }

}
