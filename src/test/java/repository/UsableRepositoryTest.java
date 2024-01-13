package repository;

import backend.repository.entity.UsableEntity;
import backend.repository.interfaces.IUsableEntity;
import backend.repository.singleton.UsableRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UsableRepositoryTest {

    private List<IUsableEntity> usable;
    private List<IUsableEntity> usableFile;

    @Before
    public void create() {
        this.usable = new ArrayList<>();
        this.usable.add(new UsableEntity(1, 11, 4));
        this.usable.add(new UsableEntity(2, 2, 8));
        this.usable.add(new UsableEntity(3, 1, 6));

        this.usableFile = UsableRepository.getInstance().getAll();
    }

    @Test
    public void validAll() {
        for (int i = 0; i < this.usable.size(); i++) {
            assertEquals(this.usable.get(i).toString(), this.usableFile.get(i).toString());
        }
    }
}
