package repository;

import backend.repository.entity.EventItemEntity;
import backend.repository.interfaces.IEventItemEntity;
import backend.repository.singleton.EventIDoorRepository;
import backend.repository.singleton.EventItemRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class EventItemRepositoryTest {

    private List<IEventItemEntity> event;
    private List<IEventItemEntity> eventFile;

    @Before
    public void create() {
        this.event = new ArrayList<>();
        this.event.add(new EventItemEntity(1, 5, 11));
        this.event.add(new EventItemEntity(2, 12, 11));

        this.eventFile = EventItemRepository.getInstance().getAll();
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
                EventItemRepository.getInstance().getByIdItem(this.event.get(0).idItem()).toString());
    }
}
