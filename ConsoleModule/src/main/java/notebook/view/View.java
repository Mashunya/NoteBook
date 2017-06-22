package notebook.view;

import notebook.model.Model;

/**
 * Created by Маша on 11.06.2017.
 */
public interface View<T extends Model> {
    void show(T model);
}
