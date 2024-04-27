package repository;

import backend.repository.entity.NPCEntity;
import backend.repository.interfaces.INPCEntity;
import backend.repository.singleton.NPCRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class NPCRepositoryTest {

    private List<INPCEntity> npcs;
    private List<INPCEntity> npcsFile;

    @Before
    public void create() {
        this.npcs = new ArrayList<>();
        this.npcs.add(new NPCEntity(1, 1, 52, 31, 1, -1));
        this.npcs.add(new NPCEntity(2, 12, 28, 70, 15, 15));

        this.npcsFile = NPCRepository.getInstance().getAll();
    }

    @Test
    public void validAll() {
        for (int i = 0; i < this.npcs.size(); i++) {
            assertEquals(this.npcs.get(i).toString(), this.npcsFile.get(i).toString());
        }
    }

    @Test
    public void validByIdOri() {
        var list1 = NPCRepository.getInstance().getByIdMap(this.npcs.get(0).idMap());
        var list2 = this.npcs.stream().filter(v -> v.idMap() == this.npcs.get(0).idMap()).toList();
        for (INPCEntity entity : list2) {
            var item = list1.stream().filter(v -> v.id() == entity.id()).findFirst();
            assertEquals(Optional.of(entity).toString(), item.toString());
        }
    }
}
