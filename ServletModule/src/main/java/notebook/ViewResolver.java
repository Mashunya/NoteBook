package notebook;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Маша on 22.06.2017.
 */
public class ViewResolver {
    private Map<String, String> viewMap;

    public ViewResolver() {
        viewMap = new HashMap<>();
        viewMap.put("MessagesView", "messages.jsp");
        viewMap.put("RecordView", "record.jsp");
        viewMap.put("RecordListView", "recordList.jsp");
    };

    public String getView(String viewName) {
        return viewMap.get(viewName);
    }
}
