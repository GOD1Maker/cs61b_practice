import org.junit.Test;
import static org.junit.Assert.*;

public class FlikTest {

    @Test
    public void testFlik(){
        assertTrue(Flik.isSameNumber(1,1));
        assertTrue(Flik.isSameNumber(2,2));
        assertFalse(Flik.isSameNumber(1,2));
        assertEquals(true,Flik.isSameNumber(128,128));
    }
}
