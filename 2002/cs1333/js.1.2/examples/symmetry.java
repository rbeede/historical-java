//+simple
Object a, b;
Object temp;

//...

temp = a;
a = b;
b = temp;
//-simple

//+simpleA
Object a...;
//...
... = a;
a = ...;
...;
//-simpleA

//+simpleB
Object ...b;
//...
...;
... = b;
b = ...;
//-simpleB
