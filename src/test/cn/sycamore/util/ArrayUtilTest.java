package cn.sycamore.util;

import static org.junit.Assert.*;

import org.junit.Test;


public class ArrayUtilTest {

    @Test
    public void testdoubleBitCount(){
        int size = 1000000;
        double[] arr = new double[size];
        for (int i = 0; i < size; i++) {
            arr[i] = ((double) 1)/(i+1);
        }
        Integer[] result = ArrayUtil.doubleBitCount(arr);
        System.out.println(result);
    }

}
