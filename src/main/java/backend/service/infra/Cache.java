package backend.service.infra;

import backend.repository.interfaces.IMapGameEntity;
import backend.repository.singleton.MapGameRepository;
import backend.service.model.MapGame;
import backend.service.model.MapGameFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public final class Cache {

    // TODO usar o padrao Flyweight
    private static final Map<Integer, MapGame> mapGame = new HashMap<>();

    private Cache(){}

    public static void addMapGame(MapGame mapGame){
        Cache.mapGame.put(mapGame.getId(), mapGame);
    }

    public static Optional<MapGame> getMapGame(int idMap){
        var mapGame1 = Optional.ofNullable(Cache.mapGame.get(idMap));
        if(mapGame1.isEmpty()) {
            var entity = getMapGameEntity(idMap);
            if(entity.isEmpty()) return Optional.empty();
            mapGame1 = create(entity.get());
            mapGame1.ifPresent(Cache::addMapGame);
        }
        return mapGame1;
    }

    private static Optional<IMapGameEntity> getMapGameEntity(int idMap){
        return MapGameRepository.getInstance().getById(idMap);
    }

    private static Optional<MapGame> create(IMapGameEntity entity){
        return Optional.of(MapGameFactory.create(entity));
    }

}
