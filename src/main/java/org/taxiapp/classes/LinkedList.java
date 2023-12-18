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
    public boolean isEmpty(){
        return head == null;
    }

    public int length(){
        return n;
    }

    public Boolean hasNext(){
        if(head == null){
            return false;
        }
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
    //public void pointToHead(){ current = head;}
    public void append(T value){
        Node newNode = new Node(value);
        if(head == null){
            head = newNode;
            current = head;
            n++;
            return;
        }
        while(hasNext()){
            moveForward();
        }
        current.next = newNode;
        getHead();
        n++;
    }
    public void remove(T e){
        if(isEmpty()){
            return;
        }
        if(head.data == e){
            head = head.next;
            n--;
            return;
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
        n--;
    }
    public T retrieveCurrent(){
        return current.data;
    }
    public void getHead(){
        current = head;
    }

    public void addAll(LinkedList<? extends T> list){
        list.getHead();

        while(list.current!=null){
            this.append(list.retrieveCurrent());

            if(list.current.next == null){
                break;
            }
            list.moveForward();
        }
    }


    public T get(int i){
        if(isEmpty()){
            return null;
        }
        getHead();
        if(i==0){
            return current.data;
        }

        for (int j = 0; j < i; j++) {
            if(!hasNext())
            {
                return null;
            }
            current = current.next;
        }
        return current.data;
    }
    public boolean contains(T e){
        if(isEmpty()){
            return false;
        }
        getHead();

        while (current!=null){
            if(current.data == e){
                return true;
            }
            current = current.next;
        }
        return false;
    }



    public void printList(){
        Node tmp = head;
        while (tmp!=null){
            System.out.println(tmp.data.toString());
            tmp = tmp.next;
        }

        System.out.println("Length: "+n);
    }


}
