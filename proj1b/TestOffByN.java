import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    static CharacterComparator Offbyn = new OffByN(2);

    @Test
    public void testequalChars() {
        assertTrue(Offbyn.equalChars('a', 'c'));
        assertFalse(Offbyn.equalChars('a', 'd'));
        assertTrue(Offbyn.equalChars('z', 'x'));
    }
}
