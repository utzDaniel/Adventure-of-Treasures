package repository;

import backend.repository.entity.ExitEntity;
import backend.repository.interfaces.IExitEntity;
import backend.repository.singleton.ExitRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class ExitRepositoryTest {

    private List<IExitEntity> exits;
    private List<IExitEntity> exitsFile;

    @Before
    public void create() {
        this.exits = new ArrayList<>();
        this.exits.add(new ExitEntity(1, 1, "NORTH", 5));
        this.exits.add(new ExitEntity(2, 1, "WEST", 2));
        this.exits.add(new ExitEntity(3, 1, "EAST", 4));
        this.exits.add(new ExitEntity(4, 2, "EAST", 1));
        this.exits.add(new ExitEntity(5, 4, "WEST", 1));
        this.exits.add(new ExitEntity(6, 5, "NORTH", 6));
        this.exits.add(new ExitEntity(7, 5, "SOUTH", 1));
        this.exits.add(new ExitEntity(8, 6, "SOUTH", 5));

        this.exitsFile = ExitRepository.getInstance().getAll();
    }

    @Test
    public void validAll() {
        for (int i = 0; i < this.exits.size(); i++) {
            assertEquals(this.exits.get(i).toString(), this.exitsFile.get(i).toString());
        }
    }

    @Test
    public void validByIdMapOri() {
        var list1 = ExitRepository.getInstance().getByIdMapOri(this.exits.get(0).idMapOri());
        var list2 = this.exits.stream().filter(v -> v.idMapOri() == this.exits.get(0).idMapOri()).toList();
        for (IExitEntity entity : list2) {
            var item = list1.stream().filter(v -> v.id() == entity.id()).findFirst();
            assertEquals(Optional.of(entity).toString(), item.toString());
        }
    }
}
