package repository;

import backend.repository.entity.ItemEntity;
import backend.repository.interfaces.IItemEntity;
import backend.repository.singleton.ItemRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ItemRepositoryTest {

    private List<IItemEntity> itens;
    private List<IItemEntity> itensFile;

    @Before
    public void create() {
        itens = new ArrayList<>();
        itens.add(new ItemEntity(1, "chave", "utilizada para abrir algo", 3, 580, 300, "src/main/resources/item/chave.png"));
        itens.add(new ItemEntity(2, "escada", "utilizada para subir em algum lugar", 5, 410, 200, "src/main/resources/item/escada.png"));
        itens.add(new ItemEntity(3, "faca", "serve para cortar algo", 3, 420, 130, "src/main/resources/item/faca.png"));
        itens.add(new ItemEntity(4, "frasco", "contém algum líquido inflamável", 3, 280, 310, "src/main/resources/item/frasco.png"));
        itens.add(new ItemEntity(5, "livro", "livro antigo usado para decifrar escrita antiga", 1, 490, 390, "src/main/resources/item/livro.png"));
        itens.add(new ItemEntity(6, "madeira", "cabo de madeira velho", 5, 410, 200, "src/main/resources/item/madeira.png"));
        itens.add(new ItemEntity(7, "madeiras", "madeira para construir algo", 5, 640, 80, "src/main/resources/item/madeiras.png"));
        itens.add(new ItemEntity(8, "mapa", "algo está enterrado na praia", 0, 410, 200, "src/main/resources/item/mapa.png"));
        itens.add(new ItemEntity(9, "martelo", "utilizado para construir algo", 4, 160, 320, "src/main/resources/item/martelo.png"));
        itens.add(new ItemEntity(10, "mochila", "utilizada para carregar mais coisas", 0, 650, 220, "src/main/resources/item/mochila.png"));
        itens.add(new ItemEntity(11, "pa", "ferramenta usada para cavar", 3, 200, 280, "src/main/resources/item/pa.png"));
        itens.add(new ItemEntity(12, "papel", "papel escrito em lingua antiga", 2, 510, 320, "src/main/resources/item/papel.png"));
        itens.add(new ItemEntity(13, "pederneira", "item específico para fazer fogo", 2, 440, 200, "src/main/resources/item/pederneira.png"));
        itens.add(new ItemEntity(14, "pregos", "utilizado para construir algo", 3, 460, 400, "src/main/resources/item/pregos.png"));
        itens.add(new ItemEntity(15, "tesouro", "tesouro lendário dos templários", 3, 620, 240, "src/main/resources/item/tesouro.png"));
        itens.add(new ItemEntity(16, "tocha", "utilizado para iluminar", 5, 410, 200, "src/main/resources/item/tocha.png"));

        itensFile = ItemRepository.getInstance().getAll();

    }

    @Test
    public void validAll() {
        for (int i = 0; i < itens.size(); i++) {
            assertEquals(itens.get(i).toString(), itensFile.get(i).toString());
        }
    }
}
