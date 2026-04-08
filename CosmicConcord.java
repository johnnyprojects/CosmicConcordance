/*  John Sullivan
    Cosmic Concordance
    COP3503 Computer Science 2
    CosmicConcord.java
*/

public class CosmicConcord {
    public int solve(int[] A, int[] B, int N, int M, int D, int G, int K) {
        return 1;
    }

    public int solveRecMemo(int[] A, int[] B, int N, int M, int D, int G, int K, int[][] memo) {
        if (N == 0 || M == 0) 
            return 0; 
  
        if (memo[N][M] != -1) 
            return memo[N][M]; 
  
        if (A[N - 1] == B[M - 1]) 
		{ 
            memo[N][M] = 1 + lcs(A, B, N - 1, M - 1, D, G, K, memo); 
            return memo[N][M]; 
        } 
  
        memo[N][M] = Math.max(lcs(A, B, N, M - 1, D, G, K, memo), lcs(A, B, N - 1, M, D, G, K, memo)); 
        return memo[N][M]; 
    }

    public int solveTab(int[] A, int[] B, int N, int M, int D, int G, int K, int[][] memo) {
        int c[][] = new int[N + 1][M + 1]; 
		char b[][] = new char[N + 1][M + 1]; 
		
		for(int i = 0; i <= N; ++i)
			c[i][0] = 0;
		
		for(int j = 0; j <= M; ++j)
			c[0][j] = 0;
		
		
        for (int i = 1; i <= N; i++) 
		{ 
            for (int j = 1; j <= M; j++) 
			{ 
                if (A[i - 1] == B[j - 1]) 
				{
                    c[i][j] = c[i - 1][j - 1] + 1; 
					b[i][j] = 'd';
				}
                else if(c[i - 1][j] >= c[i][j - 1])
				{
                    c[i][j] = c[i - 1][j];
					b[i][j] = 'v';
				}
				else
				{
					c[i][j] = c[i][j - 1];
					b[i][j] = 'h';
				}
            } 
        }
        return c[N][M]; 
    }
	
    public static int lcs(int[] A, int[] B, int N, int M, int D, int G, int K, int[][] memo) 
	{ 
		if (N == 0 || M == 0) 
			return 0; 
		if (A[N - 1] == B[M - 1]) 
			return 1 + lcs(A, B, N - 1, M - 1, D, G, K, memo); 
		else
			return max(lcs(A, B, N, M - 1, D, G, K, memo), lcs(A, B, N - 1, M, D, G, K, memo)); 
	}

    public static int max(int a, int b) 
	{ 
		if(a > b)
			return a;
		else
			return b;
	} 
}