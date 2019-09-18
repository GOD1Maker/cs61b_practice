import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testequalChars(){
        OffByOne obo = new OffByOne();
        assertTrue(obo.equalChars('a','b'));
        assertTrue(obo.equalChars('c','d'));
        assertTrue(obo.equalChars('&','%'));
        assertFalse(obo.equalChars('e','i'));
        assertFalse(obo.equalChars('A','a'));
    }
}
