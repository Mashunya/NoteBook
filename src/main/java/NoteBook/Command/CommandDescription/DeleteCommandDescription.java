package NoteBook.Command.CommandDescription;

import NoteBook.Command.Command.DeleteCommand;
import NoteBook.Command.ParamDescription.ParamDescription;
import NoteBook.Validators.ValidatorRegistry;

/**
 * Created by Маша on 15.06.2017.
 */
public class DeleteCommandDescription extends CommandDescription {
    public DeleteCommandDescription(String commandName) {
        super(commandName, DeleteCommand.class);

        ParamDescription.Builder builder;

        builder = ParamDescription.newBuilder();
        paramsDescription.add(builder.paramName("recordID").required(true)
                .validators(ValidatorRegistry.getInstance().getValidator("NotNegativeNumber")).build());
    }
}
