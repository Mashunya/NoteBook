package NoteBook.Command.Command;

import NoteBook.Command.CommandResult.CommandResult;
import NoteBook.Exception.PropFileLoadException;
import NoteBook.GlobalProps;
import NoteBook.Services.NoteBookService;
import NoteBook.View.View;

import java.util.*;

/**
 * Created by Маша on 08.06.2017.
 */
//TODO interface with only execute + abstract with only notebookservice for commands that going to use it
public abstract class Command {

    protected NoteBookService noteBookService;
    protected List<CommandResult> results = new ArrayList<>();

    public void setNoteBookService(NoteBookService noteBookService) {
        this.noteBookService = noteBookService;
    }

    public abstract List<CommandResult> execute();
//TODO try to use decorator
    public void addProgramNameToResult() {
        try {
            String aboutProgram = "Программа " + GlobalProps.getProps().get("Program_Name") + ", версия " +GlobalProps.getProps().get("Version");
            results.add(new CommandResult(aboutProgram, CommandResult.INFO));
        } catch(PropFileLoadException ex) {
            results.add(new CommandResult("Не удалось загрузить глобальные параметры", CommandResult.WARNING));
        }
    }
    //TODO try to use decorator
    public void addCheckOSToResult() {
        try {
            String recommendedOS = GlobalProps.getProps().get("Recommended_OS");
            if(!recommendedOS.equals(System.getenv().get("OS"))) {
                results.add(new CommandResult("Рекомендованная версия ОС: " + recommendedOS, CommandResult.WARNING));
            }
        } catch(PropFileLoadException ex) {
            results.add(new CommandResult("Не удалось загрузить глобальные параметры", CommandResult.WARNING));
        }
    }
}
