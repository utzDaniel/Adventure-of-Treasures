package backend.repository.singleton;

import backend.repository.enums.Filename;
import backend.repository.interfaces.IDecorationEntity;
import backend.repository.mapper.DecorationEntityMapper;

import java.util.*;

public final class DecorationRepository {
    private static DecorationRepository repository;
    private final Map<Integer, IDecorationEntity> map;

    public static DecorationRepository getInstance() {
        if (Objects.nonNull(repository)) return repository;
        synchronized (DecorationRepository.class) {
            if (Objects.isNull(repository)) {
                var mapper = new DecorationEntityMapper();
                var rep = new Repository<>(Filename.DECORATION, mapper);
                var map = rep.create();
                repository = new DecorationRepository(map);
            }
        }
        return repository;
    }

    private DecorationRepository(Map<Integer, IDecorationEntity> map) {
        this.map = new HashMap<>(map);
    }

    public List<IDecorationEntity> getAll() {
        return this.map.values().stream().toList();
    }

    public Optional<IDecorationEntity> getById(int id) {
        return Optional.ofNullable(this.map.get(id));
    }

}
