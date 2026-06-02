import java.util.*;
class CoreBasics{
    public static void variables(){
        print("Hello World");
        print("Welcome to Java Programming");
        byte b = 100;
        short s = 10000;
        int i = 100000;
        long l = 10000000000L;
        float f = 3.14f;
        double d = 3.141592653589793;
        char c = 'A';
        boolean bool = true;
        print("Byte value: " + b, "Short value: " + s, "Int value: " + i, "Long value: " + l, "Float value: " + f, "Double value: " + d, "Char value: " + c, "Boolean value: " + bool);
    }
    public static void operators(){
        int a = 10;
        int b = 3;
        int sum = a + b;
        int diff = a - b;
        int prod = a * b;
        int div = a / b;
        int mod = a % b;
        print("Sum: " + sum, "Difference: " + diff, "Product: " + prod, "Integer Division: " + div, "Modulus: " + mod);
    }
    public static void controlFlow(){
        int num = 5;
        if(num > 0){
            print(num + " is positive");
        } else if(num < 0){
            print(num + " is negative");
        } else {
            print(num + " is zero");
        }
        for(int i = 1; i <= 5; i++){
            print("For Loop Iteration: " + i);
        }
        int count = 1;
        while(count <= 5){
            print("While Loop Count: " + count);
            count++;
        }
    }
    public static void methods(){
        int result = add(5, 10);
        print("Result of addition: " + result);
        int diff = subtract(10, 5);
        print("Result of subtraction: " + diff);
        int quotient = divide(10, 2);
        print("Result of division: " + quotient);
    }
    public static int add(int x, int y){
        return x + y;
    }
    public static int subtract(int x, int y){
        return x - y;
    }
    public static int divide(int x, int y){
        if(y != 0){
            return x / y;
        } else {
            print("Cannot divide by zero");
            return 0;
        }
    }
    public static void arrays(){
        int[] arr = {1, 2, 3, 4, 5};
        print("Array elements:");
        for(int i = 0; i < arr.length; i++){
            print(arr[i]);
        }
    }
    public static void matrix(){
        int [][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        print("Matrix elements:");
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                print(matrix[i][j]);
            }
        }
    }
}