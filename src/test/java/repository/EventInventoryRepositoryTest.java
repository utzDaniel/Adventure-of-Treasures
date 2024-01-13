package repository;

import backend.repository.entity.EventInventoryEntity;
import backend.repository.interfaces.IEventInventoryEntity;
import backend.repository.singleton.EventInventoryRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EventInventoryRepositoryTest {

    private List<IEventInventoryEntity> event;
    private List<IEventInventoryEntity> eventFile;

    @Before
    public void create() {
        this.event = new ArrayList<>();
        this.event.add(new EventInventoryEntity(1, 11, 1));

        this.eventFile = EventInventoryRepository.getInstance().getAll();
    }

    @Test
    public void validAll() {
        for (int i = 0; i < this.event.size(); i++) {
            assertEquals(this.event.get(i).toString(), this.eventFile.get(i).toString());
        }
    }
}
