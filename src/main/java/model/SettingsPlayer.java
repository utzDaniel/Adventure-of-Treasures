package model;

import repository.CreateImagePlayer;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class SettingsPlayer {
    private final CreateImagePlayer imagePlayer;
    private final List<MovePlayer> movePlayer;

    public SettingsPlayer(){
        movePlayer = new ArrayList<>();
        imagePlayer = new CreateImagePlayer();
    }

    public List<MovePlayer> createMovePlayer() {
        int STEP = 10;
        movePlayer.add(new MovePlayer(Direction.LESTE.getLabel(), true, STEP,imagePlayer.selectImage(Direction.LESTE.getLabel())));
        movePlayer.add(new MovePlayer(Direction.OESTE.getLabel(),true,-STEP,imagePlayer.selectImage(Direction.OESTE.getLabel())));
        movePlayer.add(new MovePlayer(Direction.SUL.getLabel(), false,STEP,imagePlayer.selectImage(Direction.SUL.getLabel())));
        movePlayer.add(new MovePlayer(Direction.NORTE.getLabel(),false,-STEP,imagePlayer.selectImage(Direction.NORTE.getLabel())));
        return movePlayer;
    }

    public int positionInitialX (){
        return 300;
    }

    public int positionInitialY (){
        return 470;
    }

    public ImageIcon ImageInitial (){ return imagePlayer.selectImage(Direction.SUL.getLabel()); }

}
