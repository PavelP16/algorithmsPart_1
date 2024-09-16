package test;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayListTest {
    private ArrayList list;

    @Before
    public void setUp() {
        list = new ArrayList(5);
    }

    @Test
    public void testAddElement() {
        String result = list.add("Hello");
        assertEquals("Hello", result);
    }

    @Test(expected = NullElementException.class)
    public void testAddNullElement() {
        list.add(null);
    }

    @Test
    public void testGetElement() {
        list.add("Hello");
        assertEquals("Hello", list.get(0));
    }

    @Test(expected = ElementNotFoundException.class)
    public void testGetNonExistentElement() {
        list.get(0);
    }

    @Test
    public void testRemoveElement() {
        list.add("Hello");
        String removed = list.remove(0);
        assertEquals("Hello", removed);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveFromEmptyList() {
        list.remove(0);
    }

    @Test
    public void testSize() {
        list.add("Hello");
        list.add("World");
        assertEquals(2, list.size());
    }

    @Test
    public void testClear() {
        list.add("Hello");
        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    public void testCapacityIncrease() {
        for (int i = 0; i < 10; i++) {
            list.add("Element " + i);
        }
        assertEquals(10, list.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetInvalidIndex() {
        list.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveInvalidIndex() {
        list.remove(0);
    }
}