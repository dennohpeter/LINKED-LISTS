package clientunorderedlinkedlistint;

import java.util.*;

public abstract class LinkedListIntClass implements LinkedListIntADT {

    protected class LinkedListNode implements Cloneable {

        public int info;
        public LinkedListNode link;

        //Default constructor
        public LinkedListNode() {
            info = 0;
            link = null;
        }

        //Alternate constructor
        public LinkedListNode(int elem, LinkedListNode ptr) {
            info = elem;
            link = ptr;
        }

        public Object clone() {
            LinkedListNode copy = null;
            try {
                copy = (LinkedListNode) super.clone();
            } catch (CloneNotSupportedException e) {
                return null;
            }
            return copy;
        }

        public String toString() {
            return Integer.toString(info);
        }
    } //end class LinkedListNode

    public class LinkedListIterator {

        private LinkedListNode current;  //points to the current node in list
        private LinkedListNode previous; //points to the node before the current node

        //Default constructor
        public LinkedListIterator() {
            current = (LinkedListNode) first;
            previous = null;
        }

        //Method to reset the iterator to the first node
        public void reset() {
            current = (LinkedListNode) first;
            previous = null;
        }

        //return a reference to current and advance iterator to next node
        public int next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            LinkedListNode temp = current;
            previous = current;
            current = current.link;
            return temp.info;
        }

        public boolean hasNext() {
            return (current != null);
        }

        //remove the node currently pointed to by the iterator.
        public void remove() {
            if (current == null) {
                throw new NoSuchElementException();
            }
            if (current == first) {
                first = first.link;
                current = (LinkedListNode) first;
                previous = null;
                if (first == null) {
                    last = null;
                }
            } else {
                previous.link = current.link;
                if (current == last) {
                    last = first;
                    while (last.link != null) {
                        last = last.link;
                    }
                }
                current = current.link;
            }
            count--;
        }

        public String toString() {
            return Integer.toString(current.info);
        }

    } //end class LinkedListIterator

    //Instance variables of the class LinkedListClass
    protected LinkedListNode first;//address of the first node/list
    protected LinkedListNode last; //address of the last node/list
    protected int count;            //number of nodes in the list
    public int totalSum;        //total sum of the nodes
    protected int minNumber;       //minimum node

    //Default constructor
    public LinkedListIntClass() {
        first = null;
        last = null;
        totalSum = 0;
        count = 0;
    }

    public boolean isEmptyList() {
        return (first == null);
    }

    public void initializeList() {
        first = null;
        last = null;
        count = 0;
    }

    public void print() {
        LinkedListNode current; //variable to traverse the list
        current = first;
        if (current != null) {
            minNumber = current.info;

            while (current != null) {//while more data to print            
                System.out.print(current.info + " ");
                totalSum += current.info;
                if (current.info < minNumber) {
                    minNumber = current.info;
                } else {
                    minNumber = minNumber;
                }
                current = current.link;
            }
        }
    }
    public void print2() {
        LinkedListNode current; //variable to traverse the list
        current = first;
            minNumber = current.info;                           
              int i = 0;
            while (current != null) {//while more data to print   
                if (i<count-1){                              
                System.out.print(current.info);   
                System.out.print(","); 
                }
                else{                                  
                System.out.print(current.info); 
                    break;
                }                         
                i+=1;
                current = current.link;
            
        }
    }

    //For getting Sum of all Elements
    String findSum() {

        return Integer.toString(totalSum);
    }
    // for getting min no.

    String findMin() {
        return Integer.toString(minNumber);
    }

    public int length() {
        return count;
    }

    public int front() {
        return first.info;
    }

    public int back() {
        return last.info;
    }

    public Object clone() {
        LinkedListIntClass copy = null;
        try {
            copy = (LinkedListIntClass) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }

        //If the list is not empty clone each node of the list.
        if (first != null) {
            //Clone the first node
            copy.first = (LinkedListNode) first.clone();
            copy.last = copy.first;
            LinkedListNode current;
            if (first != null) {
                current = first.link;
            } else {
                current = null;
            }
            //Clone the remaining nodes of the list
            while (current != null) {
                copy.last.link = (LinkedListNode) current.clone();
                copy.last = copy.last.link;
                current = current.link;
            }
        }
        return copy;
    }

    //Method to return an iterator of the list.
    public LinkedListIterator iterator() {
        return new LinkedListIterator();
    }

    public abstract boolean search(int searchItem);

    public abstract void insertFirst(int newItem);

    public abstract void insertLast(int newItem);

    public abstract void deleteNode(int deleteItem);
}
