/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.louisiana.cacs.csce450Project;

public class Node {
    public String nodeString;
    public Node left;
    public Node middle;
    public Node right;
    //public Boolean Leaf;
       
    public Node(String s)
    {
        nodeString = s;
        left = null;
        middle = null;
        right = null;       
    }
       
    public Boolean isLeaf()
    { 
        if (left == null && middle == null && right == null)
            return true;
        else 
            return false;
    }
}

