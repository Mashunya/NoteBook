package NoteBook.Parsers;

import NoteBook.Validators.NotNegativeNumberValidator;
import NoteBook.Validators.NotNullValidator;
import NoteBook.Validators.Validator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Маша on 15.06.2017.
 */
public class ParserRegistry {
    private Map<Class, Parser> parserMap;
    private static ParserRegistry parserRegistry;

    private ParserRegistry() {
        parserMap = new HashMap<>();
        parserMap.put(Integer.class, new IntParser());
    }

    public static ParserRegistry getInstance() {
        if(parserRegistry == null) {
            parserRegistry = new ParserRegistry();
        }
        return parserRegistry;
    }

    public Parser getParser(Class parserClass) {
        return parserMap.get(parserClass);
    }
}
