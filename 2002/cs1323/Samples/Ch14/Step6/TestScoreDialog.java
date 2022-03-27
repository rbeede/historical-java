import java.awt.*;
import javabook.*;

class TestScoreDialog
{

   private MultiInputBox   scoreBox;
   private int[]           score;

   public TestScoreDialog(Frame owner, int size)
   {
       scoreBox = new MultiInputBox(owner, size);
       score    = new int[size];

       String label[] = new String[size];

       for (int i = 0; i < size; i++) {
           label[i] = "Test  #" + (i+1);
       }

       scoreBox.setLabels(label);

   }

    public boolean isCanceled()
    {
        return scoreBox.isCanceled();
    }


    public void show(Student student)
    {
        String value, scoreStr[];

        for (int i = 0; i < score.length; i++) {
            value = Convert.toString(student.getTestScore(i+1));
            scoreBox.setValue(i, value);
        }

        scoreStr = scoreBox.getInputs();

        if (!scoreBox.isCanceled()) {
           for (int i = 0; i < scoreStr.length; i++) {
               score[i] = Convert.toInt(scoreStr[i]);
           }
        }
    }

    public int[] getTestScores()
    {
        return score;
    }

}
