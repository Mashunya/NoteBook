package notebook.command.decorator;

import notebook.command.Command;
import notebook.model.Message;
import notebook.model.MessageStatus;
import notebook.model.Model;
import notebook.model.ModelAndView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

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
        decorator = Mockito.spy(new CheckOSDecorator(command));

        when(modelAndView.getModel()).thenReturn(model);
    }

    @Test
    public void executeOSIsSameWithRecommended() {
        //given
        Map<String, Object> testGlobalParams = new HashMap<>();
        testGlobalParams.put("Recommended_OS", "Windows_NT");

        when(command.execute(testGlobalParams)).thenReturn(modelAndView);
        when(decorator.getCurrentOS()).thenReturn("Windows_NT");

        Message expectedMessage = new Message("Ваша ОС совпадает с рекомендованной", MessageStatus.INFO);

        //when
        decorator.execute(testGlobalParams);

        //then
        verify(model).addMessage(expectedMessage);
        verify(command).execute(testGlobalParams);
    }

    @Test
    public void executeOSIsNotSameWithRecommended() {
        //given
        Map<String, Object> testGlobalParams = new HashMap<>();
        testGlobalParams.put("Recommended_OS", "Windows_NT");

        when(command.execute(testGlobalParams)).thenReturn(modelAndView);
        when(decorator.getCurrentOS()).thenReturn("Linux");

        Message expectedMessage = new Message("Рекомендованная версия ОС: Windows_NT", MessageStatus.WARNING);

        //when
        decorator.execute(testGlobalParams);

        //then
        verify(model).addMessage(expectedMessage);
        verify(command).execute(testGlobalParams);
    }

    @Test
    public void executeSomeGlobalParamsNotSpecified() {
        //given
        Map<String, Object> testGlobalParams = new HashMap<>();

        when(command.execute(testGlobalParams)).thenReturn(modelAndView);

        Message expectedMessage = new Message("Глобальный параметр Recommended_OS не задан", MessageStatus.WARNING);

        //when
        decorator.execute(testGlobalParams);

        //then
        verify(model).addMessage(expectedMessage);
        verify(command).execute(testGlobalParams);
    }
}