import javax.swing.*;

public class ItemNotRemove extends Item {

    private MapGame mapGame;

    protected ItemNotRemove(String name, String description,MapGame mapGame, int weight, int positionItemX, int positionItemY, ImageIcon imagemIcon) {
        super(name, description, weight, positionItemX, positionItemY, imagemIcon);
        this.mapGame = mapGame;
    }

    public MapGame getMapGame() {
        return mapGame;
    }

    public void setMapGame(MapGame mapGame) {
        this.mapGame = mapGame;
    }
}
