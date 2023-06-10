package backend.repository.factory;

import backend.repository.interfaces.*;

import java.util.Objects;

public final class RepositoryFactory {
    private static RepositoryItem repositoryItem;
    private static RepositoryMapGame repositoryMapGame;
    private static RepositoryDoor repositoryDoor;
    private static RepositoryExit repositoryExit;

    private RepositoryFactory() {
    }

    public static synchronized IRepository<IItemEntity, String> getRepositoryItem() {
        if (Objects.isNull(repositoryItem)) {
            repositoryItem = new RepositoryItem();
        }
        return repositoryItem;
    }

    public static synchronized IRepository<IMapGameEntity, String> getRepositoryMapGame() {
        if (Objects.isNull(repositoryMapGame)) {
            repositoryMapGame = new RepositoryMapGame();
        }
        return repositoryMapGame;
    }

    public static synchronized IRepository<IDoorEntity, Integer> getRepositoryDoor() {
        if (Objects.isNull(repositoryDoor)) {
            repositoryDoor = new RepositoryDoor();
        }
        return repositoryDoor;
    }

    public static synchronized IRepository<IExitEntity, Integer> getRepositoryExit() {
        if (Objects.isNull(repositoryExit)) {
            repositoryExit = new RepositoryExit();
        }
        return repositoryExit;
    }

}
