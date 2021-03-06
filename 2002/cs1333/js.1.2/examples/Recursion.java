public class Recursion
{
    //+sum1
    public static int sum1(int n)
    // post: compute the sum of 0..n
    {
	int result = 0;
	for (int i = 1; i <= n; i++)
	{
	    result = result + i;
	}
	return result;
    }
    //-sum1
    //+sum2
    public static int sum2(int n)
    // post: compute the sum of 0..n
    {
	if (n < 1) return 0;
	else return sum1(n-1) + n;
    }
    //-sum2
    //+sum3
    public static int sum3(int n)
    // post: compute the sum of 0..n
    {
	if (n < 1) return 0;        // base case
	else return sum3(n-1) + n;  // reduction, progress, sol'n
    }
    //-sum3
    /*
    //+sum3b
    public static int sum3(int n)
    // post: compute the sum of 0..n
    {
	if (n < 1) return 0;	    // 1
	else return                 // 2
                    sum3(           // 3
			 n-1        // 4
			    ) + n;  // 5
    }
    //-sum3b
    */

    public static void main(String args[])
    {
	int i;
	for (i = -1; i < 10; i++)
	{
	    System.out.println(i+": "+sum1(i)+" "+sum2(i)+" "+sum3(i));
	}
    }
}


/*
//+output
-1: 0 0 0
0: 0 0 0
1: 1 1 1
2: 3 3 3
3: 6 6 6
4: 10 10 10
5: 15 15 15
6: 21 21 21
7: 28 28 28
8: 36 36 36
9: 45 45 45
//-output
*/
