package edu.qc.seclass;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BuggyClassTestSC3 {
	private BuggyClass bc;

	@Before
	public void setUp() throws Exception {
		bc = new BuggyClass();
	}

	@After
	public void tearDown() throws Exception {
		bc = null;
	}

	@Test
	public void testBuggyMethod3_1sc() {
		assertEquals(5, bc.buggyMethod3(20, 4));
	}

	@Test(expected = ArithmeticException.class)
	public void testBuggyMethod3_2sc() {
		assertEquals(0, bc.buggyMethod3(50, 0));
	}

}
