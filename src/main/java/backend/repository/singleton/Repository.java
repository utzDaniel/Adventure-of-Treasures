package backend.repository.singleton;

import backend.repository.enums.Filename;
import backend.repository.interfaces.IEntity;
import backend.util.FileUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class Repository<T extends IEntity> {
    private final Map<Integer, T> map;
    private final FileUtil<T> fileUtil;
    private final Function<String, T> mapper;

    public Repository(Filename filename, Function<String, T> mapper) {
        this.map = new HashMap<>();
        this.fileUtil = new FileUtil<>(filename.getPath());
        this.mapper = mapper;
    }

    public Map<Integer, T> create() {
        try {
            this.map.putAll(
                    this.fileUtil.readFile(this.mapper, 1)
                            .stream().collect(Collectors.toMap(IEntity::id, entity -> entity)));
        } catch (IOException e) {
            System.exit(0);
        }
        return this.map;
    }

}