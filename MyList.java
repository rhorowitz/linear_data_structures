package project3;

import java.util.NoSuchElementException;

/**
 * This class represents a doubly-linked list
 * It provides implementations for adding, removing, searching, retrieving, clearing, and checking for
 * common properties of a list (size)
 * Provides a method for checking equality between two linked lists
 * @param <E> the type of object that the linkedList will hold
 */
public class MyList<E> implements List<E>
{
    // Data fields
    private int size; // size
    private Node head; // head node
    private Node tail; // tail node

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
        Node prev;

        // Constructor
        Node(E data)
        {
            this.data = data;
            next = null;
            prev = null;
        }
    }

    /**
     * Default Constructor for node class
     * Creates an empty LinkedList
     * Changes size to 0, head to null, and tail to null
     */
    public MyList()
    {
        // Create empty list
        size = 0;
        head = null;
        tail = null;
    }

    /**
     * Adds an element 'item' at position `pos`, shifts elements starting at `pos` by
     * one position to the right (higher position values)
     * @param item the element to be added to this list
     * @param pos postion at which `item` should be added
     * throws NoSuchElementException if `pos` < 0 or `pos` > size
     * throws IllegalArgumentException if `item == null`
     */
    public boolean add(E item, int pos) throws NoSuchElementException
    {
        // Check for valid parameters
        if (pos < 0 || pos > size)
            throw new NoSuchElementException("Invalid position entered");
        if (item == null)
            throw new IllegalArgumentException("Attempt to add null object to list");

        // Create a new Node
        Node n = new Node(item);

        // Add if list is empty
        if (size == 0)
        {
            head = n;
            tail = n;
            size++;
            return true;
        }
        // Add to front of list
        if (pos == 0)
        {
            n.next = head;
            head.prev = n;
            head = n;
            size++;
            return true;
        }
        // Add to end of list
        if (pos == size)
        {
            tail.next = n;
            n.prev = tail;
            tail = n;
            size++;
            return true;
        }

        // Create a counter and point current to head
        int counter = 0;
        Node current = head;

        // Iterate until you get to position
        while (counter < pos)
        {
            current = current.next;
            counter++;
        }

        // Add to middle of list
        n.next = current;
        current.prev.next = n;
        n.prev = current.prev;
        current.prev = n;
        size++;
        return true;

    }

    /**
     * Removes and returns an element at position `pos`, shifts elements starting
     * at `pos+1` by one to the left (lower position values)
     * @param pos the position from which the element should be removed
     * @ return the element removed from the list
     * throws NoSuchElementException if pos < 0 or pos >= size
     */
    public E remove(int pos) throws NoSuchElementException
    {
        // Check for valid parameter
        if (pos < 0 || pos >= size)
            throw new NoSuchElementException("Invalid position entered");

        // Remove from empty list
        if (size == 0)
            return null;

        // Remove from list with one element
        if (size == 1)
        {
            E tmp = (E) head.data;
            head = null;
            tail = null;
            size--;
            return tmp;
        }

        // Remove from front of list
        if (pos == 0)
        {
           E tmp = (E) head.data;
           head.next.prev = null;
           head = head.next;
           size--;
           return tmp;
        }

        // Remove from end of list
        if (pos == size - 1)
        {
            E tmp = (E) tail.data;
            tail.prev.next = null;
            tail = tail.prev;
            size--;
            return tmp;
        }

        // Create a counter and point current to head
        int counter = 0;
        Node current = head;

        // Iterate until you get to position
        while (counter < pos)
        {
            current = current.next;
            counter++;
        }

        // Remove element from middle of list
        current.prev.next = current.next;
        current.next.prev = current.prev;
        size--;
        return (E) current.data;
    }

    /**
     * Removes and returns an element equal to `item`, shifts elements starting
     * at the next position by one to the left (lower position values)
     * @param item element to remove
     * @return the removed element, or null if element equal to `item` is not in this list
     */
    public E remove(E item) {
        // Remove from empty list
        if (size == 0)
            return null;

        // Check if item is null
        if (item == null)
            return null;

        // Remove from list with one element
        if (size == 1)
        {
            // Check if item is in list
            if (item.equals(head.data))
            {
                E tmp = (E) head.data;
                head = null;
                tail = null;
                size--;
                return tmp;
            }
            return null;
        }

        // Remove from front of list
        if (item.equals(head.data))
        {
            E tmp = (E) head.data;
            head.next.prev = null;
            head = head.next;
            size--;
            return tmp;
        }

        // Remove from end of list
        if (item.equals(tail.data))
        {
            E tmp = (E) tail.data;
            tail.prev.next = null;
            tail = tail.prev;
            size--;
            return tmp;
        }

        // Point current to head
        Node current = head;

        // Iterate until you find item or list comes to an end
        while (current != null && !current.data.equals(item))
            current = current.next;

        if (current == null)
            return null;

        // Remove element from middle of list
        current.prev.next = current.next;
        current.next.prev = current.prev;
        size--;
        return (E) current.data;
    }

    /**
     * Determines if 'item' is in the list and if so returns its position
     * @param item to locate in this list
     * @return position of `item` in this list or -1 if `item` is not found in this list
     */
    public int find(E item)
    {
        // Create a counter and point current to head
        int counter  = 0;
        Node current = head;

        // Check if list is empty
        if (size == 0)
            return -1;

        // Check if item is null
        if (item == null)
            return -1;

        // Check if at front
        if (item.equals(head.data))
            return 0;

        // Check if at end
        if (item.equals(tail.data))
            return size - 1;

        // Search for item in list
        while (current != null && !(current.data.equals(item)))
        {
            current = current.next;
            counter++;
        }

        if (current == null)
            return -1;

        return counter;
    }

    /**
     * Retrieves and returns an element from position `pos`
     * @param pos the position of item to return
     * @return element stored at position `pos`
     * throws NoSuchElementException if pos < 0 or pos >= size
     */
    public E get(int pos) throws NoSuchElementException
    {
        // Check for valid parameter
        if (pos < 0 || pos >= size)
            throw new NoSuchElementException("Invalid position entered");

        // Retrieve and return from empty list
        if (size == 0)
            return null;

        // Retrieve and return from one element list
        if (size == 1)
            return (E) head.data;

        // Retrieve and return from front of list
        if (pos == 0)
            return (E) head.data;

        // Retrieve and return from end of list
        if (pos == size - 1)
            return (E) tail.data;

        // Create a counter and point current to head
        int counter = 0;
        Node current = head;

        // Iterate until you get to position
        while (counter < pos)
        {
            current = current.next;
            counter++;
        }

        // Retrieve element from current position and return it
        return (E) current.data;
    }

    /**
     * Returns the current number of elements in this list
     * @return the number of elements in this list
     */
    public int size()
    {
        return size;
    }

    /**
     * Removes all elements from this list, so it is once again empty.
     */
    public void clear()
    {
        // Clear the list
        size = 0;
        head = null;
        tail = null;
    }

    /**
     * Determines if this list is equal to `obj`.
     * @obj an Object that is compared to this list for equality
     * @return true if this list is equal to `obj` (same elements in the same order)
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

        // Check if obj can be downcasted to MyList type
        if (!(obj instanceof MyList))
            return false;

        // Cast obj to MyList object
        MyList<E> objectList = (MyList<E>) obj;

        // Check if same size
        if (size != objectList.size)
            return false;

        // Create nodes that point to heads of each list
        Node current1 = head;
        Node current2 = ((MyList<E>) obj).head;

        // Iterate through lists
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
     * Returns a string representation of this list. The string is constructed by
     * concatenating all elements of this list separated by a comma and a single space.
     * There should be no comma after the last element.
     * @return a string representation of this list.
     */
    public String toString()
    {
        // Check if list is empty
        if (size == 0)
            return "";

        // Point current to head and create empty string
        Node current = head;
        String listString = "";

        // Iterate throughout list (up until but not including last element) and add data elements to string
        while (current.next != null)
        {
            listString += current.data + ", ";
            current = current.next;
        }

        // Add last element to string
        if (current.next == null)
            listString += current.data;

        return listString;
    }
}
