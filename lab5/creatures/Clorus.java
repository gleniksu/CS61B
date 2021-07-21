package creatures;

import huglife.*;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;


public class Clorus extends Creature {
    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;

    /**
     * creates a clorus with energy equal to E
     */
    public Clorus(double e) {
        super("clorus");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    /**
     * creates a clorus with energy equal to 1.
     */
    public Clorus() {
        this(1);
    }

    public Color color() {
        r = 34;
        g = 0;
        b = 231;
        return color(r, g, b);
    }
    public void attack(Creature c) {
        energy = energy + c.energy();
    }

    @Override
    public Clorus replicate() {
        return new Clorus(energy);
    }

    public void move() {
        energy = energy - 0.03;
    }

    public void stay() {
        energy = energy - 0.01;
    }

    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        Deque<Direction> emptyNeighbor= new ArrayDeque<>();
        Deque<Direction> plipNeighbor = new ArrayDeque<>();
        for(Direction eachKey: neighbors.keySet()) {
            Occupant o = neighbors.get(eachKey);
            if (o.name() == "empty") {
                emptyNeighbor.addLast(eachKey);
            } else if(o.name() == "plip") {
                plipNeighbor.addLast(eachKey);
            }
        }
        if (emptyNeighbor.size() == 0) {
            return new Action(Action.ActionType.STAY);
        }
        else if(plipNeighbor.size() != 0) {
            Direction d = HugLifeUtils.randomEntry(plipNeighbor);
            return new Action(Action.ActionType.ATTACK, d);
        }
        else if(energy >= 1) {
            Direction d = HugLifeUtils.randomEntry(emptyNeighbor);
            return new Action(Action.ActionType.REPLICATE, d);
        }
        else {
            Direction d = HugLifeUtils.randomEntry(emptyNeighbor);
            return new Action(Action.ActionType.MOVE, d);
        }
    }


}
