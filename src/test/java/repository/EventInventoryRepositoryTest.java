package repository;

import backend.repository.entity.EventInventoryEntity;
import backend.repository.interfaces.IEventInventoryEntity;
import backend.repository.singleton.EventInventoryRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class EventInventoryRepositoryTest {

    private List<IEventInventoryEntity> event;
    private List<IEventInventoryEntity> eventFile;

    @Before
    public void create() {
        this.event = new ArrayList<>();
        this.event.add(new EventInventoryEntity(1, 3, 16));
        this.event.add(new EventInventoryEntity(2, 4, 16));
        this.event.add(new EventInventoryEntity(3, 5, 8));
        this.event.add(new EventInventoryEntity(4, 6, 16));
        this.event.add(new EventInventoryEntity(5, 7, 2));
        this.event.add(new EventInventoryEntity(6, 9, 2));
        this.event.add(new EventInventoryEntity(7, 11, 1));
        this.event.add(new EventInventoryEntity(8, 12, 8));
        this.event.add(new EventInventoryEntity(9, 13, 16));
        this.event.add(new EventInventoryEntity(10, 14, 2));

        this.eventFile = EventInventoryRepository.getInstance().getAll();
    }

    @Test
    public void validAll() {
        for (int i = 0; i < this.event.size(); i++) {
            assertEquals(this.event.get(i).toString(), this.eventFile.get(i).toString());
        }
    }

    @Test
    public void validByIdItem() {
        assertEquals(Optional.of(this.event.get(0)).toString(),
                EventInventoryRepository.getInstance().getByIdItem(this.event.get(0).idItem()).toString());
    }
}
