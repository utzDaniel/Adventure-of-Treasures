package backend.repository.factory;

import backend.repository.interfaces.*;

import java.util.Objects;

public final class RepositoryFactory {
    private static RepositoryItem repositoryItem;
    private static RepositoryMapGame repositoryMapGame;
    private static RepositoryDoor repositoryDoor;
    private static RepositoryExit repositoryExit;
    private static RepositoryCombinable repositoryCombinable;
    private static RepositoryEquipable repositoryEquipable;
    private static RepositoryUsable repositoryUsable;

    private RepositoryFactory() {
    }

    public static synchronized IRepository<IItemEntity, Integer> getRepositoryItem() {
        if (Objects.isNull(repositoryItem)) {
            repositoryItem = new RepositoryItem();
        }
        return repositoryItem;
    }

    public static synchronized IRepository<IMapGameEntity, Integer> getRepositoryMapGame() {
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
    public static synchronized IRepository<ICombinableEntity, Integer> getRepositoryCombinable() {
        if (Objects.isNull(repositoryCombinable)) {
            repositoryCombinable = new RepositoryCombinable();
        }
        return repositoryCombinable;
    }
    public static synchronized IRepository<IEquipableEntity, Integer> getRepositoryEquipable() {
        if (Objects.isNull(repositoryEquipable)) {
            repositoryEquipable = new RepositoryEquipable();
        }
        return repositoryEquipable;
    }
    public static synchronized IRepository<IUsableEntity, Integer> getRepositoryUsable() {
        if (Objects.isNull(repositoryUsable)) {
            repositoryUsable = new RepositoryUsable();
        }
        return repositoryUsable;
    }

}
