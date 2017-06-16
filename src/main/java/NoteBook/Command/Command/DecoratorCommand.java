package NoteBook.Command.Command;

import NoteBook.Command.CommandResult.CommandResult;

import java.util.List;

/**
 * Created by Vovan on 16.06.2017.
 */
public class DecoratorCommand implements Command {
    Command delegate;

    public DecoratorCommand(Command delegate) {
        this.delegate = delegate;
    }

    @Override
    public List<CommandResult> execute() {
        List<CommandResult> execute = delegate.execute();
        results.add(//anything);
        return execute;
    }
}
