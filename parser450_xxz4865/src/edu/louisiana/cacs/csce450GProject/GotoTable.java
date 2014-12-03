/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.louisiana.cacs.csce450Project;
import edu.louisiana.cacs.csce450Project.*;
import java.util.*;
public class GotoTable {
    Map<String,Integer> GTable;

    public GotoTable() 
    { this.GTable = new HashMap<String, Integer>();}
    
    public HashMap<String, Integer> getMap()
    {
        Frame[][] f = new Frame[12][3];   
        for (int i = 0; i < 12; i++)
        {
            int j = 0;
            if (i == 0)
            {
                f[i][j] = new Frame("E",i);
                GTable.put(f[i][j++].toString(),1);
                f[i][j] = new Frame("T",i);
                GTable.put(f[i][j++].toString(),2);
                f[i][j] = new Frame("F",i);
                GTable.put(f[i][j].toString(), 3);
            }
            else if (i == 4){
                f[i][j] = new Frame("E",i);
                GTable.put(f[i][j++].toString(),8);
                f[i][j] = new Frame("T",i);
                GTable.put(f[i][j++].toString(),2);
                f[i][j] = new Frame("F",i);
                GTable.put(f[i][j].toString(), 3);
            }
            else if (i == 6){
                f[i][j] = new Frame("E", i);
                GTable.put(f[i][j++].toString(), 0);
                f[i][j] = new Frame("T",i);
                GTable.put(f[i][j++].toString(),9);
                f[i][j] = new Frame("F",i);
                GTable.put(f[i][j].toString(), 3);
            }
            else if (i == 7)
            {
                f[i][j] = new Frame("E", i);
                GTable.put(f[i][j++].toString(), 0);
                f[i][j] = new Frame("T",i);
                GTable.put(f[i][j++].toString(),0);
                f[i][j] = new Frame("F",i);
                GTable.put(f[i][j].toString(), 10);               
            }
            else
            {
                f[i][j] = new Frame("E", i);
                GTable.put(f[i][j++].toString(), 0);
                f[i][j] = new Frame("T",i);
                GTable.put(f[i][j++].toString(),0);
                f[i][j] = new Frame("F",i);
                GTable.put(f[i][j].toString(), 0);  
            }           
        } 
        return (HashMap)GTable;
    }
    
    public void showMap()
    {
        Iterator it = GTable.entrySet().iterator();
        while (it.hasNext()) {
        Map.Entry pairs = (Map.Entry)it.next();
        System.out.println(pairs.getKey() + " = " + pairs.getValue());
        it.remove();
        }
    }   
}
