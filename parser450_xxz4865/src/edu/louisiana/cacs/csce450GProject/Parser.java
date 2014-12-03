package edu.louisiana.cacs.csce450Project;




import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class Parser{

    private ArrayList<LinkedList<String>> StringList;

public Parser(String fileName)
        
{
    StringList = new ArrayList<LinkedList<String>>();
    File file = new File(fileName);
    String line;
    try
            {
	       FileReader fileRead = new FileReader(file);
               BufferedReader reader = new BufferedReader(fileRead);
               line = reader.readLine();
               while (line != null){                  
                   LinkedList<String> inString = new LinkedList<String>();//a queue of string
                   inString.add(line);   //add a line read from the file in the queue              
                   StringList.add(inString);    // add the queue in the queue array           
                   line = reader.readLine();// read a another line from the file
            }
              fileRead.close();
            }
            catch (IOException e)
            {
                System.out.println(e);
            }
     System.out.println("File to parse : "+fileName);
}
	
					
									
public void parse() {

        Frame initStatus = new Frame("", 0);//needed for initial output    
        Stack<Frame> status = new Stack<Frame>();
        parseTable PTable = new parseTable(); 
        HashMap Action = PTable.getTable(); //* parsing table
        GotoTable Transfer = new GotoTable();
        HashMap<String, Integer>  Goto = Transfer.getMap();//*Goto table 
        Expressions epr = new Expressions();
        String[][] gramTable = epr.getExp();//* grammar expression table
        //ArrayList<LinkedList<String>> StringList = getFile();//* input queue 
        ArrayList<LinkedList<String>> inputStr = new ArrayList<LinkedList<String>>();
        
        
        for (LinkedList<String> s: StringList){//put each line/queue to the scanner
            TokenScanner scaner = new TokenScanner();//for each line in the file make a scanner
            inputStr.add(scaner.getTokens(s)); //break the queue into tokens and put back to another queue
        }                                      // lastly put the queue of tokens into the queue array
        
        for (LinkedList<String> inputString: inputStr)
        {               
            if (status.size() > 0)//for each input make sure status starts from scratch
                status.clear();          
            status.push(initStatus);//add initial state frame
            Boolean stop = false;
            outPut(status, inputString, stop, Action, Goto, gramTable);
            System.out.println();
        }
    }
    
    
    public void outPut(Stack<Frame> status, LinkedList<String> inputString, Boolean stop, HashMap Action, HashMap Goto, String[][] gramTable)
            //throws NullPointerException
    {   
        Stack<Tree> treeStack = new Stack<Tree>();//needed for printing trees in the stack

        if(inputString.size()>0)
	{
        	System.out.printf("%-20s%-18s%-10s%-8s%-8s%-8s%-15s%-10s%-9s%-14s\n", " ", "Input", "Action","Action", "Value", "Value", "Temp", "Goto", "Goto","Stack", " ");
       	 	System.out.printf("%-20s%-18s%-10s%-8s%-8s%-8s%-15s%-10s%-9s%-14s", "Stack", "tokens", "lookup","value", "of LHS", "of RHS", "stack", "lookup", "value","action");
        	System.out.print("Parse tree stack\n");
        	System.out.println("_____________________________________________________________________________________________________________________________________________");
        }  
   
        while (inputString.size() > 0 && !stop)
        {
            String stateStr = "";
            String strInQ = "";           
            Frame statFrame = status.peek();//set a pointer to the first frame in status
            String retrievedString = inputString.peek();//find the first token in the queue
            String lookUpString = retrievedString+statFrame.getIndex();//make a frame 
            Frame  lookUpFrame = (Frame)Action.get(lookUpString);// get a rule frame(e.g. S5)
            try{
            if(lookUpFrame.getSymbol().equals("A")) 
            {                
                stop = true;
                for (Frame f: status)
                    stateStr += f;// needed for printing status               
                String ss = inputString.pop();
                System.out.printf("%-20s%-18s%-10s%-8s", stateStr, ss,"[ "+statFrame.getIndex()+", "+retrievedString+"]", "Accept" );
                System.out.printf("%-8s%-8s%-15s%-10s%-9s%-14s", " "," "," "," "," "," ");
                Object[] objArray = treeStack.toArray();
                for (int j = objArray.length -1; j >= 0; j--)             
                    System.out.print((Tree)objArray[j]);
                System.out.println();
                printParseTree(treeStack.pop().root);
                
            }       
            else if(lookUpFrame.getSymbol().equals("S")) 
            {
                for (Frame f: status)
                    stateStr += f;// needed for printing status
                for (String s: inputString)
                    strInQ += s;//needed for printing tokens left in the input string
                Frame inStat = new Frame(retrievedString, lookUpFrame.getIndex());//e.g. id5
        
                status.push(inStat);// push a new frame in status such as id5
                if(!inStat.getSymbol().equals("*")&&!inStat.getSymbol().equals("+"))
                {
                    Tree t = new Tree(new Node(inStat.getSymbol()));
                    fillStack(treeStack,t,1);
                }
                inputString.pop();//take one out of the input string e.g id
                System.out.printf("%-20s%-18s%-10s%-8s", stateStr, strInQ, "[ "+statFrame.getIndex()+", "+retrievedString+"]", lookUpFrame);
                System.out.printf("%-8s%-8s%-15s%-10s%-9s%-14s", " ", " ", " ", " ", " ", "push "+inStat);
                Object[] objArray = treeStack.toArray();
                for (int j = objArray.length -1; j >= 0; j--)             
                    System.out.print((Tree)objArray[j]);
                System.out.println();  
            }
            else if(lookUpFrame.getSymbol().equals("R")) 
            {
                for (Frame f: status)// needed for printing status
                        stateStr += f;
                for (String s: inputString)
                        strInQ += s;// needed for printing input string
                System.out.printf("%-20s%-18s%-10s",stateStr,strInQ,"[ "+statFrame.getIndex()+", "+retrievedString+"]");
                String frameString = gramTable[lookUpFrame.getIndex()-1][0];//needed to see how may frame to pop
                for (int i = 1; i < gramTable[lookUpFrame.getIndex()-1].length; i++)
                {                   
                    Frame tempf = status.pop();                   
                    if(tempf.getSymbol().equals("*")||tempf.getSymbol().equals("+"))
                    {
                        Tree t = new Tree(new Node(tempf.getSymbol()));
                        fillStack(treeStack, t, 1);
                    }
                }
                
                
                stateStr = "";
                for (Frame f: status)// needed for printing status
                        stateStr += f;
                
                System.out.printf("%-8s",lookUpFrame);
                Integer tempIndex = status.peek().getIndex();// needed for look up Goto table
                int lookUpIndex = tempIndex; // needed for output format
                System.out.printf("%-8s%-8s", frameString, gramTable[lookUpFrame.getIndex()-1].length-1);
                String tempS = frameString + tempIndex;
                tempIndex = (Integer)Goto.get(tempS);// find the index to be pushed in status
                
                System.out.printf("%-15s%-10s%-9s%-14s", stateStr, "["+lookUpIndex+","+ frameString+"]", tempIndex, "push"+" "+frameString+tempIndex);

                Frame f = new Frame(frameString, tempIndex);
                Tree tIn = new Tree(new Node(f.getSymbol()));
                fillStack(treeStack,tIn,gramTable[lookUpFrame.getIndex()-1].length-1 );
                status.push(f);
                Object[] objArray = treeStack.toArray();
                for (int j = objArray.length -1; j >= 0; j--)             
                    System.out.print((Tree)objArray[j]);
                System.out.println();
            }           
            else
            {
                stop = true;
                for (Frame f: status)
                    stateStr += f;// needed for printing status               
                String ss = inputString.pop();
                System.out.printf("%-20s%-18s%-10s%-8s", stateStr, ss,"[ "+statFrame.getIndex()+", "+retrievedString+"]", "UNGRAMMATICAL" );
            }
            
          }
          catch (NullPointerException e)
          {
              stop = true;
              for (Frame f: status)
                    stateStr += f;// needed for printing status               
                String ss = inputString.pop();
                System.out.printf("%-20s%-18s%-10s%-8s", stateStr, ss,"[ "+statFrame.getIndex()+", "+retrievedString+"]", "UNGRAMMATICAL" );
          }
        }
    }
    

    /*public ArrayList<LinkedList<String>> getFile() throws IOException
    {
        ArrayList<LinkedList<String>> ls = new ArrayList<LinkedList<String>>();
        Scanner input = new Scanner(System.in);
        System.out.print("Please input a filename or path to the file: ");
        String filename = input.next();
        File file = new File(filename);
        String line;
        if (!file.exists())
            System.out.print("file does not exist.");
        else{           
            try
            {
	       FileReader fileRead = new FileReader(file);
               BufferedReader reader = new BufferedReader(fileRead);
               line = reader.readLine();
               while (line != null){                  
                   LinkedList<String> inString = new LinkedList<String>();//a queue of string
                   inString.add(line);   //add a line read from the file in the queue              
                   ls.add(inString);    // add the queue in the queue array           
                   line = reader.readLine();// read a another line from the file
               }
              fileRead.close();
            }
            catch (IOException e)
            {
                System.out.println(e);
            }
        }
        return ls;// returns the array of queues of strings
    }*/

    
    public void fillStack(Stack<Tree> treeStack, Tree t, int number)
    {   
        if (t.root.nodeString.equals("id")||t.root.nodeString.equals("("))
            treeStack.push(t);   
        else if (t.root.nodeString.equals("*") || t.root.nodeString.equals("+"))
        {   
            Tree t1 = treeStack.pop();
            Tree t2 = treeStack.pop();           
            treeStack.push(t1);
            treeStack.push(t);
            treeStack.push(t2);           
        }
        else if (number == 3)
        {
            Tree t1 = treeStack.pop();
            Tree t2 = treeStack.pop();
            Tree t3 = treeStack.pop();
            t.root.left = t1.root;
            t.root.middle = t2.root;
            t.root.right = t3.root;
            treeStack.push(t);
        }
        else if(t.root.nodeString.equals(")"))
        {
            Tree t1 = treeStack.pop();
            Tree t2 = treeStack.pop();
            treeStack.push(t);
            treeStack.push(t1);
            treeStack.push(t2);            
        }
        else 
        {   
            Tree t1 = treeStack.pop();
            t.root.middle = t1.root;
            treeStack.push(t);                
        }
    } 
       
       
    public void printParseTree(Node R)
    {
	System.out.println("Hello World from " + getClass().getName());

	printHelp(R, 0);
    }

    
    public void printHelp(Node r, int indent)
    {       
       if(r != null)
       { 
           for(int i = 0; i< indent; i++)
               System.out.print("\t");        
        System.out.println(r.nodeString);
        indent++;
        printHelp(r.left, indent);
        printHelp(r.middle, indent);
        printHelp(r.right, indent);
       }
    }   


}





