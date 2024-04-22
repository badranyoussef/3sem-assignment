package assignment2;

import java.util.Arrays;

public class FunctionalProgramming {

    public static int[] map(int[] a, MyTransformingType op){
        int[] result = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = op.tranformInteger(a[i]);
        }
        return result;
    }

    public static int[] filter(int[] a, MyValidatingType op){
        int[] result = new int[a.length];
        boolean valid = false;

        for (int i = 0; i < a.length; i++) {
            valid = op.validateDivision3(a[i]);
            if(valid){
                result[i]=a[i];
            }
        }
        return result;
    }
    //denne metode kunne også se således ud. hvor man benytter Java stream
    public static int[] filterWithStream(int[] a, MyValidatingType op) {
        return Arrays.stream(a)
                .filter(op::validateDivision3)
                .toArray();
    }

}
