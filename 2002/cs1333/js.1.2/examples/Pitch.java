// An example implementation of a pitch.
import java.lang.Math;

public class Pitch
{
    protected double A;
    protected int steps;
    final static double half = Math.pow(2.0,1.0/12.0);
    final static double USA = 440.0;
    public Pitch()
    {
	steps = 0;
    }
    public Pitch(double freq, double stdA)
    {
	A = stdA;
	steps = 0;
	while (freq > A)
	{
	    freq /= half;
	    steps++;
	}
	while (freq < A)
	{
	    freq *= half;
	    steps--;
	}
    }
    public Pitch(int steps, double stdA)
    {
	A = stdA;
	this.steps = steps;
    }
    public Pitch(int steps)
    {
	this(steps,USA);
    }

    public Pitch(double freq)
    {
	this(freq,USA);
    }

    public double frequency()
    {
	return Math.pow(half,(double)steps)*A;
    }
    public int steps()
    {
	return steps;
    }

    public static void main(String a[])
    {
	Pitch middleC = new Pitch(131.0);
	for (int i = (-9)-12; i < 12; i++) {
	    Pitch p1 = new Pitch(i);
	    Pitch p2 = new Pitch(i,415.0);
	    System.out.println(p1.steps()+": "+p1.frequency()+" | "+p2.frequency());
	}
    }
}

/*
//+output
-21: 130.8127826502992 | 123.38023818153219
-20: 138.59131548843592 | 130.716808926593
-19: 146.83238395870364 | 138.48963487014095
-18: 155.56349186104035 | 146.72465709620852
-17: 164.81377845643485 | 155.4493592259556
-16: 174.61411571650183 | 164.69285914170058
-15: 184.99721135581706 | 174.48600616514565
-14: 195.99771799087452 | 184.8614840141203
-13: 207.65234878997245 | 195.8539198814513
-12: 219.9999999999999 | 207.4999999999999
-11: 233.08188075904488 | 219.83859207955368
-10: 246.94165062806198 | 232.9108750241948
-9: 261.6255653005985 | 246.76047636306453
-8: 277.182630976872 | 261.4336178531861
-7: 293.66476791740746 | 276.97926974028206
-6: 311.1269837220808 | 293.44931419241715
-5: 329.62755691286986 | 310.89871845191135
-4: 349.2282314330038 | 329.38571828340133
-3: 369.99442271163434 | 348.9720123302915
-2: 391.99543598174927 | 369.7229680282408
-1: 415.3046975799451 | 391.7078397629028
0: 440.0 | 415.0
1: 466.1637615180899 | 439.6771841591075
2: 493.8833012561241 | 465.8217500483898
3: 523.2511306011974 | 493.52095272612934
4: 554.3652619537443 | 522.8672357063724
5: 587.3295358348153 | 553.9585394805644
6: 622.253967444162 | 586.8986283848345
7: 659.2551138257401 | 621.797436903823
8: 698.456462866008 | 658.771436566803
9: 739.988845423269 | 697.9440246605833
10: 783.990871963499 | 739.445936056482
11: 830.6093951598907 | 783.415679525806
//-output
*/
