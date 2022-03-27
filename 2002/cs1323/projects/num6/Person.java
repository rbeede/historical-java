
class Person
{

/*******************
   Data Members
*******************/   

   private String  name;
   private int     age;
   private char    gender;

/*******************
   Constructors
*******************/   
   public Person()
   {
      this("Not Given", 0, 'U');
   }
   
   public Person(String name, int age, char gender)
   {
      this.age = age;
      this.name = name;
      this.gender = gender;
   }
   
   public int getAge() 
   {
      return age;
   }
   
   public char getGender()
   {
      return gender;
   }
   
   public String getName()
   {
      return name;
   }
   
   public void setAge(int age)
   {
      this.age = age;
   }
   
   public void setGender(char gender)
   {
      this.gender = gender;
   }
   
   public void setname(String name)
   {
      this.name = name;
   }

}
