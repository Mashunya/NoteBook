package notebook.parsers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Маша on 19.06.2017.
 */
public class ParserRegistry {
    private Map<Class, Parser> parserMap;

    public ParserRegistry() {
        parserMap = new HashMap<>();
        parserMap.put(Integer.class, new IntParser());
        parserMap.put(Date.class, new DateParser());
    }

    public Parser getParser(Class parsedClass) {
        return parserMap.get(parsedClass);
    }
}
