package notebook.view;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Маша on 19.06.2017.
 */
public class ViewResolver {
    private Map<String, View> viewMap;

    public ViewResolver() {
        viewMap = new HashMap<>();
        viewMap.put("MessagesView", new MessagesView());
        viewMap.put("RecordView", new RecordView());
        viewMap.put("RecordListView", new RecordListView());
    };

    public View getView(String viewName) {
        return viewMap.get(viewName);
    }
}
