package kernel.ds;

import java.io.Serializable;

/**
 *
 * @author Syeed Ibn Faiz
 */
public class SparseVector implements Serializable {
    
    public static class Element implements Serializable {
        public int index;
        public double value;

        public Element(int index, double value) {
            this.index = index;
            this.value = value;
        }        
    }
    
    private Element[] elements;
    private int size;
    private final int MAX_SIZE = 100;

    public SparseVector(int capacity) {
        elements = new Element[capacity];
    }

    public SparseVector() {
        elements = new Element[MAX_SIZE];
    }
    
    public void add(int index, double value) {
        add(new Element(index, value));
    }
    
    public void add(Element elem) {
        if (isFull()) {
            resize();
        }
        elements[size++] = elem;
    }
    
    public Element get(int n) {
        if (n >= size) {
            return null;
        }
        return elements[n];
    }
    
    public boolean isFull() {
        return size == elements.length;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public int size() {
        return size;
    }
    
    private void resize() {
        Element[] newElements = new Element[size + MAX_SIZE];
        for (int i = 0; i < elements.length; i++) {
            newElements[i] = elements[i];
            elements[i] = null;
        }
        elements = newElements;
    }
    
}
