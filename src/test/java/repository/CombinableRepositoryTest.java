package repository;

import backend.repository.entity.CombinableEntity;
import backend.repository.interfaces.ICombinableEntity;
import backend.repository.singleton.CombinableRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CombinableRepositoryTest {

    private List<ICombinableEntity> combinable;
    private List<ICombinableEntity> combinableFile;

    @Before
    public void create() {
        this.combinable = new ArrayList<>();
        this.combinable.add(new CombinableEntity(1, 8, 5));
        this.combinable.add(new CombinableEntity(2, 8, 12));
        this.combinable.add(new CombinableEntity(3, 2, 7));
        this.combinable.add(new CombinableEntity(4, 2, 9));
        this.combinable.add(new CombinableEntity(5, 2, 14));
        this.combinable.add(new CombinableEntity(6, 16, 3));
        this.combinable.add(new CombinableEntity(7, 16, 4));
        this.combinable.add(new CombinableEntity(8, 16, 6));
        this.combinable.add(new CombinableEntity(9, 16, 13));

        this.combinableFile = CombinableRepository.getInstance().getAll();
    }

    @Test
    public void validAll() {
        for (int i = 0; i < this.combinable.size(); i++) {
            assertEquals(this.combinable.get(i).toString(), this.combinableFile.get(i).toString());
        }
    }
}
