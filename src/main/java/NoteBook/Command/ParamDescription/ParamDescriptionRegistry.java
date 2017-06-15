package NoteBook.Command.ParamDescription;

import NoteBook.Parsers.ParserRegistry;
import NoteBook.Validators.Validator;
import NoteBook.Validators.ValidatorRegistry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Маша on 15.06.2017.
 */
public class ParamDescriptionRegistry {
    private Map<String, ParamDescription> paramDescriptionMap;
    private static ParamDescriptionRegistry paramDescriptionRegistry;

    private ParamDescriptionRegistry() {
        paramDescriptionMap = new HashMap<>();

        ArrayList<Validator> validatorList = new ArrayList<>();
        validatorList.add(ValidatorRegistry.getInstance().getValidator("NotNegativeNumber"));

        ParamDescription.Builder builder;

        builder = ParamDescription.newBuilder();
        paramDescriptionMap.put("recordID",
                builder.required(true).validators(validatorList)
                        .parser(ParserRegistry.getInstance().getParser(Integer.class)).build());

        builder = ParamDescription.newBuilder();
        paramDescriptionMap.put("text", builder.required(true).build());

        builder = ParamDescription.newBuilder();
        paramDescriptionMap.put("author", builder.required(false).defaultValue("anonymous").build());

        builder = ParamDescription.newBuilder();
        paramDescriptionMap.put("type", builder.required(false).defaultValue("no type").build());

        builder = ParamDescription.newBuilder();
        paramDescriptionMap.put("title", builder.required(false).defaultValue("no title").build());
    }

    public static ParamDescriptionRegistry getInstance() {
        if(paramDescriptionRegistry == null) {
            paramDescriptionRegistry = new ParamDescriptionRegistry();
        }
        return paramDescriptionRegistry;
    }

    public ParamDescription getParamDescription(String paramName) {
        return paramDescriptionMap.get(paramName);
    }
}
