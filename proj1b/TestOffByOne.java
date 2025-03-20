import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testequalChars(){
        assertTrue(offByOne.equalChars('l', 'k'));
        assertTrue(offByOne.equalChars('x', 'y'));
        assertFalse(offByOne.equalChars('x', 'x'));
        assertTrue(offByOne.equalChars('&', '%'));
        assertFalse(offByOne.equalChars('A', 'b'));
    }
}
