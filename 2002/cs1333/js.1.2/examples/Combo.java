
public class Combo
{
    protected int combination[];
    protected boolean locked;
    protected int comboIndex;

    public Combo(int combo[])
    {
	combination = new int[combo.length];
	for (int i = 0; i < combo.length; i++)
	{
	    combination[i] = combo[i];
	}
	comboIndex = 0;
	locked = comboIndex < combination.length;
    }

    public Combo()
    {
	combination = new int[4];
	combination[1] = combination[3] = 0;
	combination[0] = 9;
	combination[2] = 21;
	locked = true;
    }
 
    public void lock()
    {
	reset();
	locked = comboIndex < combination.length;
    }

    public void reset()
    {
	comboIndex = 0;
    }

    public void press(int i)
    {
	if (!isLocked()) return;
	if (comboIndex >= combination.length) return;
	if (combination[comboIndex] != i) comboIndex = combination.length;
	else {
	    comboIndex++;
	    locked = comboIndex != combination.length;
	}
    }

    public boolean isLocked()
    {
	return locked;
    }

    public static void main(String[] args)
    {
	Combo lock = new Combo();
	System.out.println("Locked: "+lock.isLocked());
	lock.press(4);
	System.out.println(" after press(4)...");
	System.out.println("Locked: "+lock.isLocked());
	lock.reset();
	System.out.println(" after reset()...");
	System.out.println("Locked: "+lock.isLocked());
	lock.press(9);
	System.out.println(" after press(9)...");
	System.out.println("Locked: "+lock.isLocked());
	lock.press(0);
	System.out.println(" after press(0)...");
	System.out.println("Locked: "+lock.isLocked());
	lock.press(21);
	System.out.println(" after press(21)...");
	System.out.println("Locked: "+lock.isLocked());
	lock.press(0);
	System.out.println(" after press(0)...");
	System.out.println("Locked: "+lock.isLocked());
	lock.lock();
	System.out.println(" after lock()...");
	System.out.println("Locked: "+lock.isLocked());
    }
}

/*
//+output
Locked: true
 after press(4)...
Locked: true
 after reset()...
Locked: true
 after press(9)...
Locked: true
 after press(0)...
Locked: true
 after press(21)...
Locked: true
 after press(0)...
Locked: false
 after lock()...
Locked: true
//-output
*/
