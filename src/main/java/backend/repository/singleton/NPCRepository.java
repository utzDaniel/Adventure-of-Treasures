package backend.repository.singleton;

import backend.repository.enums.Filename;
import backend.repository.interfaces.INPCEntity;
import backend.repository.mapper.NPCEntityMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class NPCRepository {
    private static NPCRepository repository;
    private final Map<Integer, INPCEntity> map;

    public static NPCRepository getInstance() {
        if (Objects.nonNull(repository)) return repository;
        synchronized (NPCRepository.class) {
            if (Objects.isNull(repository)) {
                var mapper = new NPCEntityMapper();
                var rep = new Repository<>(Filename.NPC, mapper);
                var map = rep.create();
                repository = new NPCRepository(map);
            }
        }
        return repository;
    }

    private NPCRepository(Map<Integer, INPCEntity> map) {
        this.map = new HashMap<>(map);
    }

    public List<INPCEntity> getAll() {
        return this.map.values().stream().toList();
    }

    public List<INPCEntity> getByIdMap(int idMap) {
        return this.map.values()
                .stream()
                .filter(v -> v.idMap() == idMap)
                .toList();
    }

}
