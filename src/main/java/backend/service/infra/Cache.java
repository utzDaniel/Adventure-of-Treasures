package backend.service.infra;

import backend.repository.factory.RepositoryFactory;
import backend.service.model.builder.MapGame;
import backend.service.model.builder.MapGameFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class Cache {

    // TODO usar o padrao Flyweight
    private static final Map<Integer, MapGame> mapGame = new HashMap<>();
    private static final MapGameFactory factory = new MapGameFactory();

    private Cache(){}

    public static void addMapGame(MapGame mapGame){
        Cache.mapGame.put(mapGame.getId(), mapGame);
    }

    public static MapGame getMapGame(int idMap){
        var mapGame1 = Cache.mapGame.get(idMap);
        if(Objects.isNull(mapGame1)) {
            mapGame1 = create(idMap);
            addMapGame(mapGame1);
        }
        return mapGame1;
    }

    private static MapGame create(int idMap){
        var entity = RepositoryFactory.getRepositoryMapGame().get(idMap);
        return factory.create(entity);
    }

}
