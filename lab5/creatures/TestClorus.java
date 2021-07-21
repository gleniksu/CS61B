package creatures;

import huglife.*;
import org.junit.Test;

import javax.swing.plaf.basic.BasicScrollPaneUI;
import java.awt.*;
import java.util.HashMap;

import static org.junit.Assert.*;

public class TestClorus {
    @Test
    public void testBasic() {
        Clorus c = new Clorus(2);
        assertEquals(c.name(), "clorus");
        c.move();
        assertEquals(1.97,c.energy(), 0.01);
        c.move();
        assertEquals(1.94, c.energy(), 0.01);
        c.stay();
        assertEquals(1.93, c.energy(), 0.01);
        c.stay();
        assertEquals(1.92, c.energy(), 0.01);
        assertEquals(new Color(34, 0, 231), c.color());
    }

    @Test
    public void testAttack() {
        Clorus c = new Clorus(1);
        Plip p = new Plip(1);
        c.attack(p);
        assertEquals(2,c.energy(), 0.1);
    }

    @Test
    public void testReplicate() {
        double originEnergy = 1;
        Clorus c = new Clorus(originEnergy);
        Clorus cc = c.replicate();
        assertTrue(c != cc);
        assertEquals(c.energy(), cc.energy(), 0.1);
    }

    @Test
    public void testChoose() {

        //No empty space spaces, the Clorus will STAY.
        Clorus c = new Clorus(1.2);
        HashMap<Direction, Occupant> surroundedImpass = new HashMap<Direction, Occupant>();
        surroundedImpass.put(Direction.TOP, new Impassible());
        surroundedImpass.put(Direction.BOTTOM, new Impassible());
        surroundedImpass.put(Direction.LEFT, new Impassible());
        surroundedImpass.put(Direction.RIGHT, new Impassible());

        Action actual = c.chooseAction(surroundedImpass);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);

        c = new Clorus(1.2);
        HashMap<Direction, Occupant> surroundedPlip= new HashMap<Direction, Occupant>();
        surroundedPlip.put(Direction.TOP, new Plip());
        surroundedPlip.put(Direction.LEFT, new Plip());
        surroundedPlip.put(Direction.RIGHT, new Plip());
        surroundedPlip.put(Direction.BOTTOM, new Plip());

        actual = c.chooseAction(surroundedPlip);
        expected = new Action(Action.ActionType.STAY);



        //if any Plips are seen, the CLorus will Attack one of them randomly.

        c = new Clorus(1.2);
        HashMap<Direction, Occupant> onePlip = new HashMap<Direction, Occupant>();
        onePlip.put(Direction.TOP, new Plip());
        onePlip.put(Direction.LEFT, new Empty());
        onePlip.put(Direction.RIGHT, new Empty());
        onePlip.put(Direction.BOTTOM, new Empty());

        actual = c.chooseAction(onePlip);
        expected = new Action(Action.ActionType.ATTACK, Direction.TOP);

        //Energy >= 1; replicate towards an empty space.
        c = new Clorus(1.2);
        HashMap<Direction, Occupant> oneEmpty = new HashMap<Direction, Occupant>();
        oneEmpty.put(Direction.TOP, new Empty());
        oneEmpty.put(Direction.BOTTOM, new Impassible());
        oneEmpty.put(Direction.LEFT, new Impassible());
        oneEmpty.put(Direction.RIGHT, new Impassible());

        actual = c.chooseAction(oneEmpty);
        expected = new Action(Action.ActionType.REPLICATE, Direction.TOP);

        assertEquals(expected, actual);

        //Energy < 1; move towards an empty space;
        c = new Clorus(0.9);

        actual = c.chooseAction(oneEmpty);
        expected = new Action(Action.ActionType.MOVE, Direction.TOP);

    }

}
