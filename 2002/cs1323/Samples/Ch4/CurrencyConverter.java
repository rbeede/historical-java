/*

   Class:  CurrencyConverter
   
   Description: 
                An object of this class is used to do the 
                currency conversion between a foreign
                currency and the U.S. dollar.
*/

class CurrencyConverter
{

/**********************
   Data Members
**********************/

   private float exchangeRate;  // How much the US $1.00 is worth
                                // in the foreign currency

/**********************
   Public Methods:
   
      float   fromDollar        ( float )
      float   toDollar          ( float )
      void    setExchangeRate   ( float )
      
**********************/
                                
   /*  Method:       fromDollar
   
       Purpose:      Converts a given amount in dollars into
                     an equivalent amount in foreign currency
       Parameters:
                     float dollar 
                        - amount in dollars                                
                      
       Returns:      float foreignCurrency
                        - amount in foreign currency equivalent
                          to the given dollar amount
   */
   
   public float fromDollar( float dollar )
   {
      return (dollar * exchangeRate);
   }
   
   /*  Method:       toDollar
   
       Purpose:      Converts a given amount in foreigh currency
                     an equivalent dollar amount
       Parameters:
                     float foreignMoney 
                        - amount in foreign currency                                
                      
       Returns:      float dollar
                        - dollar amount equivalent to the given 
                          foreign currency amount
   */
   
   public float toDollar( float foreignMoney )
   {
      return (foreignMoney / exchangeRate);
   }
                             
   /*  Method:       setExchangeRate
   
       Purpose:      Sets the exchange rate to the value passed
                     to this method
       Parameters:
                     float rate 
                        - the exchange rate
                      
       Returns:      None
   */
   
   public void setExchangeRate( float rate )
   {
      exchangeRate = rate;
   }
                   
}                   
