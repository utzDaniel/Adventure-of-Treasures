package repository;

import backend.repository.entity.DoorEntity;
import backend.repository.interfaces.IDoorEntity;
import backend.repository.singleton.DoorRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class DoorRepositoryTest {

    private List<IDoorEntity> doors;
    private List<IDoorEntity> doorsFile;

    @Before
    public void create() {
        this.doors = new ArrayList<>();
        this.doors.add(new DoorEntity(1, 1, 12, 52, 30, false));
        this.doors.add(new DoorEntity(2, 2, 3, 24, 26, true));
        this.doors.add(new DoorEntity(3, 3, 2, 26, 73, true));
        this.doors.add(new DoorEntity(4, 6, 7, 37, 14, true));
        this.doors.add(new DoorEntity(5, 6, 11, 30, 72, true));
        this.doors.add(new DoorEntity(6, 6, 8, 14, 38, false));
        this.doors.add(new DoorEntity(7, 7, 6, 52, 50, true));
        this.doors.add(new DoorEntity(8, 8, 6, 54, 38, true));
        this.doors.add(new DoorEntity(9, 8, 9, 19, 25, false));
        this.doors.add(new DoorEntity(10, 8, 10, 24, 10, false));
        this.doors.add(new DoorEntity(11, 9, 8, 18, 26, true));
        this.doors.add(new DoorEntity(12, 10, 8, 15, 9, true));
        this.doors.add(new DoorEntity(13, 11, 6, 52, 37, true));
        this.doors.add(new DoorEntity(14, 12, 1, 33, 7, true));
        this.doors.add(new DoorEntity(15, 12, 1, 28, 71, false));

        this.doorsFile = DoorRepository.getInstance().getAll();
    }

    @Test
    public void validAll() {
        for (int i = 0; i < this.doors.size(); i++) {
            assertEquals(this.doors.get(i).toString(), this.doorsFile.get(i).toString());
        }
    }

    @Test
    public void validByIdMapOri() {
        var list1 = DoorRepository.getInstance().getByIdMapOri(this.doors.get(0).idMapOri());
        var list2 = this.doors.stream().filter(v -> v.idMapOri() == this.doors.get(0).idMapOri()).toList();
        for (IDoorEntity entity : list2) {
            var item = list1.stream().filter(v -> v.id() == entity.id()).findFirst();
            assertEquals(Optional.of(entity).toString(), item.toString());
        }
    }
}
