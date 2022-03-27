class Exam
{
	public static void main(String args[])
	{
		String str1 = "Daisy", str2 = "Iris";
		TestExam test = new TestExam();
		test.exchange(str1, str2);
		System.out.println(str2);
	}
}


class TestExam
{
	public void exchange(String one, String two)
	{
		String temp;
		temp = one;
		one = two;
		two = temp;
	}
}
