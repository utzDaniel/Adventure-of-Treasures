package repository;

import backend.repository.entity.DoorEntity;
import backend.repository.factory.RepositoryFactory;
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
        doors.add(new DoorEntity(1, "barco", 300, 520, true));
        doors.add(new DoorEntity(2, "dentro do farol", 270, 240, true));
        doors.add(new DoorEntity(3, "farol", 720, 260, true));
        doors.add(new DoorEntity(4, "alojamento", 140, 380, true));
        doors.add(new DoorEntity(4, "enfermaria", 720, 310, true));
        doors.add(new DoorEntity(4, "templo", 370, 150, false));
        doors.add(new DoorEntity(5, "vila", 500, 510, true));
        doors.add(new DoorEntity(6, "vila", 380, 530, false));
        doors.add(new DoorEntity(6, "topo do templo", 260, 190, false));
        doors.add(new DoorEntity(6, "porao do templo", 90, 240, false));
        doors.add(new DoorEntity(7, "templo", 250, 180, false));
        doors.add(new DoorEntity(8, "templo", 100, 150, false));
        doors.add(new DoorEntity(9, "vila", 370, 510, true));
        doors.add(new DoorEntity(10, "cais", 70, 320, true));

        doorsFile = RepositoryFactory.getRepositoryDoor().getAll();
    }

    @Test
    public void validAll() {
        for (int i = 0; i < doors.size(); i++) {
            assertEquals(doors.get(i).toString(), doorsFile.get(i).toString());
        }
    }
}
