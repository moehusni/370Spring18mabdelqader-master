package edu.qc.seclass;

class BuggyClass {	
	public int buggyMethod1(int x, int y) {
		if (y > 0) {
			int results = x/y;
			return results;
		}
		return x/y;
	}

	public int buggyMethod2(int x, int y) {
		int result;
		if (y > 0) {
			result = x / y;
		}
		else if (x > y) {
			result = x / y;
		}
		else {
			result = x / y;
		}
		return result;
	}
	public int buggyMethod3(int x, int y) {
		int result;
		if (y > 0) {
			result = x / y;
			return result;
		}
		if (x == 1) {
		}
		return result = x / y;
	}
	public void buggyMethod4() {
		/*
		 * Statement coverage is a subset of branch coverage. This means
		 * branch coverage subsumes statement coverage. If every test that 
		 * achieves 100% statement coverage is able to find the fault, it means
		 * that every test that achieves 100% branch coverage is also able to find the
		 * 
		 */
	}
	public void buggyMethod5() {
		/*
		 * 100% Statement coverage is when every statement is covered
		 * by the test. Line 4 will be invoked. Division by zero will
		 * always be invoked if we have 100% statement coverage, and therefore
		 * there is no test suite that can satisfy both a and b. 
		 */
	}
	
}
