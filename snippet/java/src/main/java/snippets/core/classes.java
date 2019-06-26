package snippets.core;

class A
{
}

class B {
   public void print () {
      System.out.println("Class B");
   }
}

class C
{
   public static void main(String[] args)
   {
      System.out.println("Application C entry point ");
      B1 b = new B1();
      b.print();
   }
}