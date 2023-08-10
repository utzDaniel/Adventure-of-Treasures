package backend.service.infra;

import backend.repository.singleton.MapGameRepository;
import backend.service.model.MapGame;
import backend.service.model.MapGameFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class Cache {

    // TODO usar o padrao Flyweight
    private static final Map<Integer, MapGame> mapGame = new HashMap<>();

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
        var entity = MapGameRepository.getInstance().getById(idMap);
        return MapGameFactory.create(entity);
    }

}
