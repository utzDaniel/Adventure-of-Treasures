package repository;

import backend.repository.entity.DoorEntity;
import backend.repository.interfaces.IDoorEntity;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RepositoryDoorTest {

    private List<IDoorEntity> doors;
    private List<IDoorEntity> doorsFile;

    @Before
    public void create() {
        doors = new ArrayList<>();
        doors.add(new DoorEntity(1,1, "barco", 300, 520, true));
        doors.add(new DoorEntity(2,2, "dentro do farol", 270, 240, true));
        doors.add(new DoorEntity(3,3, "farol", 720, 260, true));
        doors.add(new DoorEntity(4,6, "alojamento", 140, 380, true));
        doors.add(new DoorEntity(5,6, "enfermaria", 720, 310, true));
        doors.add(new DoorEntity(6,6, "templo", 370, 150, false));
        doors.add(new DoorEntity(7,7, "vila", 500, 510, true));
        doors.add(new DoorEntity(8,8, "vila", 380, 530, false));
        doors.add(new DoorEntity(9,8, "topo do templo", 260, 190, false));
        doors.add(new DoorEntity(10,8, "porao do templo", 90, 240, false));
        doors.add(new DoorEntity(11,9, "templo", 250, 180, false));
        doors.add(new DoorEntity(12,10, "templo", 100, 150, false));
        doors.add(new DoorEntity(13,11, "vila", 370, 510, true));
        doors.add(new DoorEntity(14,12, "cais", 70, 320, true));

        doorsFile = RepositoryFactory.getRepositoryDoor().getAll();
    }

    @Test
    public void validAll() {
        for (int i = 0; i < doors.size(); i++) {
            assertEquals(doors.get(i).toString(), doorsFile.get(i).toString());
        }
    }
}
