import model.*;
import model.enums.Direction;
import org.junit.Before;
import org.junit.Test;
import repository.CreateMapGame;

import javax.swing.*;

import static org.junit.Assert.*;

public class PlayerTest {

    private Player player;

    @Before
    public void iniciacaoDoPlayerParaTeste() {
        CreateMapGame createMapGame = new CreateMapGame();
        player = Player.getInstance();
        player.setDirection(Direction.SUL.getLabel());
        player.setCurrentMap(createMapGame.getInitialScenery());
        for (Item item : createMapGame.getItemInvisiblePlayer()) {
            player.getInventory().setItemInvisible(item);
        }
    }

    @Test
    public void testarPegarItem() {
        int size = player.getInventory().getItemVisible().size();
        player.getInventory().addItem(new ItemEquipable("asqe", "utilizada para carregar mais coisas", 0,
                650, 220, null));
        player.takeItem(new ItemUsable("aq1a4e", "ferramenta usada para cavar", 0, "praia",
                200, 280, null));
        assertEquals(player.getInventory().getItemVisible().size(), size+2);
    }

    @Test
    public void validarCapacidadeMaximaDoInventory() {
        assertTrue(player.takeItem(new ItemUsable("pa", "ferramenta usada para cavar", 0,
                "praia", 200, 280, null)));
    }


    @Test
    public void buscarPeloNomeDoItem() {
        player.getInventory().addItem(new ItemEquipable("mochila22", "utilizada para carregar mais coisas", 0,
                650, 220, null));
        assertNotNull(player.getInventory().getItem("mochila22"));
    }

    @Test
    public void nullBuscarPeloNomeDoItem() {
        assertNull(player.getInventory().getItem("adas"));
    }

    @Test
    public void buscarListaDeItensVisivelNoInventario() {
        int size = player.getInventory().getItemVisible().size();
        player.getInventory().addItem(new ItemEquipable("m1154h", "utilizada para carregar mais coisas", 0,
                650, 220, null));
        assertEquals(player.getInventory().getItemVisible().size(), size+1);
    }

    @Test
    public void buscarListaDeItensNãoVisivelNoInventario() {
        assertEquals(player.getInventory().getItemInvisible().size(), 3);
    }

    @Test
    public void atualizarOTamanhoDoInventarioAoAdicionarItemNovo() {
        Item item = new ItemCombinable("faca", "serve para cortar algo", 1, 3, 420, 130, null);
        int tamanhoAnterior = player.getInventory().getCapacity();
        player.getInventory().updadeCapacity(item.getWeight());
        assertNotEquals(tamanhoAnterior, player.getInventory().getCapacity());
    }

}
