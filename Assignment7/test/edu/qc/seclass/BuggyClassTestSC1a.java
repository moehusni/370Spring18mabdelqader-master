package edu.qc.seclass;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BuggyClassTestSC1a {
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
	public void testBuggyMethod1_sc1a() {
		assertEquals(2, bc.buggyMethod1(4, 2));
	}

	@Test
	public void testBuggyMethod1_sc2a() {
		assertEquals(2, bc.buggyMethod1(-4, -2));
	}

	@Test
	public void testBuggyMethod1_sc3a() {
		assertEquals(-4, bc.buggyMethod1(-8, 2));
	}

	@Test
	public void testBuggyMethod1_sc4a() {
		assertEquals(-4, bc.buggyMethod1(8, -2));
	}

	@Test
	public void testBuggyMethod1_sc5a() {
		assertEquals(0, bc.buggyMethod1(0, 1000));
	}

	@Test
	public void testBuggyMethod1_sc6a() {
		assertEquals(0, bc.buggyMethod1(0, -1000));
	}

}
