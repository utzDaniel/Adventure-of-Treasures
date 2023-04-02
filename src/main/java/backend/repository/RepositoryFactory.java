package backend.repository;

import backend.model.builder.item.Item;
import backend.model.builder.map.MapGame;

import java.util.Objects;

public final class RepositoryFactory {
    private static RepositoryItem repositoryItem;
    private static RepositoryMapGame repositoryMapGame;

    public static synchronized Repository<Item> getRepositoryItem() {
        if (Objects.isNull(repositoryItem)) {
            repositoryItem = new RepositoryItem();
        }
        return repositoryItem;
    }

    public static synchronized Repository<MapGame> getRepositoryMapGame() {
        if (Objects.isNull(repositoryMapGame)) {
            repositoryMapGame = new RepositoryMapGame();
        }
        return repositoryMapGame;
    }

}
