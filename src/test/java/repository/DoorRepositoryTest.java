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
        this.doors.add(new DoorEntity(1, 1, 52, 30, 12, 32, 7, false));
        this.doors.add(new DoorEntity(2, 2, 24, 26, 3, 26, 72, true));
        this.doors.add(new DoorEntity(3, 3, 26, 73, 2, 24, 27, true));
        this.doors.add(new DoorEntity(4, 6, 37, 14, 7, 51, 50, true));
        this.doors.add(new DoorEntity(5, 6, 30, 72, 11, 51, 37, true));
        this.doors.add(new DoorEntity(6, 6, 14, 38, 8, 53, 38, false));
        this.doors.add(new DoorEntity(7, 7, 52, 50, 6, 38, 14, true));
        this.doors.add(new DoorEntity(8, 8, 54, 38, 6, 15, 37, true));
        this.doors.add(new DoorEntity(9, 8, 19, 25, 9, 18, 25, false));
        this.doors.add(new DoorEntity(10, 8, 24, 10, 10, 15, 10, false));
        this.doors.add(new DoorEntity(11, 9, 18, 26, 8, 19, 26, true));
        this.doors.add(new DoorEntity(12, 10, 15, 9, 8, 24, 9, true));
        this.doors.add(new DoorEntity(13, 11, 52, 37, 6, 31, 72, true));
        this.doors.add(new DoorEntity(14, 12, 33, 7, 1, 52, 30, true));
        this.doors.add(new DoorEntity(15, 12, 28, 71, 1, 52, 30, false));

        this.doorsFile = DoorRepository.getInstance().getAll();
    }

    @Test
    public void validAll() {
        for (int i = 0; i < this.doors.size(); i++) {
            assertEquals(this.doors.get(i).toString(), this.doorsFile.get(i).toString());
        }
    }

    @Test
    public void validByIdMapInside() {
        var list1 = DoorRepository.getInstance().getByIdMapInside(this.doors.get(0).idMapInside());
        var list2 = this.doors.stream().filter(v -> v.idMapInside() == this.doors.get(0).idMapInside()).toList();
        for (IDoorEntity entity : list2) {
            var item = list1.stream().filter(v -> v.id() == entity.id()).findFirst();
            assertEquals(Optional.of(entity).toString(), item.toString());
        }
    }
}
