class EmptySolution {

	// online interview coding challenges are often given on a website like HackerRank.
	// In Java, your solution typically must go inside an already created "Solution" class,
	// and the function that gets called is usually static, which is how I've set it up here.
	
	
	public static int solve(String[] inputLines) {

		// your code goes here

	}




	public static void main(String[] args) {

		String[] travelers = {   // 8 steps
			"David HEALTHY London Tokyo Berlin Tokyo",
			"Lisa RECOVERING Tokyo Berlin London Seattle Berlin",
			"Frank SICK Seattle London Tokyo Berlin",
			"Mary SICK Berlin Berlin London Seattle"
		};
		System.out.println("Steps required: " + solve(travelers));
	}
}
