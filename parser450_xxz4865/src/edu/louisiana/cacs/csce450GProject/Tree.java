/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.louisiana.cacs.csce450Project;


import edu.louisiana.cacs.csce450Project.*;
public class Tree {
    public Node root;
    
    public Tree()
    { root = null;}
    
    public Tree(Node n)
    { root = n;}
       
    public String inOrder(Node R)
    {
        String s = "";
        if (R != null)
        {             
            if (!R.isLeaf())               
                s += "["+R.nodeString+" "+inOrder(R.left)+" "+inOrder(R.middle)+" "+inOrder(R.right)+"]";
            /*else
            {
                if (R.nodeString.equals("*") || R.nodeString.equals("+"))
                    s = "]"+R.nodeString;
                else
                    s = R.nodeString; 
            }*/
            else
                s = R.nodeString;
        }  
       return s; 
    }
    
    public Node getRoot()
    { return root;}
    
    @Override
    public String toString()
    { return inOrder(root);}
}
