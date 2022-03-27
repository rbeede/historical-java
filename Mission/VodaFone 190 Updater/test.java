import java.io.File;
import java.io.FileWriter;


class test {

	public static void main(String args[]) throws java.io.IOException {

		File myFile = new File(".\\TESTDUMP\\test.txt");		
		FileWriter myW;
	
		System.out.print("File is called:  ");
		System.out.println(myFile.toString());

		System.out.println("mkdir:  "+new File(".\\TESTDUMP\\").mkdir());
		System.out.println("create:  "+myFile.createNewFile());

		myW = new FileWriter(myFile);
		myW.write("TESTING 123");
		myW.close();

		System.out.println("CHAR codes for newline is " + new Character((char)13) );
		System.out.println("CHAR codes for carriage is " + new Character((char)10) );
System.out.println("Timezone is:\t" + (new java.util.Date()).getTimezoneOffset() );
		
	}

}