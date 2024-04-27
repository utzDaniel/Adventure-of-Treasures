package service;

import backend.controller.enums.TypeMessage;
import backend.repository.singleton.MapGameRepository;
import backend.service.command.CommandFactory;
import backend.service.enums.Direction;
import backend.service.interfaces.ICoordinate;
import backend.service.model.MapGame;
import backend.service.model.MapGameFactory;
import backend.service.model.Move;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class MoveCommandTest {

    private Map<Integer, MapGame> mapGame;

    @Before
    public void create() {
        this.mapGame = new HashMap<>();
        this.mapGame.put(1, new MapGameFactory().create(MapGameRepository.getInstance().getById(1).orElse(null)));
        this.mapGame.put(5, new MapGameFactory().create(MapGameRepository.getInstance().getById(5).orElse(null)));
        this.mapGame.put(6, new MapGameFactory().create(MapGameRepository.getInstance().getById(6).orElse(null)));
    }

    @Test
    public void valid1() {
        var coordinate = ICoordinate.getInstance(53, 23);
        var move = new Move("", coordinate, this.mapGame.get(1));
        var cmd = CommandFactory.createMoveCommand(move, Direction.SOUTH);
        var msg = cmd.execute();
        assertEquals(TypeMessage.MOVE_BLOCKED, msg);
    }

    @Test
    public void valid2() {
        var coordinate = ICoordinate.getInstance(47, 30);
        var move = new Move("", coordinate, this.mapGame.get(1));
        var cmd = CommandFactory.createMoveCommand(move, Direction.SOUTH);
        var msg = cmd.execute();
        assertEquals(TypeMessage.MOVE, msg);
    }

    @Test
    public void valid3() {
        var coordinate = ICoordinate.getInstance(25, 0);
        var move = new Move("", coordinate, this.mapGame.get(1));
        var cmd = CommandFactory.createMoveCommand(move, Direction.WEST);
        var msg = cmd.execute();
        assertEquals(TypeMessage.MOVE, msg);
    }

    @Test
    public void valid4() {
        var coordinate = ICoordinate.getInstance(25, 0);
        var move = new Move("", coordinate, this.mapGame.get(1));
        var cmd = CommandFactory.createMoveCommand(move, Direction.WEST);
        cmd.execute();
        cmd = CommandFactory.createMoveCommand(move, Direction.EAST);
        var msg = cmd.execute();
        assertEquals(TypeMessage.MOVE, msg);
    }

    @Test
    public void valid5() {
        var coordinate = ICoordinate.getInstance(0, 39);
        var move = new Move("", coordinate, this.mapGame.get(1));
        var cmd = CommandFactory.createMoveCommand(move, Direction.WEST);
        var msg = cmd.execute();
        assertEquals(TypeMessage.MOVE, msg);
    }

    @Test
    public void valid6() {
        var coordinate = ICoordinate.getInstance(0, 39);
        var move = new Move("", coordinate, this.mapGame.get(1));
        var cmd = CommandFactory.createMoveCommand(move, Direction.WEST);
        cmd.execute();
        cmd = CommandFactory.createMoveCommand(move, Direction.EAST);
        var msg = cmd.execute();
        assertEquals(TypeMessage.MOVE, msg);
    }

    @Test
    public void valid7() {
        var coordinate = ICoordinate.getInstance(45, 0);
        var move = new Move("", coordinate, this.mapGame.get(5));
        var cmd = CommandFactory.createMoveCommand(move, Direction.WEST);
        var msg = cmd.execute();
        assertEquals(TypeMessage.MOVE_NEXT_SCENERY_NOT_EXIT, msg);
    }

    @Test
    public void valid8() {
        var coordinate = ICoordinate.getInstance(38, 77);
        var move = new Move("", coordinate, this.mapGame.get(6));
        var cmd = CommandFactory.createMoveCommand(move, Direction.EAST);
        var msg = cmd.execute();
        assertEquals(TypeMessage.MOVE_NEXT_SCENERY_NOT_EXIT, msg);
    }

}
