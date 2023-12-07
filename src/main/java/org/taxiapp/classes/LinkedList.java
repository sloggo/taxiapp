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
        else if(i+1>n){
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
        //i is index + 1, list is zero indexed
        //if empty insert at head
        if(isEmpty()){
            System.out.println("Empty list, node inserted as head");
            head = new Node(e);
            n++;
        }
        //if location i exceeds list length insert element at end of list
        else if(i>n){
            System.out.println("i exceeds list length, inserting at end of list");
            Node tmp = head;
            while(tmp.next!=null){
                tmp = tmp.next;
            }
            tmp.next = new Node(e);
            n++;
        }
        //if i = 0, insert before head
        else if(i==0){
            System.out.println("i = 0, node inserted before head");
            Node tmp = new Node(e);
            tmp.next = head;
            head = tmp;
            n++;
        }
        //else loop through list until at given index, insert node at given index
        else{
            Node newNode = new Node(e);
            Node tmp = head;
            for (int j = 0; j < i-1; j++) {
                tmp = tmp.next;
            }
            newNode.next = tmp.next;
            tmp.next = newNode;
            n++;
        }
    }
    public void update(T e, int i){
        //if list empty do nothing
        if(isEmpty()){
            return;
        }
        //if location exceeds length do nothing
        if(i+1>n){
            return;
        }
        //else update node data at index i
        Node tmp = head;
        for (int j = 0; j < i-1; j++) {
            tmp = tmp.next;
        }
        tmp.data = e;
    }
    public void remove(int i){
        //if list empty do nothing
        if(isEmpty()){
            return;
        }
        //if location exceeds length do nothing
        if((i+1)>n){
            return;
        }
        //else remove node at index i
        Node tmp = head;
        for (int j = 0; j < i-1; j++) {
            tmp = head.next;
        }
        tmp.next = tmp.next.next;
        n--;
    }
    public void printList(){
        Node tmp = head;
        while (tmp!=null){
            System.out.println(tmp.data);
            tmp = tmp.next;
        }

        System.out.println("Length: "+n);
    }


}
