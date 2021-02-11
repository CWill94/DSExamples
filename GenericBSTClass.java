/*
 * Generic Binary Search Tree extends Comparable
 *
 * @author Clayton Williams
 */
package p7_Package;

import org.jetbrains.annotations.Contract;

public class GenericBSTClass<GenericData extends Comparable<GenericData>>
{

    /*
     * Binary Search Tree node class for managing generic data
     */

    private class BST_Node
    {
        private GenericBSTClass.BST_Node leftChildRef, rightChildRef;
        private GenericData nodeData;

        /*
         * Initialization constructor for data
         *
         * @param GenericData inData generic data quantity
         */
        BST_Node(GenericData inData)
        {
            this.nodeData = inData;
            this.leftChildRef = null;
            this.rightChildRef = null;
        }

        /*
         * Initialization constructor for data
         *
         * @param GenericData inData generic data quantity
         * @param BST_Node leftRef reference to left child node
         * @param BST_Node rightRef reference to right child node
         */
        BST_Node(GenericData inData, BST_Node leftRef, BST_Node rightRef)
        {
            this.nodeData = inData;
            this.leftChildRef = leftRef;
            this.rightChildRef = rightRef;
        }

    }
    BST_Node BST_Root;
    private GenericData foundData, removeData;
    static int INORDER_TRAVERSE = 102, POSTORDER_TRAVERSE = 103,
            PREORDER_TRAVERSE = 101;

    /*
     * Default class constructor, no initialization actions
     */
    GenericBSTClass()
    {

    }

    /*
     * Provides inOrder traversal action
     *
     * @param localRoot - BST_Node tree root reference at the
      * current recursion level
     */

    private void displayInOrder(BST_Node localRoot)
    {
        if(localRoot != null)
        {
            displayInOrder(localRoot.leftChildRef);
            System.out.println(localRoot.nodeData);
            displayInOrder(localRoot.rightChildRef);
        }
    }

    /*
     * Provides postOrder traversal action
     *
     * @param localRoot - BST_Node tree root reference at the
     * current recursion level
     */

    private void displayPostOrder(BST_Node localRoot)
    {
        if(localRoot != null)
        {
            displayPostOrder(localRoot.leftChildRef);
            displayPostOrder(localRoot.rightChildRef);
            System.out.println(localRoot.nodeData);
        }
    }

    /*
     * Provides preOrder traversal action
     *
     * @param localRoot - BST_Node tree root reference at the
     * current recursion level
     */

    private void displayPreOrder(BST_Node localRoot)
    {
        if(localRoot != null)
        {
            System.out.println(localRoot.nodeData);
            displayPreOrder(localRoot.leftChildRef);
            displayPreOrder(localRoot.rightChildRef);
        }
    }

    /*
     * Provides user with three ways to display BST data
     *
     * @param traverseCode - int code for selecting BST traversal method,
     * accepts PREORDER_TRAVERSE, INORDER_TRAVERSE, POSTORDER_TRAVERSE
     */

    void displayTree(int traverseCode)
    {
        if( traverseCode == INORDER_TRAVERSE)
        {
            System.out.println("Inorder Display:");
            displayInOrder(BST_Root);
        }
        if( traverseCode == PREORDER_TRAVERSE)
        {
            System.out.println("Preorder Display");
            displayPreOrder(BST_Root);
        }
        if( traverseCode == POSTORDER_TRAVERSE)
        {
            System.out.println("Postorder Display");
            displayPostOrder(BST_Root);
        }

    }

    /*
     * Insert method for BST
     *
     * @param inData GenericData to be added to BST
     *
     * @returns Boolean result of action
     */

    boolean insertData(GenericData inData)
    {
        BST_Node newNode = new BST_Node(inData);
        if(isEmpty())
        {
            BST_Root = newNode;
            return true;
        }
        return insertHelper(BST_Root, inData);
    }

    /*
     * Insert helper method for BST insert action
     *
     * @param local tree root reference at the current recursion level
     * @param inData GenericData to be added to BST
     *
     * @returns Boolean result of insertion action
     */

    private boolean insertHelper(BST_Node localroot, GenericData inData)
    {
        BST_Node newNode = new BST_Node(inData);
        BST_Node current = localroot;
        BST_Node parent = null;
        while(true)
        {
            parent = current;
            if(inData.compareTo(current.nodeData) == -1)
            {
                current = current.leftChildRef;
                if(current == null)
                {
                    parent.leftChildRef = newNode;
                    return true;
                }
            }
            else
            {
                current = current.rightChildRef;
                if(current==null)
                {
                    parent.rightChildRef = newNode;
                    return true;
                }
            }
        }
    }

    /*
     * Test for empty tree
     *
     * @returns Boolean result of test
     */

    boolean isEmpty()
    {
        return BST_Root == null;
    }

    /*
     * Searches tree from given node to minimum value
     *
     * @param minParent - BST_Node reference to current node
     * @param minLoc - BST_Node reference to child node to be tested
     *
     * @returns BST_Node reference containing removed node
     */

    @Contract(pure = true)
    private BST_Node removeFromMin(BST_Node minParent, BST_Node minLoc)//not done
    {
        return BST_Root;
    }

    /*
     * Removes data node from tree using given key
     *
     * @param inData GenericData that includes the necessary key
     *
     * @returns GenericData result of remove action
     */
    GenericData removeNode(GenericData inData)
    {
        return BST_Root.nodeData;
    }

    /*
     * Remove helper for BST remove action
     * Note: uses removeFromMin method
     * Note: this method sets the class removedData variable so the data
     * can be provided to the user
     * <p>
     * @param localRoot  BST_Node tree root reference at the current
     * recursion level
     * @param outData GenericData item that includes the necessary key
     *
     * @returns BST_Node reference result of remove helper action
     */

    @Contract(pure = true)
    private BST_Node removeNodeHelper(BST_Node localRoot,
                                      BST_Node outData)
    {
        return BST_Root;
    }

    /*
     * Searches for data in BST given GenericData with necessary key
     *
     * @param searchData GenericData item containing key
     *
     * @returns GenericData reference to found data
     */

    GenericData search(GenericData searchData)
    {
        BST_Node current = BST_Root;
        searchHelper(current, searchData);
        return foundData;
    }

    /*
     * Helper method for BST search action
     * Note: this function sets class variable foundData so the data is made
     * available, but then returns a Boolean success
     * <p>
     * @param localRoot BST_Node tree root reference at the current recursion
     * level
     * @param searchData GenericData item containing key
     *
     * @returns Boolean result of search
     */

    @Contract("null, _ -> false")
    private boolean searchHelper(BST_Node localRoot, GenericData searchData)
    {
        if(localRoot == null)
        {
            return false;
        }
        if(searchData.compareTo(localRoot.nodeData)== 0)
        {
            foundData = localRoot.nodeData;
            return true;
        }
        if(searchData.compareTo(localRoot.nodeData)== 1)
        {
            return searchHelper(localRoot.rightChildRef, searchData);
        }

        return searchHelper(localRoot.leftChildRef, searchData);

    }

}