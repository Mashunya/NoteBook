package NoteBook.Command.CommandDescription;

import NoteBook.Command.ParamDescription.ParamDescription;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Маша on 15.06.2017.
 */
public class CommandDescription {

    //todo: get rid of command
    protected String commandName;
    protected Class commandClass;
    protected Collection<ParamDescription> paramsDescription;

    public CommandDescription(String commandName, Class commandClass) {
        this.commandName = commandName;
        this.commandClass = commandClass;
        this.paramsDescription = new ArrayList<>();
    }

    public CommandDescription(String commandName, Class commandClass, Collection<ParamDescription> paramsDescription) {
        this.commandName = commandName;
        this.commandClass = commandClass;
        this.paramsDescription = paramsDescription;
    }

    public Class getCommandClass() {
        return commandClass;
    }

    public Collection<ParamDescription> getParamsDescription() {
        return paramsDescription;
    }
}
