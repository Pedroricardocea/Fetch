package com.example.fetch;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void testParseJsonData() throws Exception {
        String json = "[{\"id\": 1, \"listId\": 1, \"name\": \"Item 1\"}, {\"id\": 2, \"listId\": 1, \"name\": \"Item 2\"}]";
        MainActivity activity = new MainActivity();
        List<JsonData> dataList = activity.parseJsonData(json);
        assertEquals(2, dataList.size());
        assertEquals(1, dataList.get(0).getID());
        assertEquals(1, dataList.get(0).getListID());
        assertEquals("Item 1", dataList.get(0).getName());
        assertEquals(2, dataList.get(1).getID());
        assertEquals(1, dataList.get(1).getListID());
        assertEquals("Item 2", dataList.get(1).getName());
    }

    // Add more unit tests for other methods if needed
}
