package NoteBook.Command.CommandDescription;

import NoteBook.Command.Command.AddCommand;
import NoteBook.Command.ParamDescription.ParamDescription;

/**
 * Created by Маша on 15.06.2017.
 */
public class AddCommandDescription extends CommandDescription {
    public AddCommandDescription(String commandName) {
        super(commandName, AddCommand.class);

        ParamDescription.Builder builder;

        builder = ParamDescription.newBuilder();
        paramsDescription.add(builder.paramName("text").required(true).build());

        builder = ParamDescription.newBuilder();
        paramsDescription.add(builder.paramName("author").required(false).defaultValue("anonymous").length(200).build());

        builder = ParamDescription.newBuilder();
        paramsDescription.add(builder.paramName("type").required(false).defaultValue("no type").length(100).build());

        builder = ParamDescription.newBuilder();
        paramsDescription.add(builder.paramName("title").required(false).defaultValue("no title").length(200).build());
    }
}
