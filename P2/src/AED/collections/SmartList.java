package AED.collections;

import java.util.Iterator;

public class SmartList<Item> implements Iterable<Item> {
    @Override
    public Iterator<Item> iterator() {
        return null;
    }

    private class Node {
        private Item item;
        private Node next;
    }

    private Node first;
    private int size;

    public void LinkedList() {
        this.first = null;
        this.size = 0;
    }

    public void add(Item item)
    {
        if(item == null){
            throw new IllegalArgumentException();
        }
        else{
            Node old = this.first;
            this.first = new Node();
            this.first.item = item;
            this.first.next = old;
            this.size++;
        }
    }

    Item searchMTF(Item item){
        Item value = null;
        for (Node current = first; current != null; current = current.next) {
            if(current.item.equals(item)){
                value = current.item;
            }
            else{
                value = null;
            }
        }
        return value;
    }
}
