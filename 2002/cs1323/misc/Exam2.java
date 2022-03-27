class Exam
{
	public static void main(String args[])
	{
		StringBuffer str1 = new StringBuffer("Daisy"), str2 = new StringBuffer("Iris");
		TestExam test = new TestExam();
		test.exchange(str1, str2);
		System.out.println(str2);
	}
}


class TestExam
{
	public void exchange(StringBuffer one, StringBuffer two)
	{
		StringBuffer temp;
		temp = one;
		one = two;
		two = temp;
	}
}
