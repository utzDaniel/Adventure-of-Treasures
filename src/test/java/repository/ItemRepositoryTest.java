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
        this.itens = new ArrayList<>();
        this.itens.add(new ItemEntity(1, "chave", "utilizada para abrir algo", 3, 30, 58, this.srcImage("chave")));
        this.itens.add(new ItemEntity(2, "escada", "utilizada para subir em algum lugar", 5, 20, 41, this.srcImage("escada")));
        this.itens.add(new ItemEntity(3, "faca", "serve para cortar algo", 3, 13, 42, this.srcImage("faca")));
        this.itens.add(new ItemEntity(4, "frasco", "contêm algum líquido inflamável", 3, 31, 28, this.srcImage("frasco")));
        this.itens.add(new ItemEntity(5, "livro", "livro antigo usado para decifrar escrita antiga", 1, 39, 49, this.srcImage("livro")));
        this.itens.add(new ItemEntity(6, "madeira", "cabo de madeira velho", 5, 20, 41, this.srcImage("madeira")));
        this.itens.add(new ItemEntity(7, "madeiras", "madeira para construir algo", 5, 8, 64, this.srcImage("madeiras")));
        this.itens.add(new ItemEntity(8, "mapa", "algo está enterrado na praia", 0, 20, 41, this.srcImage("mapa")));
        this.itens.add(new ItemEntity(9, "martelo", "utilizado para construir algo", 4, 32, 16, this.srcImage("martelo")));
        this.itens.add(new ItemEntity(10, "mochila", "utilizada para carregar mais coisas", 0, 22, 65, this.srcImage("mochila")));
        this.itens.add(new ItemEntity(11, "pa", "ferramenta usada para cavar", 3, 28, 20, this.srcImage("pa")));
        this.itens.add(new ItemEntity(12, "papel", "papel escrito em lingua antiga", 2, 32, 51, this.srcImage("papel")));
        this.itens.add(new ItemEntity(13, "pederneira", "item específico para fazer fogo", 2, 20, 44, this.srcImage("pederneira")));
        this.itens.add(new ItemEntity(14, "pregos", "utilizado para construir algo", 3, 40, 46, this.srcImage("pregos")));
        this.itens.add(new ItemEntity(15, "tesouro", "tesouro lendário dos templários", 3, 24, 62, this.srcImage("tesouro")));
        this.itens.add(new ItemEntity(16, "tocha", "utilizado para iluminar", 5, 20, 41, this.srcImage("tocha")));

        this.itensFile = ItemRepository.getInstance().getAll();

    }

    @Test
    public void validAll() {
        for (int i = 0; i < itens.size(); i++) {
            assertEquals(itens.get(i).toString(), itensFile.get(i).toString());
        }
    }

    private String srcImage(String name) {
        return String.format("src/main/resources/image/item/%s.png", name);
    }
}
