package repository;

import backend.repository.entity.ItemMapEntity;
import backend.repository.interfaces.IItemMapEntity;
import backend.repository.singleton.ItemMapRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class ItemMapRepositoryTest {

    private List<IItemMapEntity> itemMap;
    private List<IItemMapEntity> itemMapFile;

    @Before
    public void create() {
        this.itemMap = new ArrayList<>();
        this.itemMap.add(new ItemMapEntity(1, 3, 3));
        this.itemMap.add(new ItemMapEntity(2, 4, 11));
        this.itemMap.add(new ItemMapEntity(3, 5, 11));
        this.itemMap.add(new ItemMapEntity(4, 6, 8));
        this.itemMap.add(new ItemMapEntity(5, 7, 5));
        this.itemMap.add(new ItemMapEntity(6, 9, 7));
        this.itemMap.add(new ItemMapEntity(7, 10, 12));
        this.itemMap.add(new ItemMapEntity(8, 11, 2));
        this.itemMap.add(new ItemMapEntity(9, 12, 7));
        this.itemMap.add(new ItemMapEntity(10, 13, 9));
        this.itemMap.add(new ItemMapEntity(11, 14, 3));
        this.itemMap.add(new ItemMapEntity(12, 15, 10));

        this.itemMapFile = ItemMapRepository.getInstance().getAll();
    }

    @Test
    public void validAll() {
        for (int i = 0; i < this.itemMap.size(); i++) {
            assertEquals(this.itemMap.get(i).toString(), this.itemMapFile.get(i).toString());
        }
    }

    @Test
    public void validByIdMap() {
        var list1 = ItemMapRepository.getInstance().getByIdMap(this.itemMap.get(0).idMap());
        var list2 = this.itemMap.stream().filter(v -> v.idMap() == this.itemMap.get(0).idMap()).toList();
        for (IItemMapEntity entity : list2) {
            var item = list1.stream().filter(v -> v.id() == entity.id()).findFirst();
            assertEquals(Optional.of(entity).toString(), item.toString());
        }
    }
}
