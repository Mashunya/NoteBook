package NoteBook.ModelAndView.View;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Маша on 19.06.2017.
 */
public class ViewResolver {
    private Map<String, View> viewMap;
    private static ViewResolver viewResolver;

    private ViewResolver() {
        viewMap = new HashMap<>();
        viewMap.put("MessagesView", new MessagesView());
        viewMap.put("RecordView", new RecordView());
        viewMap.put("RecordListView", new RecordListView());
    };

    public static View getView(String viewName) {
        if(viewResolver == null) {
            viewResolver = new ViewResolver();
        }
        return viewResolver.viewMap.get(viewName);
    }
}
