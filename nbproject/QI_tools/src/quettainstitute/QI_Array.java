package quettainstitute;


import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Java
 */
public class QI_Array {
        /**
         * Removes the empty elements from an array
         * @param v array to input
         * @return an array of output
         */
    public String[] removeEmptyElements(String[] v){
    int l=v.length; 
    int outputSize=0; 
        for (int i = 0; i < l; i++) {
            if(!v[i].isEmpty()){
                v[outputSize]=v[i]; 
                outputSize++; }
        }
    return Arrays.copyOfRange(v, 0, outputSize); 
    }
    /**
     * prints an array
     * @param ext extra text like ===================== before the array element
     * @param array array to be printed.
     */
public void print(Object[] array){
int l=array.length; 
    for (int i = 0; i < l; i++) {
        System.out.println(i+" = "+array[i]);
    }

}

/**
 * Concatenates all the elements into a single string . 
 * @param a the array to be concatenated. 
 * @param delimeter string to separate each element. 
 * @return single string containing all the elements of the array. 
 */
public String toStr(String[] a, String delimeter){
String tr=""; 
    int l=a.length; 
    for (int i = 0; i < l; i++) {
        tr+=a[i]; 
        if(i<l-1){
        tr+=delimeter; 
        }
    }


return tr; 
}

}
