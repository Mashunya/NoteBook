package NoteBook.Command.Command;

import NoteBook.ModelAndView.Model.Message;
import NoteBook.ModelAndView.Model.MessageListModel;
import NoteBook.ModelAndView.Model.MessageStatus;
import NoteBook.ModelAndView.Model.Model;
import NoteBook.ModelAndView.ModelAndView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Маша on 19.06.2017.
 */
@RunWith(JUnit4.class)
public class AboutCommandTest {
    @Test
    public void executeShowGlobalParams() {
        //given
        Map<String, Object> testGlobalParams = new HashMap<>();
        testGlobalParams.put("Program_Name", "Test Program Name");
        testGlobalParams.put("Version", "1.0");
        testGlobalParams.put("About_Program", "Test program description");
        testGlobalParams.put("Author", "Maltseva Masha");

        AboutCommand command = new AboutCommand();

        String expectedMessageText = "Информация о программе:\n" +
                "Название программы: Test Program Name\n" +
                "Версия: 1.0\n" +
                "Краткое описание: Test program description\n" +
                "Автор: Maltseva Masha\n";

        Model resultModel = new MessageListModel();
        resultModel.addMessage(new Message(expectedMessageText, MessageStatus.INFO));

        ModelAndView expectedModelAndView = new ModelAndView("MessagesView", resultModel);

        //when
        ModelAndView resultModelAndView = command.execute(testGlobalParams);

        //then
        assertEquals(expectedModelAndView, resultModelAndView);
    }
}