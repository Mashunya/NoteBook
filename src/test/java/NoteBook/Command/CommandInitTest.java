package NoteBook.Command;

import NoteBook.Exception.IllegalCommandParamException;
import NoteBook.Exception.NegativeNumberException;
import NoteBook.Exception.NullObjectValidateException;
import NoteBook.Exception.ValidateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by Маша on 13.06.2017.
 */
@RunWith(JUnit4.class)
public class CommandInitTest {

    @Test
    public void initParams_addCommand() throws IllegalCommandParamException {
        CommandInit commandInit = new CommandInit();
        AddCommand command = new AddCommand();

        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("text", "some text");
        paramsMap.put("title", "some title");
        paramsMap.put("author", "some author");
        paramsMap.put("type", "some type");

        commandInit.initParams(command, paramsMap);

        assertEquals("some text", command.getText());
        assertEquals("some title", command.getTitle());
        assertEquals("some author", command.getAuthor());
        assertEquals("some type", command.getType());
    }

    @Test
    public void initParams_deleteCommand() throws IllegalCommandParamException {
        CommandInit commandInit = new CommandInit();
        DeleteCommand command = new DeleteCommand();

        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("recordID", "0");

        commandInit.initParams(command, paramsMap);

        assertEquals(0, (int)command.getRecordID());
    }

    @Test(expected = IllegalCommandParamException.class)
    public void initParams_deleteCommandIllegalParam() throws IllegalCommandParamException {
        CommandInit commandInit = new CommandInit();
        Command command = new DeleteCommand();

        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("recordID", "not_number");

        commandInit.initParams(command, paramsMap);
    }

    @Test
    public void initParams_finaByIDCommand() throws IllegalCommandParamException {
        CommandInit commandInit = new CommandInit();
        FindByIDCommand command = new FindByIDCommand();

        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("recordID", "0");

        commandInit.initParams(command, paramsMap);

        assertEquals(0, (int)command.getRecordID());
    }

    @Test(expected = IllegalCommandParamException.class)
    public void initParams_finaByIDCommandIllegalParam() throws IllegalCommandParamException {
        CommandInit commandInit = new CommandInit();
        Command command = new FindByIDCommand();

        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("recordID", "not_number");

        commandInit.initParams(command, paramsMap);
    }

    @Test
    public void initParams_finaAllCommand() throws IllegalCommandParamException {
        CommandInit commandInit = new CommandInit();
        FindAllCommand command = new FindAllCommand();

        Map<String, String> paramsMap = new HashMap<>();

        commandInit.initParams(command, paramsMap);
    }

    @Test
    public void initParams_helpCommand() throws IllegalCommandParamException {
        CommandInit commandInit = new CommandInit();
        HelpCommand command = new HelpCommand();

        Map<String, String> paramsMap = new HashMap<>();

        commandInit.initParams(command, paramsMap);
    }

    @Test
    public void validate_allIsValid() throws ValidateException {
        CommandInit commandInit = new CommandInit();
        DeleteCommand command = new DeleteCommand();

        command.setRecordID(12);

        commandInit.validate(command);
    }

    @Test(expected = NullObjectValidateException.class)
    public void validate_requiredFieldIsNull() throws ValidateException {
        CommandInit commandInit = new CommandInit();
        DeleteCommand command = new DeleteCommand();

        commandInit.validate(command);
    }

    @Test(expected = NegativeNumberException.class)
    public void validate_zeroOrNaturalFieldIsNegative() throws ValidateException {
        CommandInit commandInit = new CommandInit();
        DeleteCommand command = new DeleteCommand();

        command.setRecordID(-12);

        commandInit.validate(command);
    }
}