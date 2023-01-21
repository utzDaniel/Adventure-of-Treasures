package repository;

import model.builder.item.*;
import model.mapper.ItemMapper;
import util.FileUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepositoryItem {

    private final Map<String, Item> item;
    private final Map<String, Item> itemInvisiblePlayer;

    public RepositoryItem() {
        item = new HashMap<>();
        itemInvisiblePlayer = new HashMap<>();
        createItens();
    }

    private void createItens() {
        String filename = "item/item.csv";
        var file = new FileUtil<Item>(filename);
        try {
            List<Item> itens = file.readFile(new ItemMapper());
            itens.forEach(item1 -> item.put(item1.getName(),item1));
        } catch (IOException e) {
            System.exit(0);
        }



        /*

        item.put("tesouro", new ItemMission("tesouro", "tesouro lendário dos templários", 3, 620, 240, createImageItem.selectImage("tesouro"),false, null));
        item.put("madeira", new ItemCombinable("madeira", "cabo de madeira velho", 5, 410, 200, createImageItem.selectImage("madeira"),true, 3));//1
        item.put("pederneira", new ItemCombinable("pederneira", "item específico para fazer fogo", 2, 440, 200, createImageItem.selectImage("pederneira"),true, 3));//2
        item.put("madeiras", new ItemCombinable("madeiras", "madeira para construir algo", 5, 640, 80, createImageItem.selectImage("madeiras"),true, 2));//3
        item.put("faca", new ItemCombinable("faca", "serve para cortar algo", 3, 420, 130, createImageItem.selectImage("faca"),true, 3));//4
        item.put("frasco", new ItemCombinable("frasco", "contém algum líquido inflamável", 3, 280, 310, createImageItem.selectImage("frasco"),true, 3));//5
        item.put("martelo", new ItemCombinable("martelo", "utilzado para construir algo", 4, 160, 320, createImageItem.selectImage("martelo"),true, 2));//6
        item.put("papel", new ItemCombinable("papel", "papel escrito em lingua antiga", 2, 510, 320, createImageItem.selectImage("papel"),true, 1));//7
        item.put("pa", new ItemUsable("pa", "ferramenta usada para cavar", 3, 200, 280, createImageItem.selectImage("pa"),true, "praia"));//8
        item.put("pregos", new ItemCombinable("pregos", "utilzado para construir algo", 3, 460, 400, createImageItem.selectImage("pregos"),true, 2));//9
        item.put("livro", new ItemCombinable("livro", "livro antigo usado para decifrar escrita antiga", 1, 490, 390, createImageItem.selectImage("livro"),true, 1));//10
        item.put("mochila", new ItemEquipable("mochila", "utilizada para carregar mais coisas", 0, 650, 220, createImageItem.selectImage("mochila"),true,false));//11
        item.put("mapa", new ItemMission("mapa", "algo está enterrado na praia", 0, 410, 200, createImageItem.selectImage("mapa"),false, null));//12
        item.put("chave", new ItemUsable("chave", "utilizada para abir algo", 3, 580, 300, createImageItem.selectImage("chave"),true, "vila"));//13
        item.put("escada", new ItemUsable("escada", "utilizada para subir em algum lugar", 5, 410, 200, createImageItem.selectImage("escada"),true, "templo"));//14
        item.put("tocha", new ItemEquipable("tocha", "utilizado para iluminar", 5, 410, 200, createImageItem.selectImage("tocha"),true,false));//15

        */

        item.get("chave").setVisible(false);
        itemInvisiblePlayer.put("mapa", item.get("mapa"));
        itemInvisiblePlayer.put("escada", item.get("escada"));
        itemInvisiblePlayer.put("tocha", item.get("tocha"));
        item.get("mapa").setVisible(false);
        item.get("escada").setVisible(false);
        item.get("tocha").setVisible(false);
    }

    public Item getItem(String key) {
        return item.get(key);
    }

    public List<Item> getItemInvisiblePlayer() {
        return itemInvisiblePlayer.values().stream().toList();
    }

}
