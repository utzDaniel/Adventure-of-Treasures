package backend.repository.singleton;

import backend.repository.enums.Filename;
import backend.repository.interfaces.IExitEntity;
import backend.repository.mapper.ExitEntityMapper;
import backend.repository.util.Repository;

import java.util.*;

public final class ExitRepository {
    private static ExitRepository repository;
    private final Map<Integer, IExitEntity> map;

    public static ExitRepository getInstance() {
        if (Objects.nonNull(repository)) return repository;
        synchronized (ExitRepository.class) {
            if (Objects.isNull(repository)) {
                var mapper = new ExitEntityMapper();
                var rep = new Repository<>(Filename.EXIT, mapper);
                var map = rep.create();
                repository = new ExitRepository(map);
            }
            return repository;
        }
    }

    private ExitRepository(Map<Integer, IExitEntity> map) {
        this.map = new HashMap<>(map);
    }

    public Optional<IExitEntity> getById(int id) {
        return Optional.ofNullable(this.map.get(id));
    }

    public List<IExitEntity> getByIdMapOri(int idMapOri) {
        return this.map.values()
                .stream()
                .filter(v -> v.idMapOri() == idMapOri)
                .toList();
    }

    public List<IExitEntity> getByIdMapExt(int idMapExt) {
        return this.map.values()
                .stream()
                .filter(v -> v.idMapExt() == idMapExt)
                .toList();
    }

    public List<IExitEntity> getAll() {
        return this.map.values().stream().toList();
    }

}
