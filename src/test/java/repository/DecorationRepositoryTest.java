package repository;

import backend.repository.entity.DecorationEntity;
import backend.repository.interfaces.IDecorationEntity;
import backend.repository.singleton.DecorationRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class DecorationRepositoryTest {

    private List<IDecorationEntity> event;
    private List<IDecorationEntity> eventFile;

    @Before
    public void create() {
        this.event = new ArrayList<>();
        this.event.add(new DecorationEntity(1,  558, 317, "src/main/resources/image/decoration/praia_x.png"));
        this.event.add(new DecorationEntity(2,  259, 160, "src/main/resources/image/decoration/templo_escada.png"));

        this.eventFile = DecorationRepository.getInstance().getAll();
    }

    @Test
    public void validAll() {
        for (int i = 0; i < this.event.size(); i++) {
            assertEquals(this.event.get(i).toString(), this.eventFile.get(i).toString());
        }
    }

    @Test
    public void validById() {
        assertEquals(Optional.of(this.event.get(0)).toString(),
                DecorationRepository.getInstance().getById(this.event.get(0).id()).toString());
    }


}
