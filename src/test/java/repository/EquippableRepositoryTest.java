package repository;

import backend.repository.entity.EquippableEntity;
import backend.repository.interfaces.IEquippableEntity;
import backend.repository.singleton.EquippableRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class EquippableRepositoryTest {

    private List<IEquippableEntity> equippable;
    private List<IEquippableEntity> equippableFile;

    @Before
    public void create() {
        this.equippable = new ArrayList<>();
        this.equippable.add(new EquippableEntity(1, 10, 5));
        this.equippable.add(new EquippableEntity(2, 16, 0));

        this.equippableFile = EquippableRepository.getInstance().getAll();
    }

    @Test
    public void validAll() {
        for (int i = 0; i < this.equippable.size(); i++) {
            assertEquals(this.equippable.get(i).toString(), this.equippableFile.get(i).toString());
        }
    }

    @Test
    public void validByIdItem() {
        assertEquals(Optional.of(this.equippable.get(0)).toString(),
                EquippableRepository.getInstance().getByIdItem(this.equippable.get(0).idItem()).toString());
    }
}
