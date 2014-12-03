/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.louisiana.cacs.csce450Project;

public class Expressions {
    private final String[][] express = new String[6][];
    public Expressions()
    {
        express[0] = new String[] {"E","E","+","T"};
        express[1] = new String[] {"E","T"};
        express[2] = new String[] {"T","T","*","F"};
        express[3] = new String[] {"T","F"};
        express[4] = new String[] {"F","(","E",")"};
        express[5] = new String[] {"F","id"};        
    }
    
    public String getCode(int i, int j)
    {return express[i][j];}
    
    /**
     *
     * @return
     */
    public String[][] getExp()
    { return express;}
}
