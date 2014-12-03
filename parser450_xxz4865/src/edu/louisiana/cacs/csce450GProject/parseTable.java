/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.louisiana.cacs.csce450Project;
import edu.louisiana.cacs.csce450Project.*;
import java.util.*;
public class parseTable { 
    Map <String, Frame> table;
    
    public parseTable()
    { 
      table = new HashMap<String, Frame>();
      setTable();
    }
    
    private void setTable()
    {     
        table.put("id0", new Frame("S", 5));
        table.put("id4", new Frame("S", 5));
        table.put("id6", new Frame("S", 5));
        table.put("id7", new Frame("S", 5));
        table.put("+1",  new Frame("S", 6));
        table.put("+2",  new Frame("R", 2));
        table.put("+3",  new Frame("R",4));
        table.put("+5",  new Frame("R",6));
        table.put("+8",  new Frame("S",6));
        table.put("+9",  new Frame("R",1));
        table.put("+10", new Frame("R",3));
        table.put("+11", new Frame("R",5));
        table.put("*2",  new Frame("S",7));
        table.put("*3",  new Frame("R",4));
        table.put("*5",  new Frame("R",6));
        table.put("*9",  new Frame("S",7));
        table.put("*10", new Frame("R",3));
        table.put("*11", new Frame("R",5));
        table.put("(0",  new Frame("S",4));
        table.put("(4",  new Frame("S",4));
        table.put("(6",  new Frame("S",4));
        table.put("(7",  new Frame("S",4));
        table.put(")2",  new Frame("R",2));
        table.put(")3",  new Frame("R",4));
        table.put(")5",  new Frame("R",6));
        table.put(")8",  new Frame("S",11));
        table.put(")9",  new Frame("R",1));
        table.put(")10", new Frame("R",3));
        table.put(")11", new Frame("R",5));
        table.put("$1",  new Frame("A",0));
        table.put("$2",  new Frame("R",2));
        table.put("$3",  new Frame("R",4));
        table.put("$5",  new Frame("R",6));
        table.put("$9",  new Frame("R",1));
        table.put("$10", new Frame("R",3));
        table.put("$11", new Frame("R",5));
    }
    
    public HashMap getTable()
    { return (HashMap)table;}
}
