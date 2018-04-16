/*
 * Copyright (c) 2017. Phasmid Software
 */
package edu.neu.coe.test;

import edu.neu.coe.info6205.GA;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Test;





public class GATest {

    @Test
    public void test1() {
        GA ga = new GA();
        String x1=ga.oneChr();
        String x2 = ga.oneChr();
        Assert.assertNotNull(x1);
        Assert.assertNotNull(x2);
        Assert.assertFalse(x1.equals(x2));
        
    }

    @Test
    public void test2() {
        
        GA ga = new GA();
        String[] a = ga.InitGroup();
        for(int i=0; i<9;i++){
        Assert.assertNotNull(a[i]);
        Assert.assertFalse(a[i].equals(a[i+1]));
        }
    }
    
    @Test
    public void test3() {
        
        GA ga = new GA();
        String x1=ga.oneChr();
        String x2 = ga.oneChr();
        String a = Double.toString(ga.getfitness(x1));
        String b = Double.toString(ga.getfitness(x2));
        Assert.assertFalse(a.equals(b));  
    }
    
    
    
    
}
