package org.taxiapp.classes;

public class LinkedList<T> {
    private Node head;
    private Node current;
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
    /*public boolean isEmpty(){
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
    public void add(T e){
        if(isEmpty()){
            head = new Node(e);
            n++;
            return;
        }
        Node tmp = head;
        while(tmp.next!=null){
            tmp = tmp.next;
        }
        tmp.next = new Node(e);
        n++;
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
            tmp = tmp.next;
        }
        tmp.next = tmp.next.next;
        n--;
    }
    public void remove(T e){
        if(isEmpty()){
            return;
        }
        boolean isContained = true;

        Node tmp = head;
        if(e==head.data){
            head = head.next;
        }
        else {
            while (e != tmp.next.data) {
                if (tmp.data == null) {
                    isContained = false;
                    break;
                }
                tmp = tmp.next;
            }
            if (!isContained) {
                System.out.println("not contained");
                return;
            }
        tmp.next = tmp.next.next;
        }
        n--;

    }*/
    public boolean isEmpty(){
        return head == null;
    }
    public int length(){
        return n;
    }
    public Boolean hasNext(){
        return current.next != null;
    }
    public void moveForward(){
        if(hasNext() && current!=null){
            current = current.next;
        }
    }
    public void moveBackward(){
        if(current!=null && current!= head){
            Node tmp = head;
            while (head.next!=current) {
                tmp = tmp.next;
            }
            current = tmp;
        }

    }
    public void append(T value){
        Node newNode = new Node(value);
        if(head == null){
            head = newNode;
            current = head;
            return;
        }
        while(hasNext()){
            moveForward();
        }
        current.next = newNode;
    }
    public void remove(T e){
        if(isEmpty()){
            return;
        }
        if(head.data == e){
            head = head.next;
        }
        current = head;
        Node prev = null;
        while ( current!=null && current.data != e){
            prev = current;
            current = current.next;
        }
        if(current == null){
            return;
        }
        prev.next = current.next;
        current = head;
    }
    public T retrieveCurrent(){
        return current.data;
    }
    public void getHead(){
        current = head;
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
