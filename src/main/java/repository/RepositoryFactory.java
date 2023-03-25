package repository;

import java.util.Objects;

public class RepositoryFactory {
    private static RepositoryItem repositoryItem;
    private static RepositoryMapGame repositoryMapGame;

    public static RepositoryItem getRepositoryItem() {
        if (Objects.isNull(repositoryItem)) {
            repositoryItem = new RepositoryItem();
        }
        return repositoryItem;
    }

    public static RepositoryMapGame getRepositoryMapGame() {
        if (Objects.isNull(repositoryMapGame)) {
            repositoryMapGame = new RepositoryMapGame();
        }
        return repositoryMapGame;
    }

}
