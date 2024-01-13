package repository;

import backend.repository.entity.ExitEntity;
import backend.repository.interfaces.IExitEntity;
import backend.repository.singleton.ExitRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ExitRepositoryTest {

    private List<IExitEntity> exits;
    private List<IExitEntity> exitsFile;

    @Before
    public void create() {
        this.exits = new ArrayList<>();
        this.exits.add(new ExitEntity(1, 1, "norte", 5));
        this.exits.add(new ExitEntity(2, 1, "oeste", 2));
        this.exits.add(new ExitEntity(3, 1, "leste", 4));
        this.exits.add(new ExitEntity(4, 2, "leste", 1));
        this.exits.add(new ExitEntity(5, 4, "oeste", 1));
        this.exits.add(new ExitEntity(6, 5, "norte", 6));
        this.exits.add(new ExitEntity(7, 5, "sul", 1));
        this.exits.add(new ExitEntity(8, 6, "sul", 5));

        this.exitsFile = ExitRepository.getInstance().getAll();
    }

    @Test
    public void validAll() {
        for (int i = 0; i < this.exits.size(); i++) {
            assertEquals(this.exits.get(i).toString(), this.exitsFile.get(i).toString());
        }
    }
}
