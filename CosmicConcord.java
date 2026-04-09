/*  John Sullivan
    Cosmic Concordance
    COP3503 Computer Science 2
    CosmicConcord.java
*/

public class CosmicConcord {
	protected static int[] A, B;
	protected static int D, G, K;

    public static void solve(int[] a, int[] b, int n, int m, int d, int g, int k) {
        A = a; B = b; D = d; G = g; K = k;
    	// call solveRecMemo and solveTab here, print results
    }

    public int solveRecMemo(int N, int M, int[][] memo) {
        if (N == 0 || M == 0) 
            return 0; 
  
        if (memo[N][M] != -1) 
            return memo[N][M]; 
  
        if (A[N - 1] == B[M - 1]) 
		{ 
            memo[N][M] = 1 + solveRecMemo(N - 1, M - 1, memo); 
            return memo[N][M]; 
        } 
  
        memo[N][M] = Math.max(solveRecMemo(N, M - 1, memo), solveRecMemo(N - 1, M, memo)); 
        return memo[N][M]; 
    }

    public int solveTab(int N, int M, int[][] memo) {
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

    public static int max(int a, int b) 
	{ 
		if(a > b)
			return a;
		else
			return b;
	} 
}