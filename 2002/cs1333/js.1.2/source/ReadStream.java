// A stream for reading basic types from input.
// (c) 1998 McGraw-Hill

package structure;
import java.io.*;

public class ReadStream extends FilterInputStream 
{
    protected DataInputStream strm;
    protected boolean atEOF;            // are we at the end-of-file
    protected char buffer[];            // pushback buffer
    protected int buffersize;           // current size of pushback buffer      
    protected int buffertop;            // top element of pushback stack

    protected boolean absorbNL = false; // absorb NL if next (part of cr)

    public ReadStream()
    // post: constructs a pascal-like stream based on System.in
    {
        this(System.in);
    }

    public ReadStream(InputStream strm)
    // pre: strm is a valid input stream.
    // post: constructs a pascal-like stream based on strm
    {
  
        // This stream filters input from a data input stream
        // which filters input from strm.
        super(new DataInputStream(strm));
        this.strm = (DataInputStream)in;
        atEOF = false;
        buffer = new char[8];
        buffersize = 8;
        buffertop = -1;
    }

    public boolean eof()
    // pre: are we at the end-of-file?
    {
        // have we already detected EOF?
        if (atEOF) return true;
        // check stream by attempting a read
        peek();
        return atEOF;
    }

    static private boolean isWhite(char c)
    // post: returns true if char is whitespace
    {
        return Character.isWhitespace(c); /* JDK 1.1 */
    }

    public char peek()
    // post: returns next character in stream, without consuming it
    {
        char c = readChar();
        pushbackChar(c);
        return c;
    }

    public boolean eoln()
    // post: returns true if next stream char is a eoln char
    {
        char c = peek();
        return eof() || (c == '\n') || (c == '\r');
    }

    public void readln()
    // post: reads input stream until end-of-line (\r or \n or \n\r)
    {
        char c;
        for (c = readChar(); !eoln(); c = readChar());
    }

    public void skipWhite()
    // post: input pointer is at EOF, or non-whitespace char.
    {
        char c;
        for (c = readChar(); isWhite(c);  c = readChar());
        pushbackChar(c);
    }

    public String readString()
    // post: reads next word as string
    {
        char buffer[] = new char[512];
        char c = 0;
        int count = 0;

        skipWhite();
        while (!eof())
        {
            c = readChar();
            if (isWhite(c))
            {
                pushbackChar(c);
                break;
            }
            buffer[count++] = c;
        }
        return new String(buffer,0,count);
    }

    private boolean acceptChar(char c)
    // post: returns true if the next character is upper or lower c
    {
        char d = readChar();
        if (Character.toLowerCase(c) ==
            Character.toLowerCase(d)) return true;
        pushbackChar(d);
        return false;
    }

    private boolean acceptWord(String s)
    // post: returns true if word in s in on input (consumed)
    //       or false
    {
        char c;
        skipWhite();
        for (int i = 0; i < s.length(); i++)
        {
            if (!acceptChar(s.charAt(i))) {
                for (int j = i-1; j>= 0; j--) {
                    pushbackChar(s.charAt(j));
                }
                return false;
            }
        }
        return true;
    }

    public boolean readBoolean()
    // post: returns next boolean value read from input
    {
        if (acceptWord("true")) return true;
        else if (!acceptWord("false")) Assert.fail("Boolean not found on input.");
        return false;
    }

    public char readChar()
    // post: returns next character, or 0 for eof
    {
        char c = (char)0;
        try {
            if (atEOF) return (char)0;
            if (buffertop >= 0) {
                c = buffer[buffertop--];
            } else {
                c = (char)strm.readByte();
            }
        }
        catch (EOFException e) {
            atEOF = true;
        }
        catch (IOException e) {
            Assert.fail("Input error free.");
        }
        finally {
            if (absorbNL && (c == '\n')) {
                absorbNL = false;
                c = readChar();
            }
            absorbNL = c == '\r';
            return c;
        }
    }

    public void pushbackChar(char c)
    // post: pushes back character, possibly clearing EOF.
    //       if c == 0, does nothing
    {
        if (c == (char)0) return;
        atEOF = false;
        buffertop++;
        if (buffertop == buffersize) {
            // buffer too small, extend it.
            char old[] = buffer;
            buffersize = buffersize*2;
            buffer = new char[buffersize];
            for (int i = 0; i < buffertop; i++)
            {
                buffer[i] = old[i];
            }
        }
        buffer[buffertop] = c;
        absorbNL = false;
    }

    public double readDouble()
    // post: reads in double value
    {
        StringBuffer sb = new StringBuffer();
        char c;
        skipWhite();
        if (acceptChar('+')) sb.append('+');
        else if (acceptChar('-')) sb.append('-');
        c = readChar();
        while (Character.isDigit(c))
        {
            sb.append(c);
            c = readChar();
        }
        pushbackChar(c);
        if (acceptChar('.')) {
            sb.append('.');
            c = readChar();
            while (Character.isDigit(c))
            {
                sb.append(c);
                c = readChar();
            }
            pushbackChar(c);
        }
        if (acceptChar('E'))
        {
            sb.append('E');
            if (acceptChar('+')) sb.append('+');
            else if (acceptChar('-')) sb.append('-');
            c = readChar();
            while (Character.isDigit(c))
            {
                sb.append(c);
                c = readChar();
            }
            pushbackChar(c);
        }
        String s = sb.toString();
        //      System.out.println("["+s+"]");
        return Double.valueOf(s).doubleValue();
    }

    public float readFloat()
    // post: reads floating point value and returns value
    {
        return (float)readDouble();
    }

    public void readFully(byte b[]) throws IOException
    // post: reads an array of bytes from stream???
    {
        strm.readFully(b);
    }

    public void readFully(byte b[], int off, int len) throws IOException
    // post: reads a portion of an array of bytes from stream
    {
        strm.readFully(b,off,len);
    }

    public short readShort()
    // post: reads a short integer from stream
    {
        return (short)readLong();
    }

    public int readInt()
    // post: reads an integer from stream
    {
        return (int)readLong();
    }

    public long readLong()
    // post: reads a long integer from stream
    {
        boolean negate = false;
        int digitsRead = 0;
        long value = 0;
        int base = 10;
        char c;
        int d;
        skipWhite();
        if (eof()) return 0;
        for (;;)
        {
            if (eof()) break;
            c = readChar();
            if (digitsRead == 0) {
                if (c == '-') { 
                    negate = true;
                    continue;
                }
            }
            if ((digitsRead == 0) && (c == '0')) {
                base = 8;
                digitsRead++;
                continue;
            }
            if ((digitsRead == 1) && (base == 8) && ((c == 'x') ||
                                                     (c == 'X'))) {
                base = 16;
                digitsRead++;
                continue;
            }
            d = c - '0';
            if ((c >= 'a') && (c <= 'f')) {
                d = c - 'a' + 10;
            } else if ((c >= 'A') && (c <= 'F')) {
                d = c - 'A' + 10;
            }
            if ((d < 0) || (d >= base)) {
                pushbackChar(c);
                break;
            }
            digitsRead++;
            value = value*base+d;
        }
        if (negate) value = -value;
        return value;
    }

    public String readLine()
    // post: reads remainder of line, returns as string
    {
        StringBuffer result = new StringBuffer();
        char c;
        while (!eoln())
        {
            result.append(readChar());
        }
        readChar();
        return result.toString();
    }

    public String readUTF() throws IOException
    // post: not supported.  Avoid use.
    {
        return strm.readUTF();
    }
}

