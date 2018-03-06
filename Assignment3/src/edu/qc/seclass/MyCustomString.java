package edu.qc.seclass;


public class MyCustomString implements MyCustomStringInterface {
	
	private String string;
	

	@Override
	public String getString() {
		// TODO Auto-generated method stub
		return string;
	}

	@Override
	public void setString(String string) {
		// TODO Auto-generated method stub
		this.string = string;
	}

	@Override
	public int countNumbers() {
		// TODO Auto-generated method stub
		int countDigits=0;
		char nthChar;
		String inputString = this.string;
		for (int i =0;i< inputString.length(); i++) {

			nthChar = inputString.charAt(i);
			if (Character.isDigit(nthChar)){
				countDigits++;
			}

		}
		return countDigits;
	}

	@Override
	public String getEveryNthCharacterFromBeginningOrEnd(int n, boolean startFromEnd) {
		// TODO Auto-generated method stub
		if(string.length() < n) return "";
		if(n == 0) throw new IllegalArgumentException();
		StringBuilder sb = new StringBuilder();
		if(!startFromEnd) {
			for(int i = n ; i <= string.length(); i+=n) {
				char character = string.charAt(i-1);
				sb.append(character);
			}
		} else {
			for(int i = string.length() - n + 1; i > 0 ; i-=n) {
				char character = string.charAt(i-1);
				sb.append(character);
				
			}
			sb.reverse();
		}
		
		return sb.toString();
	}

	@Override
	public void convertDigitsToNamesInSubstring(int startPosition, int endPosition) {
		// TODO Auto-generated method stub
		if(startPosition > endPosition) throw new IllegalArgumentException();
		if(startPosition < 1 || endPosition > string.length() ) throw new MyIndexOutOfBoundsException();
		if(endPosition > 0 && string == null ) throw new NullPointerException();
		String[] digitNames = new String[] {"Zero","One","Two","Three","Four","Five","Six","Seven","Eight","Nine"};
		StringBuilder sb = new StringBuilder();
		for( int i = 0 ; i < string.length(); i++ ) {
			int position = i + 1;
			char character = string.charAt(i);
			if( position >= startPosition && position <= endPosition) {
				if(Character.isDigit(character)){
					sb.append(digitNames[Character.getNumericValue(character)]);
				} else {
					sb.append(character);
				}
			} else {
				sb.append(character);
			}
		}
		
		
		string = sb.toString();
		System.out.println(string);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
