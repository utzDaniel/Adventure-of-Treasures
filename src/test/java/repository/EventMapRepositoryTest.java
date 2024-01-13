package repository;

import backend.repository.entity.EventMapEntity;
import backend.repository.interfaces.IEventMapEntity;
import backend.repository.singleton.EventMapRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EventMapRepositoryTest {

    private List<IEventMapEntity> event;
    private List<IEventMapEntity> eventFile;

    @Before
    public void create() {
        this.event = new ArrayList<>();
        this.event.add(new EventMapEntity(1, 16, 8, 10));

        this.eventFile = EventMapRepository.getInstance().getAll();
    }

    @Test
    public void validAll() {
        for (int i = 0; i < this.event.size(); i++) {
            assertEquals(this.event.get(i).toString(), this.eventFile.get(i).toString());
        }
    }
}
