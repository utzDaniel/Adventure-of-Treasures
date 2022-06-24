import model.*;
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
        player.getInventory().getItemVisible().forEach(item -> System.out.println(item.getName()));
        assertEquals(player.getInventory().getItemVisible().size(), 2);
    }

    @Test
    public void validarCapacidadeMaximaDoInventory() {
        assertTrue(player.takeItem(new ItemUsable("pa", "ferramenta usada para cavar", 3,
                "praia", 200, 280, null)));
    }

    @Test
    public void invalidarCapacidadeMaximaDoInventory() {
        Item item = new ItemUsable("pa", "ferramenta usada para cavar", 3, "praia",
                200, 280, null);
        player.takeItem(item);
        player.takeItem(item);
        player.takeItem(item);
        assertFalse(player.takeItem(item));
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
    public void removerItemPorItemDentroDoInventario() {
        Item item = new ItemUsable("pa", "ferramenta usada para cavar", 3, "praia",
                200, 280, null);
        player.takeItem(item);
        assertTrue(player.dropItem(item));
    }

    @Test
    public void naoRemoverItemNotRemovePorItemDentroDoInventario() {
        Item item = new ItemNotRemove("tesouro", "tesouro lendário dos templários", null, 3, 620, 240, null);
        player.takeItem(item);
        assertFalse(player.dropItem(item));
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

    @Test
    public void andarPlayerParaLeste() {
        int positionX = player.getPositionPlayerX();
        player.walk(Direction.LESTE.getLabel(), new JLabel());
        assertEquals(positionX+10, player.getPositionPlayerX());
    }
    @Test
    public void andarPlayerParaOeste() {
        int positionX = player.getPositionPlayerX();
        player.walk(Direction.OESTE.getLabel(), new JLabel());
        assertEquals(positionX-10, player.getPositionPlayerX());
    }
    @Test
    public void andarPlayerParaSul() {
        int positionY = player.getPositionPlayerY();
        player.walk(Direction.SUL.getLabel(), new JLabel());
        assertEquals(positionY+10, player.getPositionPlayerY());
    }
    @Test
    public void andarPlayerParaNorte() {
        int positionY = player.getPositionPlayerY();
        player.walk(Direction.NORTE.getLabel(), new JLabel());
        assertEquals(positionY-10, player.getPositionPlayerY());
    }
}
