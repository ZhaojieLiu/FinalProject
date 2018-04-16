/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.coe.info6205;



/**
 *
 * @author liuzhaojie
 */
public class Main {
    private static final int GENERATION=2000;
    
    public static void main(String args[]) {
                
                
		GA ga = new GA();
		ga.TotalNumb = ga.InitGroup();
                //System.out.println("Initial Population:");
                for(int i=0;i<ga.CNumb;i++){
                    //System.out.println(ga.TotalNumb[i]);
                }
		String str = "";
		
		
		for (int i = 0; i < GENERATION; i++) {
			ga.choose();
			ga.crossover();
			ga.mutation();
			ga.Genercode = i;
                        //System.out.println("No "+(i+1)+" Generation");
                        //System.out.println(i +" "+ga.Bestfitness);
                        double[] x = ga.TransCoding(ga.Beststrcode);
                        
                        str = "Iterations: "+i+'\n' +"min: "+ ga.Bestfitness + '\n' + "No "+ ga.BestGenercode + " chromosome:<" + ga.Beststrcode + ">" + '\n' + "x=" + x[0] + '\n' + "y=" + x[1];

		        System.out.println(str);
                        System.out.println(" ");
                        
		}
		
		double[] x = ga.TransCoding(ga.Beststrcode);
                
                //System.out.println("Min:" + ga.Bestfitness);
		str = "Min:" + ga.Bestfitness + '\n' + "No "+ ga.BestGenercode + " chromosome:<" + ga.Beststrcode + ">" + '\n' + "x=" + x[0] + '\n' + "y=" + x[1];
                System.out.println("The final result:");
		System.out.println(str);
                

	}
}
