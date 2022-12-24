import model.*;
import model.enums.Direction;
import org.junit.Before;
import org.junit.Test;
import repository.CreateMapGame;

import javax.swing.*;

import static org.junit.Assert.*;

public class PlayerTest {

    private Player player;
    private JLabel jLabel = new JLabel();

    @Before
    public void iniciacaoDoPlayerParaTeste() {
        CreateMapGame createMapGame = new CreateMapGame();
        player = new Player();
        player.setDirection(Direction.SUL.getLabel());
        player.setCurrentMap(createMapGame.getInitialScenery());
        for (Item item : createMapGame.getItemInvisiblePlayer()) {
            player.getInventory().setItemInvisible(item);
        }
        player.getInventory().addItem(new ItemEquipable("mochila", "utilizada para carregar mais coisas", 0,
                650, 220, null));

    }

    @Test
    public void testarPegarItem() {
        player.takeItem(new ItemUsable("pa", "ferramenta usada para cavar", 3, "praia",
                200, 280, null));
        assertEquals(player.getInventory().getItemVisible().size(), 2);
    }

    @Test
    public void validarCapacidadeMaximaDoInventory() {
        assertTrue(player.takeItem(new ItemUsable("pa", "ferramenta usada para cavar", 3,
                "praia", 200, 280, null)));
    }


    @Test
    public void buscarPeloNomeDoItem() {
        assertNotNull(player.getInventory().getItem("mochila"));
    }

    @Test
    public void nullBuscarPeloNomeDoItem() {
        assertNull(player.getInventory().getItem("adas"));
    }

    @Test
    public void buscarListaDeItensVisivelNoInventario() {
        assertEquals(player.getInventory().getItemVisible().size(), 1);
    }

    @Test
    public void buscarListaDeItensNãoVisivelNoInventario() {
        assertEquals(player.getInventory().getItemInvisible().size(), 3);
    }

    @Test
    public void atualizarOTamanhoDoInventarioAoAdicionarItemNovo() {
        Item item = new ItemCombinable("faca", "serve para cortar algo", 3, 3, 420, 130, null);
        int tamanhoAnterio = player.getInventory().getCapacity();
        player.getInventory().updadeCapacity(item.getWeight());
        assertNotEquals(tamanhoAnterio, player.getInventory().getCapacity());
    }

}
