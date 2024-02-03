package service;

import backend.service.util.CalculateCoordinate;
import backend.service.enums.Direction;
import backend.service.enums.Move;
import backend.service.interfaces.ICoordinate;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculateCoordinateTest {

    @Test
    public void validOeste() {
        var x = 2;
        var y = 2;
        var coordinate = ICoordinate.getInstance(x, y);
        CalculateCoordinate.next(coordinate, Direction.OESTE);
        assertEquals(ICoordinate.getInstance(x, y - Move.STEP), coordinate);
    }

    @Test
    public void validNorte() {
        var x = 2;
        var y = 2;
        var coordinate = ICoordinate.getInstance(x, y);
        CalculateCoordinate.next(coordinate, Direction.NORTE);
        assertEquals(ICoordinate.getInstance(x - Move.STEP, y), coordinate);
    }

    @Test
    public void validLeste() {
        var x = 2;
        var y = 2;
        var coordinate = ICoordinate.getInstance(x, y);
        CalculateCoordinate.next(coordinate, Direction.LESTE);
        assertEquals(ICoordinate.getInstance(x, y + Move.STEP), coordinate);
    }

    @Test
    public void validSul() {
        var x = 2;
        var y = 2;
        var coordinate = ICoordinate.getInstance(x, y);
        CalculateCoordinate.next(coordinate, Direction.SUL);
        assertEquals(ICoordinate.getInstance(x + Move.STEP, y), coordinate);
    }

    @Test
    public void validSquare1() {
        var x = 0;
        var y = 0;
        var coordinate = ICoordinate.getInstance(x, y);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x, y + Move.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x - Move.STEP, y + Move.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x - Move.STEP, y), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x - Move.STEP, y - Move.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x, y - Move.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x + Move.STEP, y - Move.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x + Move.STEP, y), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x + Move.STEP, y + Move.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x + Move.STEP, y + Move.STEP + Move.STEP), coordinate);
    }

    @Test
    public void validSquare2() {
        var x = Move.STEP * 5;
        var y = Move.STEP * 5;
        var coordinate = ICoordinate.getInstance(x, y);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x, y + Move.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x - Move.STEP, y + Move.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x - Move.STEP, y), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x - Move.STEP, y - Move.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x, y - Move.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x + Move.STEP, y - Move.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x + Move.STEP, y), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x + Move.STEP, y + Move.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x + Move.STEP, y + Move.STEP + Move.STEP), coordinate);
    }

    @Test
    public void validSquare3() {
        var x = Move.STEP * 3;
        var y = Move.STEP * 5;
        var coordinate = ICoordinate.getInstance(x, y);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x, y + Move.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x - Move.STEP, y + Move.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x - Move.STEP, y), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x - Move.STEP, y - Move.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x, y - Move.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x + Move.STEP, y - Move.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x + Move.STEP, y), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x + Move.STEP, y + Move.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x + Move.STEP, y + Move.STEP + Move.STEP), coordinate);
    }

    @Test
    public void validSquare4() {
        var x = Move.STEP * 5;
        var y = Move.STEP * 2;
        var coordinate = ICoordinate.getInstance(x, y);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x, y + Move.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x - Move.STEP, y + Move.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x - Move.STEP, y), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x - Move.STEP, y - Move.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x, y - Move.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x + Move.STEP, y - Move.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x + Move.STEP, y), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x + Move.STEP, y + Move.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x + Move.STEP, y + Move.STEP + Move.STEP), coordinate);
    }

    @Test
    public void validSquare5() {
        var x = Move.STEP * -5;
        var y = Move.STEP * 2;
        var coordinate = ICoordinate.getInstance(x, y);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x, y + Move.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x - Move.STEP, y + Move.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x - Move.STEP, y), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x - Move.STEP, y - Move.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x, y - Move.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x + Move.STEP, y - Move.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x + Move.STEP, y), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x + Move.STEP, y + Move.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x + Move.STEP, y + Move.STEP + Move.STEP), coordinate);
    }

    @Test
    public void validSquare6() {
        var x = Move.STEP * 5;
        var y = Move.STEP * -3;
        var coordinate = ICoordinate.getInstance(x, y);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x, y + Move.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x - Move.STEP, y + Move.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x - Move.STEP, y), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x - Move.STEP, y - Move.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x, y - Move.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x + Move.STEP, y - Move.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x + Move.STEP, y), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x + Move.STEP, y + Move.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x + Move.STEP, y + Move.STEP + Move.STEP), coordinate);
    }

}
