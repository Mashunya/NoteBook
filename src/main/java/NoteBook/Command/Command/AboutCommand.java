package NoteBook.Command.Command;

import NoteBook.Command.CommandResult.CommandResult;
import NoteBook.Exception.PropFileLoadException;
import NoteBook.GlobalProps;

import java.util.List;
import java.util.Map;

/**
 * Created by Маша on 16.06.2017.
 */
public class AboutCommand extends Command {
    @Override
    public List<CommandResult> execute() {
        try {
            Map<String, String> globalProps = GlobalProps.getProps();
            String aboutProgram = "Информация о программе:\n";
            for(Map.Entry prop: globalProps.entrySet()) {
                aboutProgram += (prop.getKey() + ": " + prop.getValue() + "\n");
            }
            results.add(new CommandResult(aboutProgram, CommandResult.INFO));
        } catch(PropFileLoadException ex) {
            results.add(new CommandResult("Не удалось загрузить глобальные параметры", CommandResult.ERROR));
        }

        return results;
    }
}
