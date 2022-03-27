public class Fido
{
    //+code
    static public int fido(int n)
    // pre: n is a non-negative integer
    // post: result is the nth from the sequence
    //     1, 3, 7, 15, 31, 63, 127, ...
    {
	int result = 1;
	if (n > 1) result = 1+fido(n-1)+fido(n-1);
	// assertion: the above if condition was tested
	//    fido(n) times while computing result
	return result;
    }
    //-code
    public static void main(String args[])
    {
        System.out.println(fido(1));
        System.out.println(fido(2));
        System.out.println(fido(3));
        System.out.println(fido(4));
        System.out.println(fido(5));
        System.out.println(fido(6));
        System.out.println(fido(7));
    }
}

/*
//+output
1
3
7
15
31
63
127
//-output
*/
