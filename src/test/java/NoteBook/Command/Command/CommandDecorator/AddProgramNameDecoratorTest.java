package NoteBook.Command.Command.CommandDecorator;

import NoteBook.Command.Command.Command;
import NoteBook.ModelAndView.Model.Message;
import NoteBook.ModelAndView.Model.Model;
import NoteBook.ModelAndView.ModelAndView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.ArgumentCaptor;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Маша on 19.06.2017.
 */
@RunWith(JUnit4.class)
public class AddProgramNameDecoratorTest {

    Model model;
    ModelAndView modelAndView;
    Command command;
    AddProgramNameDecorator decorator;

    @Before
    public void init() {
        model = mock(Model.class);
        modelAndView = mock(ModelAndView.class);
        command = mock(Command.class);
        decorator = new AddProgramNameDecorator(command);

        when(command.execute()).thenReturn(modelAndView);
        when(modelAndView.getModel()).thenReturn(model);
    }

    @Test
    public void executeAddProgramNameAndVersionInfo() {
        //given
        Map<String, String> testGlobalParams = new HashMap<>();
        testGlobalParams.put("Program_Name", "Test Program Name");
        testGlobalParams.put("Version", "1.0");

        when(command.getGlobalParams()).thenReturn(testGlobalParams);

        Message expectedMessage = new Message("Программа Test Program Name, версия 1.0", Message.INFO);

        //when
        decorator.execute();

        //then
        verify(model).addMessage(expectedMessage);
        verify(command.execute());
    }

    @Test
    public void executeAllGlobalParamsNotSpecified() {
        //given
        when(command.getGlobalParams()).thenReturn(null);

        Message expectedMessage = new Message("Глобальные параметры не установлены", Message.WARNING);

        //when
        decorator.execute();

        //then
        verify(model).addMessage(expectedMessage);
        verify(command.execute());
    }

    @Test
    public void executeSomeGlobalParamsNotSpecified() {
        //given
        Map<String, String> testGlobalParams = new HashMap<>();
        testGlobalParams.put("Program_Name", "Test Program Name");

        when(command.getGlobalParams()).thenReturn(testGlobalParams);

        Message expectedMessage = new Message("Глобальные параметры Program_Name и (или) Version не заданы", Message.WARNING);

        //when
        decorator.execute();

        //then
        verify(model).addMessage(expectedMessage);
        verify(command.execute());
    }
}