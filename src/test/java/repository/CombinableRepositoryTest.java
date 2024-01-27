package repository;

import backend.repository.entity.CombinableEntity;
import backend.repository.interfaces.ICombinableEntity;
import backend.repository.singleton.CombinableRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class CombinableRepositoryTest {

    private List<ICombinableEntity> combinable;
    private List<ICombinableEntity> combinableFile;

    @Before
    public void create() {
        this.combinable = new ArrayList<>();
        this.combinable.add(new CombinableEntity(1, 3, 3));
        this.combinable.add(new CombinableEntity(2, 4, 3));
        this.combinable.add(new CombinableEntity(3, 5, 2));
        this.combinable.add(new CombinableEntity(4, 6, 3));
        this.combinable.add(new CombinableEntity(5, 7, 1));
        this.combinable.add(new CombinableEntity(6, 9, 1));
        this.combinable.add(new CombinableEntity(7, 12, 2));
        this.combinable.add(new CombinableEntity(8, 13, 3));
        this.combinable.add(new CombinableEntity(9, 14, 1));

        this.combinableFile = CombinableRepository.getInstance().getAll();
    }

    @Test
    public void validAll() {
        for (int i = 0; i < this.combinable.size(); i++) {
            assertEquals(this.combinable.get(i).toString(), this.combinableFile.get(i).toString());
        }
    }

    @Test
    public void validByIdItem() {
        var list1 = CombinableRepository.getInstance().getByIdItem(this.combinable.get(0).idItem());
        var list2 = this.combinable.stream().filter(v -> v.combination() == this.combinable.get(0).combination()).toList();
        for (ICombinableEntity entity : list2) {
            var item = list1.stream().filter(v -> v.id() == entity.id()).findFirst();
            assertEquals(Optional.of(entity).toString(), item.toString());
        }
    }
}
