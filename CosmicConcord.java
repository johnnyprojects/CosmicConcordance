/*  John Sullivan
    Cosmic Concordance
    COP3503 Computer Science 2
    CosmicConcord.java
*/

public class CosmicConcord {
	protected static int[] A, B;
	protected static int D, G, K, N, M;

    public static void solve(int[] a, int[] b, int n, int m, int d, int g, int k) {
        A = a; B = b; D = d; G = g; K = k; N = n; M = m;
		int[][][] memo = new int[N][M][K+1]; // O(N * M * K)
		for (int i = 0; i < N; i++) 
            for (int j = 0; j < M; j++)
				for (int h = 0; h < K + 1; h++)
                	memo[i][j][h] = -1; 

    	// call solveRecMemo and solveTab here then print results via schema
		// memoization
		/*
		- need a startup loop to find first best pair
		- start up loop will be O(N * M), with subsequent calls potentially exploring O(N * M),
		  with those calls exploring O(K) dip levels
		- therefore O(N^2 * M^2 * K)
		*/
		int memoLen = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (Math.abs(A[i] - B[j]) <= D)
					memoLen = Math.max(memoLen, solveRecMemo(i, j, memo, 0 ));
			}
		}

		// tabulation
		int tabLen = solveTab();

		// print
    }

    public static int solveRecMemo(int i, int j, int[][][] memo, int dips) {
        if (memo[i][j][dips] != -1) 
        	return memo[i][j][dips];

		int length = 0;

		for (int ni = i + 1; ni < N; ni++)
		{
			for (int nj = j + 1; nj < M; nj++)
			{
				if (Math.abs(A[ni] - B[nj]) <= D)
				{ 
					// increase check by growth value
					boolean inc = (A[ni] - A[i] >= G) && (B[nj] - B[j] >= G);
					if (inc) // if it is increasing, dont increase dip count
            			length = Math.max(length, solveRecMemo(ni, nj, memo, dips));
					else if (dips + 1 <= K) // increase dip count if it doesnt fail <= K
						length = Math.max(length, solveRecMemo(ni, nj, memo, dips + 1));
        		} 
			}
		}
        
		// add 1 (current match) plus best extension we have so far
        memo[i][j][dips] = length + 1;
        return memo[i][j][dips]; 
    }

    public static int solveTab() {
        int c[][][] = new int[N][M][K + 1];

		int length = 0;
		
		// replace double for loop going forward with one nested loop going backwards to fill c
		// O(N * M * K * N * M)
		// first two loops find start location from the back like in class lecture
		for (int i = N - 1; i >= 0; i--)
		{
			for (int j = M - 1; j >= 0; j--)
			{
				if (Math.abs(A[i] - B[j]) <= D)
				{
					for (int dips = K; dips >= 0; dips--) // compute answer for each dip count, worst case O(K)
					{ 
						int next_best = tabHelper(i, j, c, dips); // call helper
						c[i][j][dips] = next_best + 1; // labels in "table" the length for "cell"
						length = Math.max(length, c[i][j][dips]);
					}
				}
			}
		}
		return length;
    }

	private static int tabHelper(int i, int j, int[][][] c, int dips) {
		int next_best = 0;

		// these next loops are just the same as before
		// they find the next best options that are within tolerance D
		// then do a check if they have proper growth, or try using a K
		for (int ni = i + 1; ni < N; ni++) { 
			for (int nj = j + 1; nj < M; nj++) {
				if (Math.abs(A[ni] - B[nj]) <= D)
					{
					boolean inc = (A[ni] - A[i] >= G) && (B[nj] - B[j] >= G);
					if (inc)
						next_best = Math.max(next_best, c[ni][nj][dips]);
					else if (dips + 1 <= K)
						next_best = Math.max(next_best, c[ni][nj][dips + 1]);
				}
			}
		}
		return next_best;
	}
}