package project3;

/**
 * This class represents an array-based queue
 * It provides implementations for enqueue, dequeue, and peek
 * Allows user to check if two queues are equal and to print out contents of queue
 * @param <E> the type of object that the queue will hold
 */
public class MyQueue<E> implements Queue<E>
{
    // Data fields
    private E[] arrayElements; // Array
    private int size; //size
    private int front, rear; // Front and rear indices
    private static int DEFAULT_CAPACITY = 10; // Default capacity is 10

    /**
     * Default Constructor for MyQueue class
     * Creates an empty queue
     */
    public MyQueue()
    {
        // Create empty queue
        arrayElements = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
        front = 0;
        rear = 0;
    }

    /**
     * Add an element to the back of this queue
     * @param item to be added to this queue
     * throws IllegalArgumentException if `item == null`
     */
    public void enqueue(E item) throws IllegalArgumentException
    {
        // Check if item is null
        if (item == null)
            throw new IllegalArgumentException("Null item attempted to be enqueued");

        // Enqueue to empty queue
        if (size == 0)
        {
            arrayElements[front] = item;
            rear = (rear + 1) % arrayElements.length;
            size++;
            return;
        }

        // Enqueue to partially full queue
        if (rear % arrayElements.length != front)
        {
            arrayElements[rear % arrayElements.length] = item;
            rear = (rear + 1) % arrayElements.length;
            size++;
        }

        // Enqueue to full queue
        else
        {
            E[] biggerArray = (E[]) new Object[arrayElements.length * 2];
            for (int i = 0; i < arrayElements.length; i++)
                biggerArray[i] = arrayElements[(i - front) % arrayElements.length];
            arrayElements = biggerArray;
            arrayElements[size] = item;
            front = 0;
            rear = arrayElements.length - 1;
            size++;
        }
    }

    /**
     * Remove and return the element from the front of this queue.
     * @return the element from the front of this queue or null if this queue is empty
     */
    public E dequeue()
    {
        // Check if queue is empty
        if (size == 0)
            return null;

        // Dequeue front element from nonempty queue and return it
        E tmp = arrayElements[front];
        front = (front + 1) % arrayElements.length;
        size--;
        return tmp;
    }

    /** Return the element from the front of this queue.
     * @return  the element from the top of this queue or null if this queue is empty
     */
    public E peek()
    {
        // Check if queue is empty
        if (size == 0)
            return null;

        // Return front element from nonempty queue
        return arrayElements[front];
    }

    /**
     * Determines if this queue is equal to `obj`.
     * @obj an Object that is compared to this queue for equality
     * @return true if this queue is equal to `obj` (same elements in the same order)
     *         false, otherwise
     */
    public boolean equals(Object obj)
    {
        // Check if objects both reference same object
        if (this == obj)
            return true;

        // Check if calling object is null
        if (this == null)
            return obj == null;

        // Check if obj can be downcasted to MyQueue type
        if (!(obj instanceof MyQueue))
            return false;

        // Cast obj to MyQueue object
        MyQueue<E> objectList = (MyQueue<E>) obj;

        // Check if same size
        if (size != objectList.size)
            return false;

        // Create index variables that store front indices of queues
        int index1 = this.front;
        int index2 = objectList.front;

        // Iterate through queues
        for (int i = 0; i < size; i++)
        {
            // Return false if corresponding elements are not equal
            if (!(arrayElements[index1].equals(objectList.arrayElements[index2])))
                return false;
            index1 = (index1 + 1) % arrayElements.length;
            index2 = (index2 + 1) % objectList.arrayElements.length;
        }
        return true;
    }

    /**
     * Returns a string representation of this queue. The string is constructed by
     * concatenating all elements of this queue separated by a comma and a single space.
     * The front of the queue should be the leftmost element and the back of the queue
     * should be the rightmost element. There should be no comma after the last element.
     * @return a string representation of this queue.
     */
    public String toString()
    {
        // Check if queue is empty
        if (size == 0)
            return "";

        // Create empty string
        String queueString = "";

        // Check if corresponding queue elements are equal
        for (int i = 0; i < size - 1; i++)
        {
            queueString += arrayElements[(front + i) % arrayElements.length] + ", ";
        }

        // Add last element to string
        queueString += arrayElements[(front + size - 1) % arrayElements.length];

        return queueString;
    }
}
