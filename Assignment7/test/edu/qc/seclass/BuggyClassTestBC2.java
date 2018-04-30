package edu.qc.seclass;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BuggyClassTestBC2 {
	private BuggyClass bc;

	@Before
	public void setUp() {
		bc = new BuggyClass();
	}

	@After
	public void tearDown() {
		bc = null;
	}

	@Test
	public void testBuggyMethod2_bc1() {
		assertEquals(-3, bc.buggyMethod2(9, -3));
	}

	@Test(expected = ArithmeticException.class)
	public void testBuggyMethod2_bc2() {
		assertEquals(0, bc.buggyMethod2(10, 0));
	}

}
