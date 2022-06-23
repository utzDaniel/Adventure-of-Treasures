package repository;

import model.*;

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
        CreateImageItem createImageItem = new CreateImageItem();
        item.put("tesouro", new ItemNotRemove("tesouro", "tesouro lendário dos templários", null, 3, 620, 240, createImageItem.selectImage("tesouro")));
        item.put("madeira", new ItemCombinable("madeira", "cabo de madeira velho", 5, 3, 410, 200, createImageItem.selectImage("madeira")));//1
        item.put("pederneira", new ItemCombinable("pederneira", "item específico para fazer fogo", 2, 3, 440, 200, createImageItem.selectImage("pederneira")));//2
        item.put("madeiras", new ItemCombinable("madeiras", "madeira para construir algo", 5, 2, 640, 80, createImageItem.selectImage("madeiras")));//3
        item.put("faca", new ItemCombinable("faca", "serve para cortar algo", 3, 3, 420, 130, createImageItem.selectImage("faca")));//4
        item.put("frasco", new ItemCombinable("frasco", "contém algum líquido inflamável", 3, 3, 280, 310, createImageItem.selectImage("frasco")));//5
        item.put("martelo", new ItemCombinable("martelo", "utilzado para construir algo", 4, 2, 160, 320, createImageItem.selectImage("martelo")));//6
        item.put("papel", new ItemCombinable("papel", "papel escrito em lingua antiga", 2, 1, 510, 320, createImageItem.selectImage("papel")));//7
        item.put("pa", new ItemUsable("pa", "ferramenta usada para cavar", 3, "praia", 200, 280, createImageItem.selectImage("pa")));//8
        item.put("pregos", new ItemCombinable("pregos", "utilzado para construir algo", 3, 2, 460, 400, createImageItem.selectImage("pregos")));//9
        item.put("livro", new ItemCombinable("livro", "livro antigo usado para decifrar escrita antiga", 1, 1, 490, 390, createImageItem.selectImage("livro")));//10
        item.put("mochila", new ItemEquipable("mochila", "utilizada para carregar mais coisas", 0, 650, 220, createImageItem.selectImage("mochila")));//11
        item.put("mapa", new ItemNotRemove("mapa", "algo está enterrado na praia", null, 0, 410, 200, createImageItem.selectImage("mapa")));//12
        item.put("chave", new ItemUsable("chave", "utilizada para abir algo", 3, "vila", 580, 300, createImageItem.selectImage("chave")));//13
        item.put("escada", new ItemUsable("escada", "utilizada para subir em algum lugar", 5, "templo", 410, 200, createImageItem.selectImage("escada")));//14
        item.put("tocha", new ItemEquipable("tocha", "utilizado para iluminar", 5, 410, 200, createImageItem.selectImage("tocha")));//15

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

    public void setLocalMapa(MapGame mapGame) {
        ((ItemNotRemove) item.get("mapa")).setMapGame(mapGame);
    }

    public List<Item> getItemInvisiblePlayer() {
        return itemInvisiblePlayer.values().stream().toList();
    }

}
