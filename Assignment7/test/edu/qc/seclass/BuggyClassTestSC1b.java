package edu.qc.seclass;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BuggyClassTestSC1b {
	private BuggyClass bc;

	@Before
	public void setUp() {
		bc = new BuggyClass();
	}

	@After
	public void tearDown() {
		bc = null;
	}

	@Test(expected = ArithmeticException.class)
	public void testBuggyMethod1_sc1b() {
		assertEquals(0, bc.buggyMethod1(10, 0));
	}
}
