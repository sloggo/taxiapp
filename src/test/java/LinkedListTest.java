import org.junit.jupiter.api.Test;
import org.taxiapp.classes.LinkedList;
import java.io.IOException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedListTest {
    //intents of testing is to ensure functions in LinkedList class work as expected
    @Test
    public void isEmptyTest(){
        LinkedList<Integer> list = new LinkedList<>();
        assertEquals(list.isEmpty(), true);
    }
    @Test
    public void notEmptyTest(){
        LinkedList<Integer> list = new LinkedList<>();
        list.append(1);
        assertEquals(list.isEmpty(), false);
    }
    @Test
    public void emptyAfterRemovalTest(){
        LinkedList<Integer> list = new LinkedList<>();
        list.append(1);
        list.remove(1);
        assertEquals(list.isEmpty(), true);
    }
    @Test
    public void emptyLengthTest(){
        LinkedList<Integer> list = new LinkedList<>();
        assertEquals(list.length(), 0);
    }
    @Test
    public void lengthAfterAdditionTest(){
        LinkedList<Integer> list = new LinkedList<>();
        list.append(1);
        list.append(2);
        list.append(3);
        list.append(4);
        assertEquals(list.length(), 4);
    }
    @Test
    public void lengthAfterRemovalTest(){
        LinkedList<Integer> list = new LinkedList<>();
        list.append(1);
        list.append(2);
        list.append(3);
        list.append(4);
        list.remove(1);
        assertEquals(list.length(), 3);
    }
    @Test
    public void emptyHasNextTest(){
        LinkedList<Integer> list = new LinkedList<>();
        assertEquals(list.hasNext(),false);
    }
    @Test
    public void nullNextHasNextTest(){
        LinkedList<Integer> list = new LinkedList<>();
        list.append(1);
        assertEquals(list.hasNext(),false);
    }
    @Test
    public void validHasNextTest(){
        LinkedList<Integer> list = new LinkedList<>();
        list.append(1);
        list.append(2);
        assertEquals(list.hasNext(),true);
    }
    @Test
    public void moveForwardTest(){
        LinkedList<Integer> list = new LinkedList<>();
        list.append(1);
        list.append(2);
        list.moveForward();
        assertEquals(list.retrieveCurrent(),2);
    }
    @Test
    public void nullMoveForwardTest(){
        LinkedList<Integer> list = new LinkedList<>();
        list.append(1);
        list.moveForward();
        assertEquals(list.retrieveCurrent(),1);
    }
    @Test
    public void moveBackwardTest(){
        LinkedList<Integer> list = new LinkedList<>();
        list.append(1);
        list.append(2);
        list.moveForward();
        list.moveBackward();
        assertEquals(list.retrieveCurrent(),1);
    }
    @Test
    public void headMoveBackwardTest(){
        LinkedList<Integer> list = new LinkedList<>();
        list.append(1);
        list.moveBackward();
        assertEquals(list.retrieveCurrent(),1);
    }
    @Test
    public void retrieveCurrentTest(){
        LinkedList<Integer> list = new LinkedList<>();
        list.append(1);
        list.append(2);
        list.append(3);
        list.append(4);
        while (list.hasNext()){
            list.moveForward();
        }
        assertEquals(list.retrieveCurrent(),4);
    }
    @Test
    public void retrieveHeadTest(){
        LinkedList<Integer> list = new LinkedList<>();
        list.append(1);
        list.append(2);
        list.append(3);
        list.append(4);

        assertEquals(list.retrieveCurrent(),1);
    }
    @Test
    public void retrieveNullTest(){
        LinkedList<Integer> list = new LinkedList<>();
        assertThrows(NullPointerException.class, list::retrieveCurrent);
    }
    @Test
    public void getHeadTest(){
        LinkedList<Integer> list = new LinkedList<>();
        list.append(1);
        list.append(2);
        list.append(3);
        list.append(4);
        while (list.hasNext()){
            list.moveForward();
        }
        list.getHead();
        assertEquals(list.retrieveCurrent(),1);
    }
    @Test
    public void getHeadAfterRemovalTest(){
        LinkedList<Integer> list = new LinkedList<>();
        list.append(1);
        list.append(2);
        list.append(3);
        list.append(4);
        while (list.hasNext()){
            list.moveForward();
        }
        list.remove(1);
        list.getHead();
        assertEquals(list.retrieveCurrent(),2);
    }
    @Test
    public void addAllTest(){
        LinkedList<Integer> list = new LinkedList<>();
        list.append(1);
        list.append(2);
        list.append(3);
        list.append(4);
        LinkedList<Integer> temp = new LinkedList<>();
        temp.append(5);
        temp.append(6);
        temp.append(7);
        temp.append(8);
        list.addAll(temp);
        assertTrue(list.contains(5));
        assertTrue(list.contains(6));
        assertTrue(list.contains(7));
        assertTrue(list.contains(8));
    }
    @Test
    public void getTest(){
        LinkedList<Integer> list = new LinkedList<>();
        Random r = new Random();
        int length = r.nextInt(24)+1;
        //length is + 1 so it isnt zero;
        for (int i = 0; i < length; i++) {
            list.append(i);
        }

        //get function uses zero indexing
        int num = r.nextInt(length);
        assertEquals(list.get(num),num);
    }
    @Test
    public void headGetTest(){
        LinkedList<Integer> list = new LinkedList<>();
        list.append(1);
        list.append(2);
        list.append(3);
        list.append(4);
        //get function uses zero indexing
        assertEquals(list.get(0),1);
    }
    @Test
    public void emptyGetTest(){
        LinkedList<Integer> list = new LinkedList<>();

        //get function uses zero indexing
        assertEquals(list.get(3),null);
    }
    @Test
    public void outsideRangeGetTest(){
        LinkedList<Integer> list = new LinkedList<>();
        list.append(1);
        list.append(2);
        //get function uses zero indexing
        assertEquals(list.get(2),null);
    }
    @Test
    public void containsTest(){
        LinkedList<Integer> list = new LinkedList<>();
        list.append(1);
        assertTrue(list.contains(1));
    }
    @Test
    public void notContainsTest(){
        LinkedList<Integer> list = new LinkedList<>();
        list.append(1);
        assertFalse(list.contains(2));
    }
    @Test
    public void emptyContains(){
        LinkedList<Integer> list = new LinkedList<>();
        assertFalse(list.contains(1));
    }



}
