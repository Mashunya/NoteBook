package NoteBook.ModelAndView;

import NoteBook.ModelAndView.Model.Model;

/**
 * Created by Маша on 19.06.2017.
 */
public class ModelAndView {
    private String viewName;
    private Model model;

    public ModelAndView(String viewName, Model model) {
        this.viewName = viewName;
        this.model = model;
    }

    public String getViewName() {
        return viewName;
    }

    public Model getModel() {
        return model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ModelAndView that = (ModelAndView) o;

        if (!viewName.equals(that.viewName)) return false;
        return model.equals(that.model);
    }
}
