package backend.controller.mapper;

import backend.repository.entity.MapGameEntity;
import backend.service.model.builder.MapGame;

import java.util.function.Function;

public final class MapDomainMapper implements Function<MapGameEntity, MapGame> {

    @Override
    public MapGame apply(MapGameEntity mapGame) {
        return null;
    }
}
