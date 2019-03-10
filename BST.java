/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3v1;

/**
 *
 * @author MinhajShah
 */


/*BSTSet is the class which creates the tree. It contains side methods and
sorting algorithims to achieve the balance of the tree needed on the left
and right branches of the root. This was required for the bonus portion of
this Lab. */


public class BSTSet {
    TNode root;
    public BSTSet() {
		root = null;
	}
//RECRUSIVE IMPLEMENTATION
    //The algorithim used in this implementation garauntees MINIMUM height of tree
    
    public BSTSet(int[] input){//
        input=bubble_sort(input);//sorts array and removes duplicates
        root = ZigZag(input,0,input.length-1);//calls recursive function to generate bst
    }
    
//METHOD BUBBLE_SORT
    //used in the constructor for the BST. Called in method ZigZag which will
    //explained below. Bubbeloop uses the bubble sort algorithim integrated in
    //a looping structure to allow the contents of the array given as a parameter
    //to be put in order efficently. Duplicates are also removed in the process
    //which utilizes the looping structure after the sorting.
    //It returns the array back which is then 
    //used in ZigZag to create the set.
    
        public int[] bubble_sort(int[] input){
        int temp;
//process of algorithim consists of tempping conesecutive pairs
//of elements if they are out of order, meaning left element >right
//temp variable used to hold the swapped value temporarily
        
        for(int i=0;i<input.length;i++){
//outter loop runs through entire array which calls inner loop for 
//every iteration
            for(int j=1;j<(input.length-i);j++){
//Inner loop goes through comparing consecutive elements to be swapped 
                if(input[j-1]>input[j]){
                    temp=input[j-1];
                    input[j-1]=input[j];
                    input[j]=temp;
                }   
            }
        }
//this portion of method deals with duplicates provided in the array and eliminates them
//algorithim begins by counting the number of dupliates seen in the array which are next to each other

        int dups=0;
        for(int i=0;i<input.length;i++){
//outter loop iterates through whole array
            while(i<input.length-1){
//inner loop ran for every iteration of outter similar to bubblesort algorithim
                if(input[i]==input[i+1]){
//checks to see if elements consecutive are the same, if so increment dups
                    dups++;
                    i++;
                }
                else{break;}
            }
        }
//array now must remove all duplicate values that were dtermined by dups counter
        int[] nodups = new int[input.length-dups];
//near array instantiated to teh size of the original minus the duplicates that
//were counted to ensure the correct size of the array.
        int j=0;
        for(int i=0;i<input.length-dups;i++){
//outter loop iterates through entire array
            nodups[i]=input[j];
            j++;//j is now advanced 1 
            while(j<input.length-1 && input[j]==input[j+1])
//inner loop only iterates if current element and next are the same
//if this is true than the second element is not copied into the array so j incremented
                j++;
        }  
        return nodups;
    }
//ZIGZAG METHOD
        //Algorithm to maintain balance of left and right children off root
        //and to snsure minimum height for every tree instantiated
    public TNode ZigZag(int[] input, int left, int right){
        //BASE CASE!
        if(left>right){
            return null;
        }
        
  
//Algorithim comprised of zigzag approach which consists of placing nodes
//immediatley left then right in that order. Visually this can be seen as 
//flattening out the tree ans stretching it horizontally which ensures min height
//Starts from middle and grabs left and right elements in order incrementing on either side
//Recursive calls to this method split left portion and right over and over again 
//to achieve this while being called in constructor
        
        int center=left + (right-left)/2;
        TNode p=new TNode(input[center],null,null);
//center calculated and Node instantiated to hold at this element as the root
        p.left=ZigZag(input,left,center-1);
//ZigZag called for left. 
        p.right=ZigZag(input,center+1,right);
        return p;
    }
    

    public boolean isIn(int v) {
        if(root == null)return false;
        TNode current = root;
        while(true) {
        if(current.element == v) {
                return true;
        }else {
            if(v > current.element) {
                    if(current.right == null)
                        return false;
                    else
                        current = current.right;
            }
            if(v < current.element) {
                    if(current.left == null)
                            return false;
                    else
                        current = current.left;
                }
            }
        }
    }
    public void add(int v) {
        if(root == null) {
                root = new TNode(v,null,null);
//if empty tree then instantiate new tree with added element as root
        }else {
            TNode current = root;
//loop runs if above doesnt break
            while(true) {
                if(v == current.element ) {
                        break;
//if added item is the same as the root then exit
                }

            if(v < current.element ) {
//left side of tree
                if(current.left == null) {
                        current.left = new TNode(v,null,null);
                        break;
//if root in tree alone then we simply add a new child 
                }else {
                        current = current.left;
//iterate through left branch of tree
                }


                }

            if(v > current.element ) {
                if(current.right == null) {
                        current.right = new TNode(v,null,null);
                        break;
//alone root create new node on right
                }else {
                        current = current.right;
//iterate through right                        
                }


                }
            }
        }


}
    public boolean remove(int v) {
		TNode current = root;
		TNode parent = root;
		
		boolean onleft = true;
//assumption of wanted element on left side of tree
		while(current.element != v) {
//while loop iterates through tree looking for element in orderly fashion
//since the tree is sorted
			parent = current;
//holds the parent node of the one to be deleted. updates every iteration
			
			if(v < current.element) { // left
				onleft = true;
				current = current.left;
//check for left side then iterate left
			}else {						
				onleft = false;
//if not on left then must be on right side of tree, so iterate right				
				current = current.right;
			}
//if iteration through whole tree then we will fall off it without finding
			if(current == null)
				return false;
		}
//if we are here than we have found the node to be removed based on the tracking

                
                
		if(current.left == null && current.right == null) {
//CASE 1: no children on the target node so we simply set its pointer to null 
			if(current == root)
				root = null;
//if the node to be removed is the root we simply set to null 
			else if(onleft)
                            parent.left = null; 
			else 
                            parent.right = null;
//pointer no longer points to element to be removed
		}
                
//CASE 2: no right child on element to be deleted
	
		else if(current.right == null) {
//if no right node only then it will be null
                    if(current == root) {
                            root = current.left;
//this rellocates the current root if its the one to be removed. It is put on left 
//because its the next child on left since nothings on right
                    }
                    else if(onleft)
                        parent.left = current.left;
//this executes if the current node is on the left which is the boolean defined
//during tracking. If so its parent branch is set the to be removed branches child                            
                    else
                        parent.right = current.left;
//same but opposite for right                    
		}
//CASE 3: no left child on element to be deleted
                
//same processs as no right child but opposite
		else if(current.left == null) {
			if(current == root) 
				root = current.right;
			
			else if(onleft) 
                            parent.left = current.right;
			else 
                            parent.right = current.right;
			
		}
//CASE 4: children on both left and right of element to be deleted
//now we must find the smallest element in the right subtree to replace the node with
//there are alternative solutions oopposite to this one. Doesnt matter which is implemented
                
                else if(current.left!=null && current.right!=null){
                TNode s=current.right;
//acces the right subtree
                while(s.left!=null)
                    s=s.left;
//iterates through the entire left branch to find the smallest element                
                current=new TNode(s.element,current.left,current.right.right);
                
//resets current node to a new node and changes left and right children appropriatley
	}
                return true;
    }
    private int size(TNode start)
    {
        if (start == null)
            return 0;
        else
            return(size(start.left) + 1 + size(start.right));
    }
//private method to get the length from a root
    public int size()
    {
        return size(root);
    }
//public calls private
    private int height(TNode top) 
    {
        if(top == null) {
                return -1;
        }else {
                return max(height(top.left),height(top.right)) + 1;
        }
		
    }
//private method to return the height. makes calls to max which is explained below
    public int height() 
    {
	return height(root);
    }
//public calls private hieght
    private int max(int a, int b) 
    {
        return (a>b)? a:b;
     
    }
//max function returns longest path
    private void printBSTSet(TNode t) 
    {
        if(t != null) {
                printBSTSet(t.left);
                System.out.print(" " + t.element + ", ");
                printBSTSet(t.right);
        }
//recursive method for printing. private method

    }
    public void printBSTSet() {
        if(root==null)
                System.out.println("The set is empty");
        else{
                System.out.print("The set elements are: ");
                printBSTSet(root);
                System.out.print("\n");

        }
    }
//publicm ethod calls private printer

    
//NON RECURSIVE PRINTING METHOD BELOW
    //uses Stackarr which creates stack rep of tree available for use
    public void printNonRec() 
    {
        Stackarr<TNode> stack = new Stackarr<TNode>(size());

        TNode top = root;
//top of stack pointed to at all times. push pop off top cus LIFO
        if(root != null) {
                System.out.print("The elements are: ");
                while(true) {
                    if(top != null) {
                            stack.push(top);
                            top = top.left;
                    }
//Stack is loaded with tree elements by pushing and incrementing pointer vairable

                    if(top == null && stack.isEmpty() == false) {
                            top = stack.peek().right;
                            System.out.print(stack.pop().element + ",");
//once the top is reached we enter the above if statement which exectues till empty
//elements are seen using peek which returns the element
//then printed out and popped
                    }
                    if(top == null && stack.isEmpty() == true)break;
            }
        }else {
                System.out.println("The tree is empty");
        }
    }
    //In below methods a = the bigger tree and b = the smaller tree
    public BSTSet intersection(BSTSet s) {
		BSTSet a ; 
		BSTSet b  ; 
//intersection trees intialied
		BSTSet temp = new BSTSet(); 
		if(s.size() > this.size()) {
			a = new BSTSet();
			cumulator(s.root, a);
			b = this;
		}else {
			a = new BSTSet();
			cumulator(root, a);
			b = s;
//cumulation proces invoked above
                } 
		
		locate(b.root,a,temp);
		return temp;
	}
	private void locate(TNode top,BSTSet b,BSTSet temp) {
        if(top != null) {

                if(top.left != null) 	
                        locate(top.left,b,temp);

                if(top.right != null)
                        locate(top.right,b,temp);

                if(b.isIn(top.element) == true && temp.isIn(top.element) == false) 
                        temp.add(top.element);

        }
}
        
        private void cumulator(TNode start,BSTSet temp) {
            if(start != null) {
                    if(start.left != null)
                             cumulator(start.left,temp);
                    if(start.right != null)
                             cumulator(start.right,temp);

                    temp.add(start.element);

            }

		
	}
        
      public BSTSet union(BSTSet s) {
            BSTSet a; 
            BSTSet b; 
//sizes check for traversal
            if(s.size() > this.size()) {
                    a = new BSTSet();
                    cumulator(s.root, a);
                    b = this;
//cumulation process invoked again for both comparotor statements
            }else {
                    a = new BSTSet();
                    cumulator(root, a);
                    b = s;
            }
            TNode current = b.root;
            cumulator(current,a);
            return a;
    }
      
      
      public BSTSet difference(BSTSet s){//this - s = unique to this
          BSTSet diff;
          BSTSet inter = s.intersection(this);
          BSTSet uni = s.union(this);
          int [] hold = new int [inter.size()];
 //the difference method looks fairly complex with each of the calls
 //to the other operators but it can be done in an easy way
 //with the use of the other functions designed before it
          //The algorithim consists of two steps. The first being is finding
          //the reverse intersection of A and B. what this means is that all the elements
          //that are different are removed from the new set. 
          //then this new set is simply intersected with A to get A - B. 
       Stackarr<TNode> stack = new Stackarr<TNode>(size());

        TNode top = inter.root;
        if(root != null) {
                int x=0;
                while(true) {
                    if(top != null) {
                            stack.push(top);
                            top = top.left;
                    }

                    if(top == null && stack.isEmpty() == false) {
                            top = stack.peek().right;
                            hold[x] = stack.pop().element;
                            x++;
               
                    }
                    if(top == null && stack.isEmpty() == true)break;
            }
        }else {
                System.out.println("The tree is empty");
        }

        
        for(int i = 0 ; i<hold.length ; i++)
            this.remove(hold[i]);
 
          return this;
      }

    
    
}
******************************************
//TEST CASE
package ; //INSERT PACKAGE NAME HERE

public class TestBSTSet {
	public static void main(String[] args) {
		
		int[] d1 = {7, 0, 16, 3, 2, 11, 8, 9, 4}; //no repetitions
		int[] d2 = {5, 6, 5, 1, 2, 2, 4, 9, 8, 20, 32, 3, 17};//with repetitions
		int v1 = 11; //value in set d1
		int v2 = 10; //value not in set d1
		int v3 = 0; //another value in set d1

		int[] d3 = {17, 2, 9, 8, 30}; //has elements in common with d1
		int[] d4 = {6, 1, 10, 12, 5}; //no common elements with d1

		int[] d6 = {2, 4, 6, 8, 11, 10, 14, 15, 18, 24, 22, 35, 28, 29}; //almost sorted array
		int[] d7 = {1, 3, 6, 10, 11, 22, 28, 35}; // sorted; has elements in common with d6		

		System.out.println("Test1---constructor1" );
	        BSTSet a0 = new BSTSet();
		a0.printBSTSet();
		System.out.println("\n");
				 
		System.out.println("Test2---constructor2---no repetitions" );
		BSTSet a1 = new BSTSet(d1);
		a1.printBSTSet();
		System.out.println("\n");
		
		System.out.println("Test3---constructor2---with repetitions" );
		BSTSet a2 = new BSTSet(d2);
		a2.printBSTSet();
		System.out.println("\n");
		
		System.out.println("Test4---isIn(v1)---true" );
		System.out.println("v1 is in set d1");
		System.out.println(a1.isIn(v1));
		System.out.println("\n");
		
		System.out.println("Test5---isIn(v2)---false" );
		System.out.println("v2 is in set d1");
		System.out.println(a1.isIn(v2));
		System.out.println("\n");
		
		System.out.println("Test6---add(v1)---v1 was in the set; no change" );
		a1 = new BSTSet(d1);
		a1.add(v1);
		a1.printBSTSet();
		System.out.println("\n");
		
		System.out.println("Test7---add(v2)---v2 was not in the set; it is added" );
		a1 = new BSTSet(d1);
		a1.add(v2);
		a1.printBSTSet();
		System.out.println("\n");			
		
		System.out.println("Test9---remove(v2)--v2 was not in the set; no change" );
		a1 = new BSTSet(d1);
		System.out.println(a1.remove(v2)); //false
		a1.printBSTSet();
		System.out.println("\n");
		
		System.out.println("Test10---remove(v1)--v1 was in the set; it is removed" );
		a1 = new BSTSet(d1);
		System.out.println(a1.remove(v1)); //true
		a1.printBSTSet();
		System.out.println("\n");
		
		System.out.println("Test11---remove(v3)--v3 was in the set; it is removed" );
		a1 = new BSTSet(d1);
		System.out.println(a1.remove(v3)); //true
		System.out.println("\n");
		
		System.out.println("Test12---union()---sets with common elements" );
		a1 = new BSTSet(d1);
		BSTSet a3 = new BSTSet(d3);
		BSTSet a5 = a1.union(a3); //union of d1 and d3
		a5.printBSTSet();
		System.out.println("\n");
		
		
		System.out.println("Test13---union()---sets without common elements" );
		a1 = new BSTSet(d1);
		BSTSet a4 = new BSTSet(d4);
		a5 = a4.union(a1); //union of d1 and d4
		a5.printBSTSet();
		System.out.println("\n");
		
		System.out.println("Test14---intersection()---sets with common elements" );
		a1 = new BSTSet(d1);
		a3 = new BSTSet(d3);
		a5 = a3.intersection(a1); //intersection of d1 and d3
		a5.printBSTSet();
		System.out.println("\n");

		System.out.println("Test15---intersection()---sets with no common elements" );
		a1 = new BSTSet(d1);
		a4 = new BSTSet(d4);
		a5 = a1.intersection(a4); //intersection of d1 and d4; should be empty
		a5.printBSTSet();
		System.out.println("\n");
		
		
		System.out.println("Test16---intersection()---with empty set" );
		a0 = new BSTSet(); //empty set
		a1 = new BSTSet(d1);
		a5 = a1.intersection(a0); //intersection of d1 and the empty set; should be empty
		a5.printBSTSet();
		System.out.println("\n");
		

		System.out.println("Test17---difference()-- one way");
		a0 = new BSTSet(d6);
		a1 = new BSTSet(d7);
		a5 = a0.difference(a1); //6, 11, 22, 28 are in common in both sets
		a5.printBSTSet();
		System.out.println("\n");


		System.out.println("Test18---difference() -- other way");
		a0 = new BSTSet(d6);
		a1 = new BSTSet(d7);
		a5 = a1.difference(a0); //6, 11, 22, 28 are in common in both sets
		a5.printBSTSet();
		System.out.println("\n");


		System.out.println("Test19---difference()-- empty");
		a0 = new BSTSet(d6); 
		a1 = new BSTSet(d6);
		a5 = a1.difference(a0); 
		a5.printBSTSet();
		System.out.println("\n");


		System.out.println("Test20---size() + height()" );
		a1 = new BSTSet(d1);
		System.out.println("The size of d1 is " + a1.size());
		System.out.println("The height d1 is " + a1.height()); //height should be minimum for bonus
		a1 = new BSTSet(d6);
		System.out.println("The size of d6 is " + a1.size());
		System.out.println("The height of d6 is " + a1.height()); 

		
		System.out.println("Test21---size() + height()---empty set" );
		a0 = new BSTSet(); //empty set
		System.out.println("The size of the empty set is " + a0.size()); // should be 0
		System.out.println("The height of the empty set is " + a0.height());//should be -1

		System.out.println("Test22---printNonRec()--elements should be in increasing order" );
		a1 = new BSTSet(d1);
		a1.printNonRec(); //set d1
		a1 = new BSTSet(d6);
		a1.printNonRec(); //set d6


		//Test bonus
		System.out.println("Test23---bonus" );
		a1 = new BSTSet(d6);
		System.out.println("The height of d6 is " + a1.height());
		a2 = new BSTSet(d7);
		System.out.println("The height of d7 2 is " + a2.height());
		a3 = a1.union(a2); 
		a4 = a1.intersection(a2);
		System.out.println("Print union:");
		a3.printBSTSet(); 
		System.out.println("The height of the union is " + a3.height());
		System.out.println("Print intersection:");
		a4.printBSTSet(); 
		System.out.println("The height of the intersection is " + a4.height());
		
	}

}
