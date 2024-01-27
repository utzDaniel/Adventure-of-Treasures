package repository;

import backend.repository.entity.EventDoorEntity;
import backend.repository.interfaces.IEventDoorEntity;
import backend.repository.singleton.EventIDoorRepository;
import backend.repository.singleton.EventInventoryRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class EventIDoorRepositoryTest {

    private List<IEventDoorEntity> event;
    private List<IEventDoorEntity> eventFile;

    @Before
    public void create() {
        this.event = new ArrayList<>();
        this.event.add(new EventDoorEntity(1, 1, 6, true));
        this.event.add(new EventDoorEntity(2, 2, 9, true));
        this.event.add(new EventDoorEntity(3, 16, 10, true));

        this.eventFile = EventIDoorRepository.getInstance().getAll();
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
                EventIDoorRepository.getInstance().getByIdItem(this.event.get(0).idItem()).toString());
    }
}
