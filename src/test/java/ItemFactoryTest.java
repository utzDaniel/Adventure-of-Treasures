import model.Coordinate;
import model.builder.item.*;
import model.mapper.ItemMapper;
import org.junit.Before;
import org.junit.Test;
import util.FileUtil;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ItemFactoryTest {

    private ArrayList<Item> itens;
    private ArrayList<Item> itensFile;

    @Before
    public void create() {
        itens = new ArrayList<>();
        String filename1 = "src/main/resources/item/";
        itens.add(ItemUsableBuilder.builder().localUse("vila").name("chave").description("utilizada para abrir algo").weight(3)
                .coordinate(new Coordinate(580,300)).image(filename1 + "chave.png").removable(true).visible(true).build());
        itens.add(ItemUsableBuilder.builder().localUse("templo").name("escada").description("utilizada para subir em algum lugar").weight(5)
                .coordinate(new Coordinate(410,200)).image(filename1 + "escada.png").removable(true).visible(true).build());
        itens.add(ItemCombinableBuilder.builder().combine(3).name("faca").description("serve para cortar algo").weight(3)
                .coordinate(new Coordinate(420,130)).image(filename1 + "faca.png").removable(true).visible(true).build());
        itens.add(ItemCombinableBuilder.builder().combine(3).name("frasco").description("contém algum líquido inflamável").weight(3)
                .coordinate(new Coordinate(280,310)).image(filename1 + "frasco.png").removable(true).visible(true).build());
        itens.add(ItemCombinableBuilder.builder().combine(1).name("livro").description("livro antigo usado para decifrar escrita antiga").weight(1)
                .coordinate(new Coordinate(490,390)).image(filename1 + "livro.png").removable(true).visible(true).build());
        itens.add(ItemCombinableBuilder.builder().combine(3).name("madeira").description("cabo de madeira velho").weight(5)
                .coordinate(new Coordinate(410,200)).image(filename1 + "madeira.png").removable(true).visible(true).visible(true).build());
        itens.add(ItemCombinableBuilder.builder().combine(2).name("madeiras").description("madeira para construir algo").weight(5)
                .coordinate(new Coordinate(640,80)).image(filename1 + "madeiras.png").removable(true).visible(true).build());
        itens.add(ItemMissionBuilder.builder().mapGame("praia").name("mapa").description("algo está enterrado na praia").weight(0)
                .coordinate(new Coordinate(410,200)).image(filename1 + "mapa.png").removable(false).visible(true).build());
        itens.add(ItemCombinableBuilder.builder().combine(2).name("martelo").description("utilizado para construir algo").weight(4)
                .coordinate(new Coordinate(160,320)).image(filename1 + "martelo.png").removable(true).visible(true).build());
        itens.add(ItemEquipableBuilder.builder().equipped(false).name("mochila").description("utilizada para carregar mais coisas").weight(0)
                .coordinate(new Coordinate(650,220)).image(filename1 + "mochila.png").removable(true).visible(true).build());
        itens.add(ItemUsableBuilder.builder().localUse("praia").name("pa").description("ferramenta usada para cavar").weight(3)
                .coordinate(new Coordinate(200,280)).image(filename1 + "pa.png").removable(true).visible(true).build());
        itens.add(ItemCombinableBuilder.builder().combine(1).name("papel").description("papel escrito em lingua antiga").weight(2)
                .coordinate(new Coordinate(510,320)).image(filename1 + "papel.png").removable(true).visible(true).build());
        itens.add(ItemCombinableBuilder.builder().combine(3).name("pederneira").description("item específico para fazer fogo").weight(2)
                .coordinate(new Coordinate(440,200)).image(filename1 + "pederneira.png").removable(true).visible(true).build());
        itens.add(ItemCombinableBuilder.builder().combine(2).name("pregos").description("utilizado para construir algo").weight(3)
                .coordinate(new Coordinate(460,400)).image(filename1 + "pregos.png").removable(true).visible(true).build());
        itens.add(ItemMissionBuilder.builder().mapGame("barco").name("tesouro").description("tesouro lendário dos templários").weight(3)
                .coordinate(new Coordinate(620,240)).image(filename1 + "tesouro.png").removable(false).visible(true).build());
        itens.add(ItemEquipableBuilder.builder().equipped(false).name("tocha").description("utilizado para iluminar").weight(5)
                .coordinate(new Coordinate(410,200)).image(filename1 + "tocha.png").removable(true).visible(true).build());

        itensFile = new ArrayList<>();
        String filename = "item/item.csv";
        var file = new FileUtil<Item>(filename);
        try {
            itensFile = (ArrayList<Item>) file.readFile(new ItemMapper());
        } catch (IOException e) {
            System.exit(0);
        }

    }

    @Test
    public void validAllItens() {
        for (int i = 0; i < itens.size(); i++) {
            assertEquals(itens.get(i).toString(), itensFile.get(i).toString());
        }
    }
}
