package NoteBook.Command.CommandDescription;

import NoteBook.Command.Command.FindByIDCommand;
import NoteBook.Command.ParamDescription.ParamDescription;
import NoteBook.Validators.ValidatorRegistry;

/**
 * Created by Маша on 16.06.2017.
 */
public class FindByIDCommandDescription extends CommandDescription {
    public FindByIDCommandDescription(String commandName) {
        super(commandName, FindByIDCommand.class);

        ParamDescription.Builder builder;

        builder = ParamDescription.newBuilder();
        paramsDescription.add(builder.paramName("recordID").paramClass(Integer.class).required(true)
                .validators(ValidatorRegistry.getInstance().getValidator("NotNegativeNumber")).build());
    }
}
