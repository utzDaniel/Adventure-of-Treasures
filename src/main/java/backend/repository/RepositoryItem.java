package backend.repository;

import backend.model.builder.item.Item;
import backend.mapper.ItemMapper;
import backend.util.FileUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class RepositoryItem implements Repository<Item> {
    private final Map<String, Item> itens;

    RepositoryItem() {
        this.itens = new HashMap<>();
        createItens();
    }

    private void createItens() {
        String filename = "item/item.csv";
        var file = new FileUtil<Item>(filename);
        try {
            this.itens.putAll(file.readFile(new ItemMapper()).stream()
                    .collect(Collectors.toMap(Item::getName, item1 -> item1)));
        } catch (IOException e) {
            System.exit(0);
        }
    }

    @Override
    public Item get(String name) {
        return this.itens.get(name);
    }

    @Override
    public List<Item> getAll() {
        return this.itens.values().stream().toList();
    }

}
