package javabook;

public class Format
{
   //only class methods
   public static String leftAlign(int width, int decimalPlaces, double number)
   {
      if (width < decimalPlaces + 2) {//2 for period & minus sign
         return pad('*',width);
      }
      else {
         double num = number * Math.pow(10,decimalPlaces);
         num = Math.round(num);
         num = num / Math.pow(10,decimalPlaces);
         String str = convert(num,decimalPlaces);
         if (width < str.length()) {
            return pad('*',width);
         }
         else {
            return str + pad(' ', width-str.length());
         }
      }
   }

   public static String leftAlign(int width, long number)
   {
      String str = convert(number);

      if (width < str.length()) {
         return pad('*',width);
      }
      else {
         return str + pad(' ',width-str.length());
      }
   }

   public static String leftAlign(int width, String str)
   {
      if (width < str.length()) {
         return pad('*',width);
      }
      else {
         return str + pad(' ',width-str.length());
      }
   }

   public static String rightAlign(int width, int decimalPlaces, double number)
   {
      if (width < decimalPlaces + 2) {//2 for period & minus sign
         return pad('*',width);
      }
      else {
         double num = number * Math.pow(10,decimalPlaces);
         num = Math.round(num);
         num = num / Math.pow(10,decimalPlaces);
         String str = convert(num,decimalPlaces);
         if (width < str.length()) {
            return pad('*',width);
         }
         else {
            return pad(' ', width-str.length()) + str;
         }
      }
   }

   public static String rightAlign(int width, long number)
   {
      String str = convert(number);

      if (width <= str.length()) {
         return pad('*',width);
      }
      else {
         return pad(' ',width-str.length()) + str;
      }
   }

   public static String rightAlign(int width, int number)
   {
      long num = number;
      String str = convert(num);

      if (width < str.length()) {
         return pad('*',width);
      }
      else {
         return pad(' ',width-str.length()) + str;
      }
   }

   public static String rightAlign(int width, String str)
   {
      if (width < str.length()) {
         return pad('*',width);
      }
      else {
         return pad(' ',width-str.length()) + str;
      }
   }

   public static String centerAlign(int width, int decimalPlaces, double number)
   {
      if (width < decimalPlaces + 2) {//2 for period & minus sign
         return pad('*',width);
      }
      else {
         double num = number * Math.pow(10,decimalPlaces);
         num = Math.round(num);
         num = num / Math.pow(10,decimalPlaces);
         String str = convert(num,decimalPlaces);
         if (width < str.length()) {
            return pad('*',width);
         }
         else {
            return pad(' ',(width-str.length()+1)/2) +
                       str + pad(' ',(width-str.length())/2);
         }
      }
   }

   public static String centerAlign(int width, long number)
   {
      String str = convert(number);

      if (width < str.length()) {
         return pad('*',width);
      }
      else {
         return pad(' ',(width-str.length()+1)/2) +
                    str + pad(' ',(width-str.length())/2);
      }
   }

   public static String centerAlign(int width, int number)
   {
      long num = number;
      String str = convert(num);

      if (width < str.length()) {
         return pad('*',width);
      }
      else {
         return pad(' ',(width-str.length()+1)/2) +
                    str + pad(' ',(width-str.length())/2);
      }
   }

   public static String centerAlign(int width, String str)
   {
      if (width < str.length()) {
         return pad('*',width);
      }
      else {
         return pad(' ',(width-str.length()+1)/2) +
                    str + pad(' ',(width-str.length())/2);
      }
   }

   private static String convert(double number)
   {
      return "" + number;
   }

   private static String convert(double number, int decimalPlaces)
   {
      int i = 0, fractionalPart, strlen, numOfDigitsToAppend;
      StringBuffer str = new StringBuffer("" + number);
      strlen = str.length();

      //note: number may not have a decimal point
      while (i < strlen) {
         if (str.charAt(i) != '.') {
            i++;
         }
         else {
            break;
         }
      }

      if (i == strlen) {
         //no decimal point, so add it
         str.append(".");
         numOfDigitsToAppend = decimalPlaces;
      }
      else {

         fractionalPart = strlen - i-1; //# of digits right of decimal pt

         //now pad zeroes if necessary
         numOfDigitsToAppend = decimalPlaces - fractionalPart;
      }

      for (i = 0; i < numOfDigitsToAppend; i++) {
         str.append("0");
      }

      return str.toString();

   }


   private static String convert(long number)
   {
      return "" + number;
   }

   private static String pad(char c, int width)
   {
      StringBuffer str = new StringBuffer("");
      if (width < 1) {
         return "";
      }
      else {
         for (int i = 0; i < width; i++) {
            str.append(c);
         }
         return str.toString();
      }
   }

}
