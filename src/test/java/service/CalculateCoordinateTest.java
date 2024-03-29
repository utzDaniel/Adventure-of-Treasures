package service;

import backend.service.enums.Movement;
import backend.service.interfaces.ICoordinate;
import backend.service.CalculateCoordinate;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculateCoordinateTest {

    @Test
    public void validSquare1() {
        var x = 0;
        var y = 0;
        var coordinate = ICoordinate.getInstance(x, y);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x, y + Movement.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x - Movement.STEP, y + Movement.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x - Movement.STEP, y), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x - Movement.STEP, y - Movement.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x, y - Movement.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x + Movement.STEP, y - Movement.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x + Movement.STEP, y), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x + Movement.STEP, y + Movement.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x + Movement.STEP, y + Movement.STEP + Movement.STEP), coordinate);
    }

    @Test
    public void validSquare2() {
        var x = Movement.STEP * 5;
        var y = Movement.STEP * 5;
        var coordinate = ICoordinate.getInstance(x, y);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x, y + Movement.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x - Movement.STEP, y + Movement.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x - Movement.STEP, y), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x - Movement.STEP, y - Movement.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x, y - Movement.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x + Movement.STEP, y - Movement.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x + Movement.STEP, y), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x + Movement.STEP, y + Movement.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x + Movement.STEP, y + Movement.STEP + Movement.STEP), coordinate);
    }

    @Test
    public void validSquare3() {
        var x = Movement.STEP * 3;
        var y = Movement.STEP * 5;
        var coordinate = ICoordinate.getInstance(x, y);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x, y + Movement.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x - Movement.STEP, y + Movement.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x - Movement.STEP, y), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x - Movement.STEP, y - Movement.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x, y - Movement.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x + Movement.STEP, y - Movement.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x + Movement.STEP, y), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x + Movement.STEP, y + Movement.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x + Movement.STEP, y + Movement.STEP + Movement.STEP), coordinate);
    }

    @Test
    public void validSquare4() {
        var x = Movement.STEP * 5;
        var y = Movement.STEP * 2;
        var coordinate = ICoordinate.getInstance(x, y);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x, y + Movement.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x - Movement.STEP, y + Movement.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x - Movement.STEP, y), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x - Movement.STEP, y - Movement.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x, y - Movement.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x + Movement.STEP, y - Movement.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x + Movement.STEP, y), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x + Movement.STEP, y + Movement.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x + Movement.STEP, y + Movement.STEP + Movement.STEP), coordinate);
    }

    @Test
    public void validSquare5() {
        var x = Movement.STEP * -5;
        var y = Movement.STEP * 2;
        var coordinate = ICoordinate.getInstance(x, y);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x, y + Movement.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x - Movement.STEP, y + Movement.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x - Movement.STEP, y), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x - Movement.STEP, y - Movement.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x, y - Movement.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x + Movement.STEP, y - Movement.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x + Movement.STEP, y), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x + Movement.STEP, y + Movement.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x + Movement.STEP, y + Movement.STEP + Movement.STEP), coordinate);
    }

    @Test
    public void validSquare6() {
        var x = Movement.STEP * 5;
        var y = Movement.STEP * -3;
        var coordinate = ICoordinate.getInstance(x, y);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x, y + Movement.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x - Movement.STEP, y + Movement.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x - Movement.STEP, y), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x - Movement.STEP, y - Movement.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x, y - Movement.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x + Movement.STEP, y - Movement.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x + Movement.STEP, y), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x + Movement.STEP, y + Movement.STEP), coordinate);
        CalculateCoordinate.next(coordinate);
        assertEquals(ICoordinate.getInstance(x + Movement.STEP, y + Movement.STEP + Movement.STEP), coordinate);
    }

}
