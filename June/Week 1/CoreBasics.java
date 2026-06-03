class CoreBasics {
    public static void main(String[] args) {
        demonstrateVariables();
        demonstrateOperators();
        demonstrateControlFlow();
        demonstrateMethods();
        demonstrateArrays();
        demonstrateMatrix();
    }

    public static void print(Object... args) {
        for (Object arg : args) {
            System.out.println(arg);
        }
    }

    public static void demonstrateVariables() {
        try {
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
        print(
            "Byte value: " + b,
            "Short value: " + s,
            "Int value: " + i,
            "Long value: " + l,
            "Float value: " + f,
            "Double value: " + d,
            "Char value: " + c,
            "Boolean value: " + bool
        );
        } catch (Exception ex) {
            System.err.println("Error in demonstrateVariables: " + ex.getMessage());
        }
    }

    public static void demonstrateOperators() {
        try {
            int a = 10;
            int b = 3;
            int sum = a + b;
            int diff = a - b;
            int prod = a * b;
            int div = a / b;
            int mod = a % b;
            print(
                "Sum: " + sum,
                "Difference: " + diff,
                "Product: " + prod,
                "Integer Division: " + div,
                "Modulus: " + mod
            );
        } catch (ArithmeticException ae) {
            System.err.println("Arithmetic error in demonstrateOperators: " + ae.getMessage());
        } catch (Exception ex) {
            System.err.println("Error in demonstrateOperators: " + ex.getMessage());
        }
    }

    public static void demonstrateControlFlow() {
        try {
            int num = 5;
            if (num > 0) {
                print(num + " is positive");
            } else if (num < 0) {
                print(num + " is negative");
            } else {
                print(num + " is zero");
            }

            for (int index = 1; index <= 5; index++) {
                print("For Loop Iteration: " + index);
            }

            int count = 1;
            while (count <= 5) {
                print("While Loop Count: " + count);
                count++;
            }
        } catch (Exception ex) {
            System.err.println("Error in demonstrateControlFlow: " + ex.getMessage());
        }
    }

    public static void demonstrateMethods() {
        try {
            int result = add(5, 10);
            print("Result of addition: " + result);
            int diff = subtract(10, 5);
            print("Result of subtraction: " + diff);
            int quotient = divide(10, 2);
            print("Result of division: " + quotient);
        } catch (Exception ex) {
            System.err.println("Error in demonstrateMethods: " + ex.getMessage());
        }
    }

    public static int add(int x, int y) {
        return x + y;
    }

    public static int subtract(int x, int y) {
        return x - y;
    }

    public static int divide(int x, int y) {
        if (y != 0) {
            return x / y;
        }
        print("Cannot divide by zero");
        return 0;
    }

    public static void demonstrateArrays() {
        int[] arr = {1, 2, 3, 4, 5};
        print("Array elements:");
        for (int index = 0; index < arr.length; index++) {
            print(arr[index]);
        }
    }

    public static void demonstrateMatrix() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        print("Matrix elements:");
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[row].length; column++) {
                print(matrix[row][column]);
            }
        }
    }
}