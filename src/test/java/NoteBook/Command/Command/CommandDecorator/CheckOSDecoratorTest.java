package NoteBook.Command.Command.CommandDecorator;

import NoteBook.Command.Command.Command;
import NoteBook.ModelAndView.Model.Message;
import NoteBook.ModelAndView.Model.Model;
import NoteBook.ModelAndView.ModelAndView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Маша on 19.06.2017.
 */
@RunWith(JUnit4.class)
public class CheckOSDecoratorTest {
    Model model;
    ModelAndView modelAndView;
    Command command;
    CheckOSDecorator decorator;

    @Before
    public void init() {
        model = mock(Model.class);
        modelAndView = mock(ModelAndView.class);
        command = mock(Command.class);
        decorator = spy(new CheckOSDecorator(command));

        when(command.execute()).thenReturn(modelAndView);
        when(modelAndView.getModel()).thenReturn(model);
    }

    @Test
    public void executeOSIsSameWithRecommended() {
        //given
        Map<String, String> testGlobalParams = new HashMap<>();
        testGlobalParams.put("Recommended_OS", "Windows_NT");

        when(command.getGlobalParams()).thenReturn(testGlobalParams);
        when(decorator.getCurrentOS()).thenReturn("Windows_NT");

        Message expectedMessage = new Message("Ваша ОС совпадает с рекомендованной", Message.INFO);

        //when
        decorator.execute();

        //then
        verify(model).addMessage(expectedMessage);
        verify(command.execute());
    }

    @Test
    public void executeOSIsNotSameWithRecommended() {
        //given
        Map<String, String> testGlobalParams = new HashMap<>();
        testGlobalParams.put("Recommended_OS", "Windows_NT");

        when(command.getGlobalParams()).thenReturn(testGlobalParams);
        when(decorator.getCurrentOS()).thenReturn("Linux");

        Message expectedMessage = new Message("Рекомендованная версия ОС: Windows_NT", Message.WARNING);

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
        when(command.getGlobalParams()).thenReturn(testGlobalParams);

        Message expectedMessage = new Message("Глобальный параметр Recommended_OS не задан", Message.WARNING);

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
}