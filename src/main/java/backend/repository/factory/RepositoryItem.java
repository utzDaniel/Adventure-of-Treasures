package backend.repository.factory;

import backend.repository.interfaces.IItemEntity;
import backend.repository.mapper.ItemEntityMapper;
import backend.repository.interfaces.IRepository;
import backend.repository.util.FileUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public final class RepositoryItem implements IRepository<IItemEntity, String> {
    private final Map<String, IItemEntity> itens;

    RepositoryItem() {
        this.itens = new HashMap<>();
        createItens();
    }

    private void createItens() {
        String filename = "item/item.csv";
        var file = new FileUtil<IItemEntity>(filename);
        try {
            this.itens.putAll(file.readFile(new ItemEntityMapper()).stream()
                    .collect(Collectors.toMap(IItemEntity::name, item1 -> item1)));
        } catch (IOException e) {
            System.exit(0);
        }
    }

    @Override
    public IItemEntity get(String name) {
        return this.itens.get(name);
    }

    @Override
    public List<IItemEntity> getAll() {
        return this.itens.values().stream().toList();
    }

}
