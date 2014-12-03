/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.louisiana.cacs.csce450Project;

public class Frame {
    private String symbol;
    private int  index;
    public Frame()
    { this(" ", -1);}
    
    public Frame(String sym, int n)
    { symbol = sym; index = n;}
    
    public String getSymbol()
    { return symbol;}
    
    public void setSymbol(String s)
    { symbol = s;}
    
    public int getIndex()
    { return index;}
    
    public void setIndex(int N)
    { index = N;}
    
    @Override
    public String toString()
    { 
        String s;
        s = symbol + index;
        return s;
    }  
}
