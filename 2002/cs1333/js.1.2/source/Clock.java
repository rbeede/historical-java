// A simple clock for use in timing things.
// (c) 1998 McGraw-Hill
package structure;

public class Clock {
    // we use a native-code library for structures
    protected boolean running;  // is the clock on?
    protected long strt;        // starting millisecond count
    protected long accum;       // total milliseconds

    public Clock()
    // post: returns a stopped clock
    {
        running = false;
        strt = 0;
        accum = 0;
    }

    public void start()
    // post: clock is stopped
    // pre: starts clock, begins measuring possibly accumulated time
    {
        running = true;
        strt = System.currentTimeMillis();
    }

    public void stop()
    // pre: clock is running
    // post: stops clock, and accumulates time
    {
        running = false;
        accum += (System.currentTimeMillis()-strt);
    }

    public double read()
    // pre: clock is stopped.
    // post: returns the accumulated time on the clock
    {
        return (double)accum/(double)1000.0;
    }

    public void reset()
    // post: stops running clock and clears the accumulated time.
    {
        running = false;
        accum = 0;
    }

    public String toString()
    // post: returns a string representation of the clock
    {
        return "<Clock: "+read()+" seconds>";
    }
}
