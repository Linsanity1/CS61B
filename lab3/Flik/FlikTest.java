import static org.junit.Assert.*;

import org.junit.Test;

public class FlikTest {

    @Test
    public void isSameNumberTest() {
        assertTrue("Expect 1 == 1", Flik.isSameNumber(1, 1));
        assertFalse(" Expect 1 != 0", Flik.isSameNumber(1, 0));
        //assertTrue("Expect 128 == 128", Flik.isSameNumber(128, 128));
        Integer a = 128;
        Integer b = 128;
        System.out.print(a == b);
    }



}
