package repository;

import backend.repository.entity.EquipableEntity;
import backend.repository.interfaces.IEquipableEntity;
import backend.repository.singleton.EquipableRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class EquipableRepositoryTest {

    private List<IEquipableEntity> equipable;
    private List<IEquipableEntity> equipableFile;

    @Before
    public void create() {
        this.equipable = new ArrayList<>();
        this.equipable.add(new EquipableEntity(1, 10, 5));
        this.equipable.add(new EquipableEntity(2, 16, 0));

        this.equipableFile = EquipableRepository.getInstance().getAll();
    }

    @Test
    public void validAll() {
        for (int i = 0; i < this.equipable.size(); i++) {
            assertEquals(this.equipable.get(i).toString(), this.equipableFile.get(i).toString());
        }
    }

    @Test
    public void validByIdItem() {
        assertEquals(Optional.of(this.equipable.get(0)).toString(),
                EquipableRepository.getInstance().getByIdItem(this.equipable.get(0).idItem()).toString());
    }
}
