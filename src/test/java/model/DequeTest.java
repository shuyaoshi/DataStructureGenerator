package model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DequeTest {
    private Deque deque;

    @Before
    public void setUp() {
        deque = new Deque();
    }

    @After
    public void tearDown() {
        deque.clear();
    }

    @Test
    public void testFirstFunctions() throws Exception {
        deque.offerFirst(1);
        deque.offerFirst(2);
        Assert.assertEquals(2, deque.peekFirst().intValue());
        Assert.assertEquals(2, deque.size());
        Assert.assertEquals(2, deque.pollFirst().intValue());
        Assert.assertEquals(1, deque.pollFirst().intValue());
        Assert.assertTrue(deque.isEmpty());
    }

    @Test
    public void testLastFunctions() throws Exception {
        deque.offerLast(3);
        deque.offerLast(4);
        Assert.assertEquals(4, deque.peekLast().intValue());
        Assert.assertEquals(2, deque.size());
        Assert.assertEquals(4, deque.pollLast().intValue());
        Assert.assertEquals(3, deque.pollLast().intValue());
        Assert.assertTrue(deque.isEmpty());
    }

    @Test
    public void testRebalanceFunction() throws Exception {
        deque.offerFirst(3);
        deque.offerFirst(100);
        Assert.assertEquals(3, deque.peekLast().intValue());
        Assert.assertEquals(100, deque.pollFirst().intValue());
        Assert.assertEquals(3, deque.pollFirst().intValue());
        Assert.assertTrue(deque.isEmpty());
    }
}