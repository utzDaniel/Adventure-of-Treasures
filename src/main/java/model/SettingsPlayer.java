package model;

import repository.CreateImagePlayer;

import javax.swing.*;
import java.util.ArrayList;

public class SettingsPlayer {

    private final CreateImagePlayer imagePlayer;
    private final ArrayList<MovePlayer> movePlayer;

    public SettingsPlayer(){
        movePlayer = new ArrayList<>();
        imagePlayer = new CreateImagePlayer();
    }

    public ArrayList<MovePlayer> createMovePlayer() {
        int STEP = 10;
        movePlayer.add(new MovePlayer("leste",true, STEP,imagePlayer.selectImage("leste")));
        movePlayer.add(new MovePlayer("oeste",true,-STEP,imagePlayer.selectImage("oeste")));
        movePlayer.add(new MovePlayer("sul", false,STEP,imagePlayer.selectImage("sul")));
        movePlayer.add(new MovePlayer("norte",false,-STEP,imagePlayer.selectImage("norte")));
        return movePlayer;
    }

    public int positionInitialX (){
        return 300;
    }

    public int positionInitialY (){
        return 470;
    }

    public ImageIcon ImageInitial (){ return imagePlayer.selectImage("sul"); }

}
