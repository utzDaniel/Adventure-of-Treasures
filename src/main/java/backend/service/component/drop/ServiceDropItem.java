package backend.service.component.drop;

import backend.controller.enums.TypeMessage;
import backend.service.component.RemoveItem;
import backend.service.model.Inventory;
import backend.service.model.Player;

public class ServiceDropItem {

    private final Player player = Player.getInstance();
    private final Inventory inventory;

    public ServiceDropItem(Inventory inventory) {
        this.inventory = inventory;
    }

    public TypeMessage run(String name) {

        var item = this.inventory.getItemVisible().stream()
                .filter(item1 -> item1.getName().equals(name))
                .findFirst().get();

        var typeMessage = new RemoveItem(this.inventory, item).run();
        if (!typeMessage.isSucess()) return typeMessage;

        var mapGame = this.player.getCurrentMap();
        var coordinate = this.player.getLocation();

        typeMessage = new AddItemMapGame(mapGame, item, coordinate).run();
        if (typeMessage.isSucess()) mapGame.addItem(item);

        return typeMessage;
    }

}
