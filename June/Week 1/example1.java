class DivisionExample {
    public static void main(String[] args) {
        int a = 10;
        int b = 3;
        try {
            if (args.length >= 2) {
                try {
                    a = Integer.parseInt(args[0]);
                    b = Integer.parseInt(args[1]);
                } catch (NumberFormatException nfe) {
                    System.err.println("Invalid CLI arguments. Using defaults.");
                }
            }

            int sum = a + b;
            if (b == 0) {
                System.out.println("Cannot perform integer division by zero.");
            } else {
                int div = a / b;
                System.out.println("Integer Division: " + div);
            }

            double divExact = b == 0 ? Double.NaN : ((double) a / b);
            System.out.println("Sum: " + sum);
            System.out.println("Exact Division: " + divExact);
        } catch (Exception ex) {
            System.err.println("Unexpected error in DivisionExample: " + ex.getMessage());
        }
    }
}