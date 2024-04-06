package repository;

import backend.repository.entity.EventMapEntity;
import backend.repository.interfaces.IEventMapEntity;
import backend.repository.singleton.EventMapRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class EventMapRepositoryTest {

    private List<IEventMapEntity> event;
    private List<IEventMapEntity> eventFile;

    @Before
    public void create() {
        this.event = new ArrayList<>();
        this.event.add(new EventMapEntity(1, 2, 8, 2));
        this.event.add(new EventMapEntity(2, 5, 4, 1));
        this.event.add(new EventMapEntity(3, 11, 4, -1));
        this.event.add(new EventMapEntity(4, 12, 4, 1));

        this.eventFile = EventMapRepository.getInstance().getAll();
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
                EventMapRepository.getInstance().getByIdItem(this.event.get(0).idItem()).toString());
    }

}
