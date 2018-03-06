package edu.qc.seclass;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class MyCustomStringTest {

    private MyCustomStringInterface mycustomstring;

    @Before
    public void setUp() {
        mycustomstring = new MyCustomString();
    }

    @After
    public void tearDown() {
        mycustomstring = null;
    }

    @Test
    public void testCountNumbers1() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        assertEquals(7, mycustomstring.countNumbers());
    }

    @Test
    public void testCountNumbers2() {
    	mycustomstring.setString("489498498984");
        assertEquals(12, mycustomstring.countNumbers());
    }

    @Test (expected = NullPointerException.class)
    public void testCountNumbers3() {
    	   tearDown();
           mycustomstring.countNumbers();
    }

    @Test
    public void testCountNumbers4() {
    	mycustomstring.setString(" my number is 845 729 9010");
        assertEquals(10, mycustomstring.countNumbers());
    }
    /**
     *  test when string has no numbers
     */
    @Test
    public void testCountNumbers5() {
    	mycustomstring.setString("No Numbers");
        assertEquals(0, mycustomstring.countNumbers());
    }
    
    @Test
    public void testCountNumbers6() {
    	mycustomstring.setString("");
        assertEquals(0, mycustomstring.countNumbers());
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd1() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        assertEquals("d33p md1  i51,it", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(3, false));
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd2() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        assertEquals("'bt t0 6snh r6rh", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(3, true));
    }

    @Test 
    public void testGetEveryNthCharacterFromBeginningOrEnd3() {
    	mycustomstring.setString("FIVE.");
    	assertEquals(".", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(5, false));
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd4() {
    	mycustomstring.setString("FIVE.");
    	assertEquals("F", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(5, true));
    }

    @Test  
    public void testGetEveryNthCharacterFromBeginningOrEnd5() {
     	mycustomstring.setString("Ri8T");
     	assertEquals("8", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(3, false));
        
    }

    @Test (expected= NullPointerException.class)
    public void testGetEveryNthCharacterFromBeginningOrEnd6() {
     	mycustomstring.setString(null);
    	mycustomstring.getEveryNthCharacterFromBeginningOrEnd(1, true);
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd7() {
    	mycustomstring.setString("FACEBOOK");
    	assertEquals("FACEBOOK", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(1, true));
       
    }
    
    /**
     * test if char '\s' 
     * backword reading
     */
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd8() {
    	mycustomstring.setString("abc abc abc");
    	assertEquals("  " , mycustomstring.getEveryNthCharacterFromBeginningOrEnd(4, true));
    
    }
    
    /**
     * test if char '\s'
     */
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd9() {
    	mycustomstring.setString("abc abc abc");
    	assertEquals("  " , mycustomstring.getEveryNthCharacterFromBeginningOrEnd(4, false));
    }
    /**
     * test if position is 1
     */
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd10() {
    	mycustomstring.setString("Not yet implemented");
    	assertEquals("Not yet implemented" , mycustomstring.getEveryNthCharacterFromBeginningOrEnd(1, false));
    }
    /**
     * test when position is string length
     */
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd11() {
    	mycustomstring.setString("last char a");
    	assertEquals("a" , mycustomstring.getEveryNthCharacterFromBeginningOrEnd(11, false));
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd12() {
    	  mycustomstring.setString("a");
          assertEquals("" , mycustomstring.getEveryNthCharacterFromBeginningOrEnd(2, true));
    }

    @Test (expected=NullPointerException.class)
    public void testGetEveryNthCharacterFromBeginningOrEnd13() {
    	tearDown();
    	mycustomstring.getEveryNthCharacterFromBeginningOrEnd(3, true);
    }

    @Test (expected = IllegalArgumentException.class )
    public void testGetEveryNthCharacterFromBeginningOrEnd14() {
    	 mycustomstring.setString("IllegalArgumentExceptiont test ");
         mycustomstring.getEveryNthCharacterFromBeginningOrEnd(0 , false);
    }

    @Test
    public void testConvertDigitsToNamesInSubstring1() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        mycustomstring.convertDigitsToNamesInSubstring(17, 23);
        assertEquals("I'd b3tt3r put sZerome dOneSix1ts in this 5tr1n6, right?", mycustomstring.getString());
    }

    @Test
    public void testConvertDigitsToNamesInSubstring2() {
    	mycustomstring.setString("00101100");
    	mycustomstring.convertDigitsToNamesInSubstring(1, 8);
    	assertEquals("ZeroZeroOneZeroOneOneZeroZero", mycustomstring.getString()); 
    	}
    

    @Test (expected = MyIndexOutOfBoundsException.class)
    public void testConvertDigitsToNamesInSubstring3() {
    	mycustomstring.setString("Small String");
        mycustomstring.convertDigitsToNamesInSubstring(17, 23); 
    }

    @Test (expected = IllegalArgumentException.class)
    public void testConvertDigitsToNamesInSubstring4() {
    	mycustomstring.setString("Start Pos > End Pos");
        mycustomstring.convertDigitsToNamesInSubstring(23, 17);
    }

    @Test (expected = NullPointerException.class)
    public void testConvertDigitsToNamesInSubstring5() {
    	mycustomstring.setString(null);
    	mycustomstring.convertDigitsToNamesInSubstring(1, 8);
    }

    @Test
    public void testConvertDigitsToNamesInSubstring6() {
    	mycustomstring.setString("Ar9ument5 fr0m fir57 t10 las7 posi7ion");
        mycustomstring.convertDigitsToNamesInSubstring(1, 38);
        assertEquals("ArNineumentFive frZerom firFiveSeven tOneZero lasSeven posiSevenion", mycustomstring.getString());
    }

    @Test
    public void testConvertDigitsToNamesInSubstring7() {
    	mycustomstring.setString("45right?");
        mycustomstring.convertDigitsToNamesInSubstring(3, 5);
        assertEquals("45right?" , mycustomstring.getString());
    }

    @Test
    public void testConvertDigitsToNamesInSubstring8() {
    	mycustomstring.setString("right34?");
        mycustomstring.convertDigitsToNamesInSubstring(1, 5);
        assertEquals("right34?" , mycustomstring.getString());
    }

}
