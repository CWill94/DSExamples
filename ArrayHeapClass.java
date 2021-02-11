package p9_Package;

/*
 *Array-based Heap Class - Configured as MIN heap
 *Provides display during data addition and removal
 *<p>
 *@author Clayton Williams
 */

import java.util.Random;

public class ArrayHeapClass
{
    private int arrayCapacity;
    private int arraySize;
    public final int DEFAULT_ARRAY_CAPACITY = 10;
    private boolean displayFlag;
    private String[] heapArray;
    public final int HIGH_PRIORITY = 999;
    public final int HIGH_PROCESS_TIME = 25;
    public final int LOW_PRIORITY = 100;
    public final int LOW_PROCESS_TIME = 5;

    /*
     * Default constructor sets up array management conditions and default
     * display flag setting
     */


    ArrayHeapClass()
    {
        this.arrayCapacity = DEFAULT_ARRAY_CAPACITY;
        this.heapArray = new String[arrayCapacity];
        this.arraySize = 0;
        this.displayFlag = true;

    }

    /*
     * Accepts new OS operation in string form and adds it to heap
     *
     * @param newItem String to be added to the heap
     */

    void addItem(String newItem)
    {
        checkForArrayReSize();
        if(displayFlag)
        {
            System.out.println("Adding new item to array " + newItem);
        }
        heapArray[arraySize] = newItem;
        bubbleUpArrayHeap(arraySize);
        arraySize ++;
    }

    /*
     * Recursive operation to reset data in the correct order for the min heap
     * after new data addition
     *
     * @param currentIndex index of current item being assessed, and moved
     * up as required
     */


    private void bubbleUpArrayHeap(int currentIndex)
    {

        if(arraySize < 1)
        {
            return;
        }
        int currentValue = getPriority(heapArray[currentIndex]);
        int parentIndex = (currentIndex-1)/2;
        int parentValue = getPriority(heapArray[parentIndex]);
        if(currentValue < parentValue )
        {
            if(displayFlag)
            {
                System.out.println("Swapping Child and Parent");
                System.out.println("Bubble Up \n Swap\n"+
                        heapArray[currentIndex] + " with " + heapArray
                        [parentIndex]);
            }
            String temp = heapArray[currentIndex];
            heapArray[currentIndex] = heapArray[parentIndex];
            heapArray[parentIndex] = temp;
            bubbleUpArrayHeap(parentIndex);
        }
    }

    /*
     * Automatic resize operation used prior to any new data addition
     * in the heap
     * <p>
     * Tests for full heap array, and resizes to twice the current capacity
     * as required
     */


    private void checkForArrayReSize()
    {
        if(arraySize >= arrayCapacity)
        {
            arrayCapacity = arrayCapacity * 2;
            String temp[] = new String[arrayCapacity];
            for(int index = 0; index < arraySize; index++)
            {
                temp[index] = heapArray[index];
            }
            heapArray = temp;
        }
    }

    /*
     * Creates a randomly generated OS Run operation with the form:
     * "P(RunMMM)NN;" shows a text-based Operating System operation where
     * MMM is the priority and NN is the cycle time of the operation
     * <p>
     * @returns String OS run operation
     */


    String createHeapDataItem()
    {
        int testValue = getRandBetween(LOW_PRIORITY, HIGH_PRIORITY);

        while(isInHeap(testValue))
        {
            testValue = getRandBetween(LOW_PRIORITY, HIGH_PRIORITY);
        }

        int processTime = getRandBetween(LOW_PROCESS_TIME, HIGH_PROCESS_TIME);
        String dataValue = "P(Run" + testValue +")" + processTime + ";";
        return dataValue;
    }

    /*
     * Utility method that captures the priority value from the
     * OS operation string
     * <p>
     * @param value String input to be parsed for priority value
     *
     * @returns int Priority value
     */


    int getPriority(String value)
    {
        char first = value.charAt(5);
        String firstNum = Character.toString(first);
        char second = value.charAt(6);
        String secondNum = Character.toString(second);
        char third = value.charAt(7);
        String thirdNum = Character.toString(third);
        String num = firstNum + secondNum+ thirdNum;
        int priority = Integer.parseInt(num);

        return priority;
    }

    /*
     * Random generation of values between two numbers, inclusive
     *
     * @param lowInclusive minimum number that can be selected
     * @param highInclusive highest number that can be selected
     *
     * @returns int Pseudo-randomly generated number
     */


    private int getRandBetween(int lowInclusive, int highInclusive)
    {
        Random rand = new Random();
        int randomNum = rand.nextInt((highInclusive-lowInclusive)-1)
                + lowInclusive;

        return randomNum;
    }

    /*
     * Recursive helper method for isInHeap
     *
     * @param priorityValue Value to search for in heap array
     * @param currentIndex Index at current level to manage recursion
     *
     * @returns boolean result of the search
     */


    private boolean isInArrayHeap(int priorityValue, int currentIndex)
    {
        if(currentIndex == arraySize)
        {
            return false;
        }

        int currentPriority = getPriority(heapArray[currentIndex]);

        if(priorityValue == currentPriority)
        {
            return true;
        }
        return isInArrayHeap(priorityValue, ++currentIndex);
    }

    /*
     * Support method for createHeapDataItem
     *
     * @param priorityValue Value to search for in heap array to verify no
     * repeated priority values
     *
     * @returns boolean result of the search
     */


    private boolean isInHeap(int priorityValue)
    {
        if(arraySize == 0 )
        {
            return false;
        }
        return isInArrayHeap(priorityValue, 0);
    }

    /*
     * Removes OS operation from top of min heap, thus being the operation with
     * the lowest priority value
     *
     * @returns OS operation as string
     */
    String removeItem()
    {
        if(arraySize == 0)
        {
            return "Heap empty.";
        }
        String removed = heapArray[0];
        heapArray[0] = heapArray[--arraySize];
        arraySize--;
        heapArray[arraySize] = null;
        System.out.println(arraySize);
        trickleDownArrayHeap(0);
        return "Removed data from heap " + removed;
    }

    /*
     * Setter for state of the display flag
     *
     * @param setState Boolean to set to the display flag
     */


    void setDisplayFlag(boolean setState)
    {
        this.displayFlag = setState;
    }

    /*
     * Recursive operation to reset data in the correct order for the min heap
     * after data removal
     * <p>
     * @param currentIndex Current item being assessed, and moved down as needed
     */

    private void trickleDownArrayHeap(int currentIndex)
    {
        int lChildIndex = 2*currentIndex + 1;
        int rChildIndex = 2*currentIndex + 2;
        int currentValue = getPriority(heapArray[currentIndex]);
        int lChildValue = getPriority(heapArray[lChildIndex]);
        int rChildValue = getPriority(heapArray[rChildIndex]);
        String temp;

       if(heapArray[lChildIndex] != null)
       {
           if (currentValue > lChildValue)
           {
               if(displayFlag)
               {
                   System.out.println("Swapping Parent and Left Child");
                   System.out.println("Trickling Down \n Swap"+
                           heapArray[currentIndex] + " with " + heapArray
                           [lChildIndex]);
               }
               temp = heapArray[currentIndex];
               heapArray[currentIndex] = heapArray[lChildIndex];
               heapArray[lChildIndex] = temp;
               trickleDownArrayHeap(lChildIndex);
           }
       }

       if(heapArray[rChildIndex] != null)
       {
           if (currentValue > rChildValue)
           {
               if(displayFlag)
               {
                   System.out.println("Swapping Parent and Right Child");
                   System.out.println("Trickling Down \n Swap"+
                           heapArray[currentIndex] + " with " + heapArray
                           [rChildIndex]);
               }
               temp = heapArray[currentIndex];
               heapArray[currentIndex] = heapArray[rChildIndex];
               heapArray[rChildIndex] = temp;
               trickleDownArrayHeap(rChildIndex);
           }
       }

    }

}
