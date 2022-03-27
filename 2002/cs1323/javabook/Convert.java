package javabook; 
 
public class Convert 
{ 
    public static int toInt(String str) throws NumberFormatException 
    { 
        return Integer.parseInt(str); 
    } 
 
    public static long toLong(String str) throws NumberFormatException 
    { 
        return Long.parseLong(str); 
    } 
 
    public static float toFloat(String str) throws NumberFormatException
    { 
        Float floatObj = new Float(str); 
        return floatObj.floatValue(); 
    } 
 
    public static double toDouble(String str) throws NumberFormatException 
    { 
        Double doubleObj = new Double(str); 
        return doubleObj.doubleValue(); 
    } 
 
    public static char toChar(String str) 
    { 
        return str.charAt(0); 
    } 
 
    public static boolean toBoolean(String str) 
    { 
        Boolean boolObj = new Boolean(str); 
        return boolObj.booleanValue(); 
    } 
 
    public static String toString(long number) 
    { 
        Long longObj = new Long(number); 
        return longObj.toString(); 
    } 
 
    public static String toString(double number) 
    { 
        Double doubleObj = new Double(number); 
        return doubleObj.toString(); 
    } 
 
    public static String toString(boolean bool) 
    { 
        Boolean boolObj = new Boolean(bool); 
        return boolObj.toString(); 
    } 
 
    public static String toString(char ch) 
    { 
        Character charObj = new Character(ch); 
        return charObj.toString(); 
    } 
} 
