package model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
//import static org.mockito.Mockito.*;

public class DequeTest {
    @Mock
    org.apache.commons.logging.Log log;
    @Mock
    java.util.Queue<java.lang.Integer> headStack;
    @Mock
    java.util.Queue<java.lang.Integer> tailStack;
    @Mock
    java.util.Queue<java.lang.Integer> bufferStack;
    @InjectMocks
    model.Deque deque;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddFirst() throws Exception {
        boolean result = deque.addFirst(Integer.valueOf(0));
        Assert.assertEquals(true, result);
    }

    @Test
    public void testAddLast() throws Exception {
        boolean result = deque.addLast(Integer.valueOf(0));
        Assert.assertEquals(true, result);
    }

    @Test
    public void testGetFirst() throws Exception {
        java.lang.Integer result = deque.getFirst();
        Assert.assertEquals(Integer.valueOf(0), result);
    }

    @Test
    public void testGetLast() throws Exception {
        java.lang.Integer result = deque.getLast();
        Assert.assertEquals(Integer.valueOf(0), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme