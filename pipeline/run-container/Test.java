package pakage1;

public class Test {
    public static void main(String[] args) {

        System.out.println("Recieved Number is "+args[0]);
        int number=Integer.parseInt(args[0]);
        //find squre of number
        for (int i=0; i<=number;i++ ) {
            
            System.out.println("Square of "+i+" is "+(i*i));
        }
        System.out.println("Hello World");
    }
}
