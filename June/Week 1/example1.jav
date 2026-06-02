import java.util.*;
class VariableExample{
    public static void main(String[] args){
        int a = 10;
        int b = 3;
        int sum = a + b;
        int div = a / b;
        double divExact = (double) a / b;
        System.out.println("Sum: " + sum);
        System.out.println("Integer Division: " + div);
        System.out.println("Exact Division: " + divExact);
    }
}