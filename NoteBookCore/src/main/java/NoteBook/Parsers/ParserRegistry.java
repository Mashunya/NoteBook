package NoteBook.Parsers;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Маша on 19.06.2017.
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

    public Parser getParser(Class parsedClass) {
        return parserMap.get(parsedClass);
    }
}
