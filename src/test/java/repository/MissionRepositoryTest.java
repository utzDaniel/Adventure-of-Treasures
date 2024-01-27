package repository;

import backend.repository.entity.MissionEntity;
import backend.repository.interfaces.IMissionEntity;
import backend.repository.singleton.MissionRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class MissionRepositoryTest {

    private List<IMissionEntity> mission;
    private List<IMissionEntity> missionFile;

    @Before
    public void create() {
        this.mission = new ArrayList<>();
        this.mission.add(new MissionEntity(1, 8));
        this.mission.add(new MissionEntity(2, 15));

        this.missionFile = MissionRepository.getInstance().getAll();
    }

    @Test
    public void validAll() {
        for (int i = 0; i < this.mission.size(); i++) {
            assertEquals(this.mission.get(i).toString(), this.missionFile.get(i).toString());
        }
    }

    @Test
    public void validByIdItem() {
        assertEquals(Optional.of(this.mission.get(0)).toString(),
                MissionRepository.getInstance().getByIdItem(this.mission.get(0).idItem()).toString());
    }
}
