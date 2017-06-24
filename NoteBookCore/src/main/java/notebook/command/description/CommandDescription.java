package notebook.command.description;

import notebook.command.params.ParamDescription;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Маша on 15.06.2017.
 */
public class CommandDescription {

    private Class commandClass;
    private Collection<ParamDescription> paramsDescription;
    private List<Class> decorators;

    private CommandDescription(Class commandClass) {
        this.commandClass = commandClass;
        this.paramsDescription = new ArrayList<>();
        this.decorators = new ArrayList<>();
    }

    public Class getCommandClass() {
        return commandClass;
    }

    public Collection<ParamDescription> getParamsDescription() {
        return paramsDescription;
    }

    public List<Class> getDecorators() {
        return decorators;
    }

    public static Builder newBuilder(Class commandClass) {
        return new CommandDescription(commandClass).new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder paramDescription(Collection<ParamDescription> paramDescription) {
            CommandDescription.this.paramsDescription = paramDescription;
            return this;
        }

        public Builder decorator(Class decorator) {
            CommandDescription.this.decorators.add(decorator);
            return this;
        }

        public Builder decorators(List<Class> decorators) {
            CommandDescription.this.decorators = decorators;
            return this;
        }

        public CommandDescription build() {
            return CommandDescription.this;
        }
    }
}
