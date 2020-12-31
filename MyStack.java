package project3;

/**
 * This class represents a reference-based stack
 * It provides implementations for push, pop, and top
 * Allows user to check if two stacks are equal and to print out contents of stack
 * @param <E> the type of object that the stack will hold
 */
public class MyStack<E> implements Stack<E>
{
    // Data fields
    private int size; // size
    private Node top; // top of stack

    /**
     * This is a private node class
     * Contains a data field and a reference to previous node
     * and next node
     *
     * Constructor available to create Node for specific data
     */
    private class Node<E>
    {
        // Data fields
        E data;
        Node next;

        // Constructor
        Node(E data)
        {
            this.data = data;
            next = null;
        }
    }

    /**
     * Default Constructor for MyStack class
     * Creates an empty stack
     * Makes size 0 and sets top to null
     */
    public MyStack()
    {
        // Create empty stack
        size = 0;
        top = null;
    }

    /**
     * Add an element to the top of this stack
     * @param item to be added to this stack
     * throws IllegalArgumentException if `item == null`
     */
    public void push (E item) throws IllegalArgumentException
    {
        // Check if item is null
        if (item == null)
            throw new IllegalArgumentException("Attempted to push null item on stack");

        // Create node for item
        Node n = new Node(item);

        // Push to empty stack
        if (top == null)
        {
            top = n;
            size++;
        }
        // Push to nonempty stack
        else
        {
            n.next = top;
            top = n;
            size++;
        }
    }

    /**
     * Remove and return the element from the top of this stack
     * @return the element from the top of this stack or null if this stack is empty
     */
    public E pop()
    {
        // Check if stack is empty
        if (size == 0)
            return null;

        // Pop element from nonempty stack and return it
        E tmp = (E) top.data;
        top = top.next;
        size--;
        return tmp;
    }

    /** Return the element from the top of this stack.
     * @return  the element from the top of this stack or null if this stack is empty
     */
    public E top()
    {
        // Check if stack is empty
        if (size == 0)
            return null;

        // Return top element from nonempty stack
        return (E) top.data;
    }

    /**
     * Determines if this stack is equal to `obj`.
     * @obj an Object that is compared to this stack for equality
     * @return true if this stack is equal to `obj` (same elements in the same order)
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

        // Check if obj can be downcasted to MyStack type
        if (!(obj instanceof MyStack))
            return false;

        // Cast obj to MyStack object
        MyStack<E> object = (MyStack<E>) obj;

        // Check if same size
        if (size != object.size)
            return false;

        // Create nodes that point to top of each stack
        Node current1 = top;
        Node current2 = object.top;

        // Iterate through stacks
        while (current1 != null && current2 != null)
        {
            // Return false if corresponding elements are not equal
            if (!current1.data.equals(current2.data))
                return false;
            current1 = current1.next;
            current2 = current2.next;
        }
        return true;
    }

    /**
     * Returns a string representation of this stack. The string is constructed by
     * concatenating all elements of this stack separated by a comma and a single space.
     * The bottom of the stack should be the leftmost element and the top of the stack
     * should be the rightmost element. There should be no comma after the last element.
     * @return a string representation of this stack.
     */
    public String toString()
    {
        // Check if stack is empty
        if (size == 0)
            return "";

        // Create empty string and new stack to pop elements into
        String stackString = "";
        MyStack<E> reverseStack = new MyStack<>();

        // Set current to top
        Node current = top;

        // Iterate through stack and push elements onto reverseStack
        while (current != null)
        {
            reverseStack.push((E) current.data);
            current = current.next;
        }

        // Add elements of reverseStack to string
        while(reverseStack.top() != null)
        {
            stackString += reverseStack.pop();
            // Add comma only if more elements left
            if (reverseStack.top() != null)
                stackString += ", ";
        }
        return stackString;
    }
}
