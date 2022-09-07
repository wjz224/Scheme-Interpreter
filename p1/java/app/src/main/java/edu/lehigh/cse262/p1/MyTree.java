package edu.lehigh.cse262.p1;

import java.util.List;
import java.util.function.Function;

/**
 * A binary tree, implemented from scratch
 */
public class MyTree<T extends Comparable<T>>{
    /**
     * Insert a value into the tree
     * 
     * @param value The value to insert
     */
    // inner class for tree nodes
    private TreeNode root ;
    
    private class TreeNode{
        // value of node
        T value;
        // left node pointer
        TreeNode left;
        // right node pointer
        TreeNode right;
        // inner class constructor for tree nodes
        TreeNode(T val){
            value = val;
            // left node starts null
            left =  null;
            // right node starts null
            right = null;
        }
        
    }
    // Tree constructor
    MyTree(){
        // start tree with a null root
        root = null;
    }
    
    void insert(T value) {
        // if root is null call constructor to create node for root
        if(root == null){
            // set root to new node.
            root = new TreeNode(value);
        }
        else{
            TreeNode parent, current;
            // initalizing parent
            parent = null;
            // setting current to root
            current = root;
            // While loop iterates through and looks for leaf node until current node is null
            while(current != null){ 
                parent = current;
                // if value is less than current node's value, than go to the left child
                if(value.compareTo(current.value) < 0){
                    // setting current to left child
                    current = current.left;
                }
                else if(value.compareTo(current.value) > 0){
                    // setting current to right child
                    current = current.right;
                }
                else{
                    // if value is found in BST than return and do not do insert 
                    return;
                }
            }
            // when current node is null, its parent left and right childs are the potential spots for inserting the value
            // if value is less than parent value, than it will be the left child.
            if(value.compareTo(parent.value) < 0){
                // Creating a new node with the value and inserting it as the left child
                parent.left = new TreeNode(value);
            }
            // if value is greater than parent value, than it will be right child
            else {
                // Creating a new node with the value and inserting it as the right child
                parent.right = new TreeNode(value);
            }
        }
    }

    /** Clear the tree */
    void clear() {
        // just set root to null and tree would be destroyed, no link to previous tree.
        root = null;
    }

    /**
     * Insert all of the elements from some list `l` into the tree
     *
     * @param l The list of elements to insert into the tree
     */

    void inslist(List<T> l) {
        // if list is null or empty, than insert nothing and return from the function
        if(l == null || l.size() < 1){
            return;
        }
        // if root is null, than call the constructor with list's first element
        if(root == null){
            // Setting root to newly created node using list's first element
            root = new TreeNode(l.get(0));
            // For loop that inserts each element from the list. Since root already was created with first element, we will start at second element
            for(int i = 1; i < l.size(); i++){
                // insert element into tree.
                this.insert(l.get(i));
            }
        }
        // if root is not null, than for loop will just insert all elements starting from the first element.
        else{
            // for loop that inserts each element from the list, starting at the second element
            for(int i = 0; i < l.size(); i++){
                // insert element into tree.
                this.insert(l.get(i));
            }
        }
    }
    

    /**
     * Perform an in-order traversal, applying `func` to every element that is
     * visited
     * 
     * @param func A function to apply to each item
     */
    void inorder(Function<T, T> func) {
        // calls helper function for recursive call starting on root
        inorderHelper(root,func);
    }

    void inorderHelper(TreeNode node, Function<T,T> func){
        // recursive call to the left child
        inorderHelper(node.left,func);
        // setting new value to node after applying function
        node.value = func.apply(node.value);
        // recursive call to the reight child
        inorderHelper(node.right,func);
    }

    /**
     * Perform a pre-order traversal, applying `func` to every element that is
     * visited
     * 
     * @param func A function to apply to each item
     */
    void preorder(Function<T, T> func) {
        
    }
}