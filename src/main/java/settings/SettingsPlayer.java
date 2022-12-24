package settings;

import model.enums.Direction;
import repository.CreateImagePlayer;

import javax.swing.*;

public class SettingsPlayer {

    public int positionInitialX (){
        return 300;
    }

    public int positionInitialY (){
        return 470;
    }
    public int labelWidth (){
        return 32;
    }
    public int labelHeight (){
        return 47;
    }

    public ImageIcon ImageInitial (){ return new CreateImagePlayer().selectImage(Direction.SUL.getLabel()); }

}
