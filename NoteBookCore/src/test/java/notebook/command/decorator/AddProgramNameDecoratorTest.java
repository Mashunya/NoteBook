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

import java.util.HashMap;
import java.util.Map;

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

        when(modelAndView.getModel()).thenReturn(model);
    }

    @Test
    public void executeAddProgramNameAndVersionInfo() {
        //given
        Map<String, Object> testGlobalParams = new HashMap<>();
        testGlobalParams.put("Program_Name", "Test Program Name");
        testGlobalParams.put("Version", "1.0");

        when(command.execute(testGlobalParams)).thenReturn(modelAndView);

        Message expectedMessage = new Message("Программа Test Program Name, версия 1.0", MessageStatus.INFO);

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
        testGlobalParams.put("Program_Name", "Test Program Name");

        when(command.execute(testGlobalParams)).thenReturn(modelAndView);

        Message expectedMessage = new Message("Глобальные параметры Program_Name и (или) Version не заданы", MessageStatus.WARNING);

        //when
        decorator.execute(testGlobalParams);

        //then
        verify(model).addMessage(expectedMessage);
        verify(command).execute(testGlobalParams);
    }
}