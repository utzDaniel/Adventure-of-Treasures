package repository;

import model.builder.item.*;
import model.mapper.ItemMapper;
import util.FileUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class RepositoryItem {

    private static RepositoryItem repositoryItem;
    private final Map<String, Item> itens;
    private final Map<String, Item> itensInvisiblePlayer;

    private RepositoryItem() {
        this.itens = new HashMap<>();
        this.itensInvisiblePlayer = new HashMap<>();
        createItens();
        updateItens();
    }

    public static synchronized RepositoryItem getInstance(){
        if(Objects.isNull(repositoryItem)){
            repositoryItem = new RepositoryItem();
        }
        return repositoryItem;
    }


    private void createItens() {
        String filename = "item/item.csv";
        var file = new FileUtil<Item>(filename);
        try {
            this.itens.putAll(file.readFile(new ItemMapper()).stream()
                    .collect(Collectors.toMap(Item::getName,item1 -> item1)));
        } catch (IOException e) {
            System.exit(0);
        }
    }

    private void updateItens() {
        this.itens.get("chave").setVisible(false);
        this.itensInvisiblePlayer.put("mapa", this.itens.get("mapa"));
        this.itensInvisiblePlayer.put("escada", this.itens.get("escada"));
        this.itensInvisiblePlayer.put("tocha", this.itens.get("tocha"));
        this.itens.get("mapa").setVisible(false);
        this.itens.get("escada").setVisible(false);
        this.itens.get("tocha").setVisible(false);
    }

    public Item getItem(String name) {
        return this.itens.get(name);
    }

    public List<Item> getItemInvisiblePlayer() {
        return this.itensInvisiblePlayer.values().stream().toList();
    }

}
