package repository;

import backend.repository.entity.EventEntity;
import backend.repository.interfaces.IEventEntity;
import backend.repository.singleton.EventRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EventRepositoryTest {

    private List<IEventEntity> event;
    private List<IEventEntity> eventFile;

    @Before
    public void create() {
        this.event = new ArrayList<>();
        this.event.add(new EventEntity(1, 16, 8, 10));

        this.eventFile = EventRepository.getInstance().getAll();
    }

    @Test
    public void validAll() {
        for (int i = 0; i < this.event.size(); i++) {
            assertEquals(this.event.get(i).toString(), this.eventFile.get(i).toString());
        }
    }
}
