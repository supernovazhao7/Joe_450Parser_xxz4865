/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.louisiana.cacs.csce450Project;

//import java.io.File;
//import java.io.FileReader;
//import java.io.BufferedReader;
import java.util.*; 
//import Frame.*;
public class TokenScanner {
       
       int index = 0;
       char[] outchar;
       int charClass;
       char [] lexeme = new char[1000];
       char nextChar;
       int lexLen;
       int token;
       private int nextToken;
       int frameIndex = 0; 
       
       final int Letter = 0;
       final int Digit = 1;
       final int Unknown = 99;
        
       final int Int_lit = 10;
       final int Ident = 11;
       final int Assign_op = 20;
       final int Add_op = 21;
       final int Sub_op = 22;
       final int Mult_op = 23;
       final int Div_op = 24;
       final int Left_paren = 25;
       final int Right_paren = 26;
       final int EOF = -1;     
       LinkedList<String> inputString;
       LinkedList<String> tempList;
       
    public TokenScanner()
    { 
        inputString = new LinkedList();
        tempList = new LinkedList<String>();     
    }
        
    public void Tokenize(LinkedList<String> str){
           
        outchar = toChars(str);
        getChar(outchar);
        do
        {
            lex(outchar, tempList);
        }while (nextToken != EOF);       
    }
    
    public LinkedList<String> getTokens(LinkedList<String> expStr)
    {      
       Tokenize(expStr);
       return inputString;
    }
    

    public char[] toChars(LinkedList<String> ls)
    {
        char[] temp;
        String lines="";
        for (String l : ls) 
            lines += l;            
        temp = lines.toCharArray();
        return temp;
    }
    
    public void getChar(char[] ca)
    {
        if (index < ca.length)
        {
            nextChar = ca[index++];
            if (Character.isLetter(nextChar))
                charClass = Letter;               
            else if (Character.isDigit(nextChar))
                charClass = Digit;
            else
                charClass = Unknown;
        }
        else
            charClass = EOF;
    }
    
    public void addChar()
    {       
        lexeme[lexLen++]=nextChar;
        lexeme[lexLen]=0;    
    }
    
    public void getNonBlank(char[] ca)
    {
        while (Character.isWhitespace(nextChar))
            getChar(ca);
    }
    
    public  int lookUp(char ch, int nextToken)
    {
        switch(ch)
        {
            case '(':
                addChar();
                nextToken = Left_paren;
                break;
            case ')':
                addChar();
                nextToken = Right_paren;
                break;
            case '+':
                addChar();
                nextToken = Add_op;
                break;
            case '-':
                addChar();
                nextToken = Sub_op;
                break;
            case '*':
                addChar();
                nextToken = Mult_op;
                break;
            case '/':
                addChar();
                nextToken = Div_op;
                break;
            default:
                addChar();
                nextToken = EOF;
                break;
        }
        return nextToken;                 
    }
    
    public  void lex(char[] ca, LinkedList<String> tempList)
    {
        String lexterm = "";
        lexLen = 0;
        getNonBlank(ca);
        switch(charClass)
        {
            case Letter:
                addChar();
                getChar(ca);
                while(charClass == Letter || charClass == Digit)
                {
                    addChar();
                    getChar(ca);
                }
                nextToken = Ident;
                break;
            case Digit:
                addChar();
                getChar(ca);
                while (charClass == Digit)
                {
                    addChar();
                    getChar(ca);
                }
                nextToken = Int_lit;
                break;
            case Unknown:
                lookUp(nextChar, nextToken);
                getChar(ca);
                break;
            case EOF:
                nextToken = EOF;
                lexeme[0]= 'E';
                lexeme[1]= 'O';
                lexeme[2]= 'F';
                lexeme[3]= 0;  
        }
        
        for (char c : lexeme)         
           if (c != ' ')
               lexterm += c; 
        if (!lexterm.trim().equals("EOF"))
            inputString.offer(lexterm.trim());
        lexeme = null;
        lexeme = new char[1000];
        //System.out.println("lexterm is " + lexterm);
    }
}
