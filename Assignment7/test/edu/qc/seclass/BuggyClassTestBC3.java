package edu.qc.seclass;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BuggyClassTestBC3 {
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
	public void testBuggyMethod3_1bc() {
		assertEquals(10, bc.buggyMethod3(10, 1));
	}

	@Test
	public void testBuggyMethod3_2bc() {
		assertEquals(-5, bc.buggyMethod3(10, -2));
	}

}
