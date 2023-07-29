package repository;

import backend.repository.entity.ItemEntity;
import backend.repository.factory.RepositoryFactory;
import backend.repository.interfaces.IItemEntity;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RepositoryItemTest {

    private List<IItemEntity> itens;
    private List<IItemEntity> itensFile;

    @Before
    public void create() {
        itens = new ArrayList<>();
        itens.add(new ItemEntity(1,4, "chave", "utilizada para abrir algo", 3, 580, 300, "src/main/resources/item/chave.png", -1,
                false, null, "vila", true, false));
        itens.add(new ItemEntity(2,-1, "escada", "utilizada para subir em algum lugar", 5, 410, 200, "src/main/resources/item/escada.png", -1,
                false, null, "templo", true, false));
        itens.add(new ItemEntity(3,3, "faca", "serve para cortar algo", 3, 420, 130, "src/main/resources/item/faca.png", 3,
                false, null, null, true, true));
        itens.add(new ItemEntity(4,11, "frasco", "contém algum líquido inflamável", 3, 280, 310, "src/main/resources/item/frasco.png", 3,
                false, null, null, true, true));
        itens.add(new ItemEntity(5,11, "livro", "livro antigo usado para decifrar escrita antiga", 1, 490, 390, "src/main/resources/item/livro.png", 1,
                false, null, null, true, true));
        itens.add(new ItemEntity(6,8, "madeira", "cabo de madeira velho", 5, 410, 200, "src/main/resources/item/madeira.png", 3,
                false, null, null, true, true));
        itens.add(new ItemEntity(7,5, "madeiras", "madeira para construir algo", 5, 640, 80, "src/main/resources/item/madeiras.png", 2,
                false, null, null, true, true));
        itens.add(new ItemEntity(8,-1, "mapa", "algo está enterrado na praia", 0, 410, 200, "src/main/resources/item/mapa.png", -1,
                false, "praia", null, false, false));
        itens.add(new ItemEntity(9,7, "martelo", "utilizado para construir algo", 4, 160, 320, "src/main/resources/item/martelo.png", 2,
                false, null, null, true, true));
        itens.add(new ItemEntity(10,12, "mochila", "utilizada para carregar mais coisas", 0, 650, 220, "src/main/resources/item/mochila.png", -1,
                false, null, null, true, true));
        itens.add(new ItemEntity(11,2, "pa", "ferramenta usada para cavar", 3, 200, 280, "src/main/resources/item/pa.png", -1,
                false, null, "praia", true, true));
        itens.add(new ItemEntity(12,7, "papel", "papel escrito em lingua antiga", 2, 510, 320, "src/main/resources/item/papel.png", 1,
                false, null, null, true, true));
        itens.add(new ItemEntity(13,9, "pederneira", "item específico para fazer fogo", 2, 440, 200, "src/main/resources/item/pederneira.png", 3,
                false, null, null, true, true));
        itens.add(new ItemEntity(14,3, "pregos", "utilizado para construir algo", 3, 460, 400, "src/main/resources/item/pregos.png", 2,
                false, null, null, true, true));
        itens.add(new ItemEntity(15,10, "tesouro", "tesouro lendário dos templários", 3, 620, 240, "src/main/resources/item/tesouro.png", -1,
                false, "barco", null, false, true));
        itens.add(new ItemEntity(16,-1, "tocha", "utilizado para iluminar", 5, 410, 200, "src/main/resources/item/tocha.png", -1,
                false, null, null, true, false));

        itensFile = RepositoryFactory.getRepositoryItem().getAll();

    }

    @Test
    public void validAll() {
        for (int i = 0; i < itens.size(); i++) {
            assertEquals(itens.get(i).toString(), itensFile.get(i).toString());
        }
    }
}
