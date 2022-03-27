class UndergraduateStudent extends Student
{
   public UndergraduateStudent( )
   {
      super("No Name");
   }

   public UndergraduateStudent(String name)
   {
      super(name);
   }

   public void computeCourseGrade()
   {
      int total = 0;
      for (int i = 0; i < NUM_OF_TESTS; i++) {
         total += test[i];
      }

      if (total / NUM_OF_TESTS >= 70) {
         courseGrade = "Pass";
      }
      else {
         courseGrade = "No Pass";
      }
   }
}
