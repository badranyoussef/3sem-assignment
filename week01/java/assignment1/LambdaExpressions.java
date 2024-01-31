package assignment1;

public class LambdaExpressions {

    public static void additionOp(){
        ArithmeticOperation addition = (int a, int b) -> a + b;
    }

    public static ArithmeticOperation addition(){
        ArithmeticOperation addition = (int a, int b) -> a + b;
        return addition;
    }

    public static ArithmeticOperation subtraction(){
        ArithmeticOperation subtraction = (int a, int b) -> a - b;
        return subtraction;
    }

    public static ArithmeticOperation multiplication(){
        ArithmeticOperation multiplication = (int a, int b) -> a * b;
        return multiplication;
    }

    public static ArithmeticOperation division(){
        ArithmeticOperation division = (int a, int b) -> a / b;
        return division;
    }

    public static ArithmeticOperation modulus(){
        ArithmeticOperation modulus = (int a, int b) -> a % b;
        return modulus;
    }

    public static ArithmeticOperation power() {
        ArithmeticOperation power = (int a, int b) -> (int) Math.pow(a, b);
        return power;
    }


    public static int[] adttitionOfArrays(int[] a, int[] b, ArithmeticOperation op){
        int[] newArray = new int[a.length];

        for (int i = 0; i < a.length; i++) {
            newArray[i] = op.perform(a[i], b[i]);
        }
        return newArray;
    }

}
