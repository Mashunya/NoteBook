package notebook.command.description;

import notebook.command.params.ParamDescription;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Маша on 15.06.2017.
 */
public class CommandDescription {

    protected Class commandClass;
    protected Collection<ParamDescription> paramsDescription;

    public CommandDescription(Class commandClass) {
        this.commandClass = commandClass;
        this.paramsDescription = new ArrayList<>();
    }

    public CommandDescription(Class commandClass, Collection<ParamDescription> paramsDescription) {
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
