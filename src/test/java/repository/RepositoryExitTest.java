package repository;

import backend.repository.entity.ExitEntity;
import backend.repository.factory.RepositoryFactory;
import backend.repository.interfaces.IExitEntity;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RepositoryExitTest {

    private List<IExitEntity> exits;
    private List<IExitEntity> exitsFile;

    @Before
    public void create() {
        exits = new ArrayList<>();
        exits.add(new ExitEntity(1, "norte", "floresta"));
        exits.add(new ExitEntity(1, "oeste", "farol"));
        exits.add(new ExitEntity(1, "leste", "praia"));
        exits.add(new ExitEntity(2, "leste", "cais"));
        exits.add(new ExitEntity(3, "oeste", "cais"));
        exits.add(new ExitEntity(4, "norte", "vila"));
        exits.add(new ExitEntity(4, "sul", "cais"));
        exits.add(new ExitEntity(5, "sul", "floresta"));

        exitsFile = RepositoryFactory.getRepositoryExit().getAll();
    }

    @Test
    public void validAll() {
        for (int i = 0; i < exits.size(); i++) {
            assertEquals(exits.get(i).toString(), exitsFile.get(i).toString());
        }
    }
}