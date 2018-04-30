package edu.qc.seclass;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BuggyClassTestSC2 {
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
	public void testBuggyMethod2_sc1() {
		assertEquals(-4, bc.buggyMethod2(-8, 2));
	}

	@Test
	public void testbuggyMethod2_sc2() {
		assertEquals(-2, bc.buggyMethod2(4, -2));
	}

	@Test
	public void testBuggyMethod2_sc3() {
		assertEquals(2, bc.buggyMethod2(-4, -2));
	}
}
