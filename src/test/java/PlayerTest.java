import model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import repository.CreateMapGame;

import static org.junit.Assert.*;

public class PlayerTest {

    private Player player;

    @Before
    public void iniciacaoDoPlayerParaTeste(){
        CreateMapGame createMapGame = new CreateMapGame();
        player = new Player();
        player.setCurrentMap(createMapGame.getInitialScenery());
        for(Item item :createMapGame.getItemInvisiblePlayer() ){
            player.setItem(item);
        }
        player.setItem(new ItemEquipable("mochila", "utilizada para carregar mais coisas", 0,
                650,220,null));

    }

    @Test
    public void testarSetItem(){
        player.setItem(new ItemUsable("pa", "ferramenta usada para cavar", 3, "praia",
                200,280,null));
        assertEquals(player.getItemVisible().size(),2);
    }

    @Test
    public void validarCapacidadeMaximaDoInventory(){
        assertTrue(player.validMaxCapacity(new ItemUsable("pa", "ferramenta usada para cavar", 3,
                "praia",200,280,null)));
    }

    @Test
    public void invalidarCapacidadeMaximaDoInventory(){
        Item item = new ItemUsable("pa", "ferramenta usada para cavar", 3, "praia",
                200,280,null);
        player.validMaxCapacity(item);
        player.validMaxCapacity(item);
        player.validMaxCapacity(item);
        assertFalse(player.validMaxCapacity(item));
    }

    @Test
    public void buscarPeloNomeDoItem(){
      assertNotNull(player.getItemInventory("mochila"));
    }

    @Test
    public void nullBuscarPeloNomeDoItem(){
        assertNull(player.getItemInventory("adas"));
    }

    @Test
    public void removerItemPorItemDentroDoInventario(){
        Item item = new ItemUsable("pa", "ferramenta usada para cavar", 3, "praia",
                200,280,null);
        player.setItem(item);
        assertTrue(player.removeItemInventory(item));
    }

    @Test
    public void naoRemoverItemNotRemovePorItemDentroDoInventario(){
        Item item = new ItemNotRemove("tesouro", "tesouro lendário dos templários",null, 3,620,240,null);
        player.setItem(item);
        assertFalse(player.removeItemInventory(item));
    }

    @Test
    public void removerItemPorCombinacaoDentroDoInventario(){
        Item item = new ItemCombinable("faca", "serve para cortar algo", 3, 3,420,130,null);
        player.setItem(item);
        player.setItem(item);
        player.setItem(item);
        assertEquals(player.removeItensCombine(((ICombinable) item).getCombine()),3);
    }

    @Test
    public void removerItemPorCombinacaoDentroDoInventarioComCombinacaoInvalida(){
        Item item = new ItemCombinable("asa", "saasa", 3, 88,420,130,null);
        assertEquals(player.removeItensCombine(((ICombinable) item).getCombine()),0);
    }

}
