package notebook.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Created by Маша on 20.06.2017.
 */
@RunWith(JUnit4.class)
public class ViewResolverTest {
    @Test
    public void getViewReturnMessagesView() {
        //given
        ViewResolver viewResolver = new ViewResolver();

        //when
        View view = viewResolver.getView("MessagesView");

        //then
        assertEquals(MessagesView.class, view.getClass());
    }

    @Test
    public void getViewReturnRecordListView() {
        //given
        ViewResolver viewResolver = new ViewResolver();

        //when
        View view = viewResolver.getView("RecordListView");

        //then
        assertEquals(RecordListView.class, view.getClass());
    }

    @Test
    public void getViewReturnRecordView() {
        //given
        ViewResolver viewResolver = new ViewResolver();

        //when
        View view = viewResolver.getView("RecordView");

        //then
        assertEquals(RecordView.class, view.getClass());
    }
}