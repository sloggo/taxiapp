package org.taxiapp.classes;

public class LinkedList<T> {
    private Node head;
    private int n;

    private class Node {
        private T data;
        Node next;
        Node(T d){
            data = d;
            next = null;
        }
    }
    public LinkedList(){
        head = null;
        n=0;

    }
    public boolean isEmpty(){
        if(head == null){
            return true;
        }
        return  false;
    }
    public int length(){
        return n;
    }
    public T retrieve(int i){
        if(isEmpty()){
            System.out.println("List is empty");
            return null;
        }
        if(i+1>n){
            System.out.println("Retrieval index out of list bounds");
            return  null;
        }
        else {
            Node temp = head;
            int j=0;
            while (j<i)
            {
                temp = temp.next;
                j++;
            }

            return temp.data;
        }
    }
    public void insert(T e, int i){

    }
    public void update(T e, int i){

    }
    public void remove(int i){

    }

}
