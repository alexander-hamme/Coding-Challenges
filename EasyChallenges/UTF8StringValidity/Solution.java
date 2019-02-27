/*
UTF-8 Bit-String Validity Checker

Character	Code point	UTF-8 Encoding
a	        97	        01100001
ü	        252	        11000011 10111100
☃	        9731	    	11100010 10011000 10000011

1-byte: 0xxxxxxx  
2-byte: 110xxxxx 10xxxxxx  
3-byte: 1110xxxx 10xxxxxx 10xxxxxx
4-byte: 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
(...) (pretending characters can contain more than 4 bytes)

Task:

Write a function that determines whether a given bit-String of an
undetermined length has valid UTF-8 encoding.
*/

public class Solution {

    private static final int max_bytes = 4;  // as long as this is >= the actual max number of bytes, 
				  	     // the isValidUtf8String() function will work.

    public static void main(String args[]) throws Exception {

        Solution s = new Solution();
        // a
        s.check(true,  "01100001");
        // a☃
        s.check(true,  "01100001111000101001100010000011");
	// aaa☃
        s.check(true,  "011000010110000101100001111000101001100010000011");
        //              |       |       |       | x     |       |       |       
        s.check(false, "011000010110000101100001110000101001100010000011");
        s.check(false, "10000011");
        s.check(false, "11111111");
        
        System.out.println("All tests passed");
    }
    
    public void check(boolean expected, String input) {
        if (isValidUtf8String(input, max_bytes) != expected) { 
            throw new RuntimeException(String.format("Expected %s for %s, but got %s", expected, input, !expected));
        }
    }
    
    private String getStartString(int n_bytes) {
        return (n_bytes == 1) ? "0" : String.format("%s0", new String(new char[n_bytes]).replace("\0", "1"));
        //return (n_bytes == 1) ? "0" : String.format("%s0", StringUtils.repeat("1", n_bytes));
    }
    
    public boolean isValidUtf8String(String input, int max_bytes) {
        
        int len = input.length();
        
        int start = 0, rear = 8;
        
        for (; rear<len+1; rear += 8) {
        
            int num_bytes = (rear - start) / 8;
            
            String str = getStartString(num_bytes);
            
            if (input.startsWith(getStartString(num_bytes), start)) {
                // move start forward until it reaches rear,
		// checking that each consecutive byte (8 bits) of the current subString 
		// starts with "10"
                for (start = start + 8; start < rear; start += 8) {
                    if (! input.startsWith("10", start)) return false;
                }

            } else if (rear == len || num_bytes == max_bytes) {
                return false;
            } 
        }
        return true;
    }
}
