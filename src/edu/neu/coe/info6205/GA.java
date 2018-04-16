/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.coe.info6205;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.logging.Logger;

/**
 *
 * @author liuzhaojie
 */
public class GA {

    int CNumb = 10;
    String[] TotalNumb = new String[CNumb];
    int Genercode = 0;
    static final int GENE = 46;
    double Bestfitness = Double.MAX_VALUE;
    int BestGenercode;
    String Beststrcode;
    double a[] = new double[CNumb];
    double b[] = new double[CNumb];
    double c[] = new double[CNumb];
    double F = 0;
    double x1;
    double x2;
    TreeMap<Double, String> all = new TreeMap();
    private static Logger log = Logger.getLogger(GA.class.getName());

    public String oneChr() {
        String Code = "";
        for (int i = 0; i < GENE; i++) {
            if (Math.random() > 0.5) {
                Code += "0";
            } else {
                Code += "1";
            }
        }
        return Code;
    }

    public String[] InitGroup() {

        String[] TotalNumb = new String[CNumb];
        for (int i = 0; i < CNumb; i++) {

            TotalNumb[i] = oneChr();
        }
        return TotalNumb;

    }

    public double[] TransCoding(String str) {

        int i = Integer.parseInt(str.substring(0, 23), 2);
        int j = Integer.parseInt(str.substring(23, 46), 2);

        x1 = i * (6.0 - 0) / (Math.pow(2, 23) - 1);
        x2 = j * (6.0 - 0) / (Math.pow(2, 23) - 1);

        double[] m = {x1, x2};
        return m;

    }

    public void sortfitness() {
        for (int i = 0; i < CNumb; i++) {
            a[i] = getfitness(TotalNumb[i]);
            all.put(a[i], TotalNumb[i]);
        }

        //System.out.println("the best fitness ：" + all.firstEntry());
    }

    public double getfitness(String str) {

        double[] x;
        x = TransCoding(str);
        // You can define functions that you want to optimize.
        // function of two variables
        //double fit = 3 - Math.sin(2 * x[0]) * Math.sin(2 * x[0])- Math.sin(2 * x[1]) * Math.sin(2 * x[1]);
        double fit = x[0]*x[0]*Math.sin(6*Math.PI*x[1])-3*x[1]*x[1]*Math.sin(2*Math.PI*x[0]);
        //double fit = 1+(x[0]*x[0]+x[1]*x[1]);
        //double fit = x[0]*x[0]*x[0]-x[1]*x[1]*x[1]+3*x[0]*x[0]+3*x[1]*x[1]-9*x[0]; 
        //double fit = 0.5 + ((Math.sin(Math.sqrt(x[0] * x[0] + x[1] * x[1]))) * (Math.sin(Math.sqrt(x[0] * x[0] + x[1] * x[1]))) - 0.5) / ((1 + 0.001 * (x[0] * x[0] + x[1] * x[1])) * (1 + 0.001 * (x[0] * x[0] + x[1] * x[1])));
        return fit;

    }

    public void getMinFitness() {

        for (int i = 0; i < CNumb; i++) {
            a[i] = getfitness(TotalNumb[i]);
            if (a[i] < Bestfitness) {
                Bestfitness = a[i];
                BestGenercode = Genercode;
                Beststrcode = TotalNumb[i];
            }

            F = F + a[i];
        }
        
    }

    public void choose() {
        getMinFitness();
        for (int i = 0; i < CNumb; i++) {
            b[i] = a[i] / F;
            if (i == 0) {
                c[i] = b[i];
            } else {
                c[i] = c[i - 1] + b[i];
            }
        }
        for (int i = 0; i < CNumb; i++) {
            double r = Math.random();
            if (r <= c[0]) {
                TotalNumb[i] = TotalNumb[0];
            } else {
                for (int j = 1; j < CNumb; j++) {
                    if (r < c[j]) {
                        TotalNumb[i] = TotalNumb[j];

                    }
                }
            }
            //log.info("Cumulative probability:"+c[i]);
        }
    }

    public void crossover() {
        String Crossover1, Crossover2;
        for (int i = 0; i < CNumb; i++) {
            if (Math.random() < 0.60) {
                int position = (int) (Math.random() * GENE) + 1;
                Crossover1 = TotalNumb[i].substring(0, position) + TotalNumb[(i + 1) % CNumb].substring(position);
                Crossover2 = TotalNumb[(i + 1) % CNumb].substring(0, position) + TotalNumb[i].substring(position);
                TotalNumb[i] = Crossover1;
                TotalNumb[(i + 1) / CNumb] = Crossover2;
                //log.info("No " + i + " chromosome"+"and No "+(i+1)+" chromosome " + "crossover position:" + position);
                
            }
            
        }
        
    }

    public void mutation() {
        for (int i = 0; i < 4; i++) {
            int number = (int) (Math.random() * GENE * CNumb + 1);
            int chromCode = (int) (number / GENE) + 1; 

            int mutaCode = number - (chromCode - 1) * GENE; 
            if (mutaCode == 0) {
                mutaCode = 1;
            }
            chromCode = chromCode - 1;
            if (chromCode >= CNumb) {
                chromCode = 9;
            }
            String chromo;
            String code;   
            if (TotalNumb[chromCode].charAt(mutaCode - 1) == '0') {    
                code = "1";
            } else {
                code = "0";
            }
           
            if (mutaCode == 1) {
                chromo = code + TotalNumb[chromCode].substring(mutaCode);
            } else {
                if (mutaCode != GENE) {
                    chromo = TotalNumb[chromCode].substring(0, mutaCode - 1) + code
                            + TotalNumb[chromCode].substring(mutaCode);
                } else {
                    chromo = TotalNumb[chromCode].substring(0, mutaCode - 1) + code;
                }
            }
           		
            TotalNumb[chromCode] = chromo;
            //log.info("No " + chromCode + " chromosome" + " " + "Mutations position:" + mutaCode);
        }
    }

}
