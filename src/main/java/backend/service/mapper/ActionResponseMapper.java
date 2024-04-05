package backend.service.mapper;

import backend.controller.interfaces.IActionResponse;
import backend.controller.interfaces.IComponentInfo;
import backend.service.dto.ComponentInfoDTO;
import backend.service.dto.response.ActionResponse;
import backend.service.model.Item;
import backend.service.model.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.function.Function;

public final class ActionResponseMapper implements Function<Player, IActionResponse> {

    @Override
    public IActionResponse apply(Player player) {

        var components = new ArrayList<IComponentInfo>();
        components.add(createComponentInfoPlayer(player));
        components.add(createComponentInfoMap(player));

        var items = player.getCurrentMap().getItems();
        components.addAll(createListComponentInfoItem(items));

        var songMap = player.getCurrentMap().getSong();

        return new ActionResponse(components, songMap);
    }

    private static IComponentInfo createComponentInfoMap(Player player) {
        var x = 0;
        var y = 0;
        var point = createPoint(x, y);
        var image = player.getCurrentMap().getImage();
        return new ComponentInfoDTO("MAP", image, point);
    }

    private static IComponentInfo createComponentInfoPlayer(Player player) {
        var x = player.getCoordinate().x();
        var y = player.getCoordinate().y();
        var point = createPoint(x, y);
        var image = player.getImage();
        return new ComponentInfoDTO("PLAYER", image, point);
    }

    private static java.util.List<IComponentInfo> createListComponentInfoItem(java.util.List<Item> items) {
        return items.stream()
                .map(ActionResponseMapper::createComponentInfoItem)
                .toList();
    }

    private static IComponentInfo createComponentInfoItem(Item item) {
        var x = item.getCoordinate().x() - 1;
        var y = item.getCoordinate().y();
        var point = createPoint(x, y);
        var image = item.getImage();
        return new ComponentInfoDTO("ITEM", image, point);
    }

    private static Point createPoint(int x, int y) {
        int step = 10;
        var pX = y * step;
        var pY = x * step;
        return new Point(pX, pY);
    }

}
