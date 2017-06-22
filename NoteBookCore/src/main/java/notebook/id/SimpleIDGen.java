package notebook.id;

/**
 * Created by Маша on 08.06.2017.
 */
public class SimpleIDGen implements IDGen {
    private static int lastID = -1;

    public int getNextID() {
        return ++lastID;
    }

    public static void setLastID(int lastID) {
        SimpleIDGen.lastID = lastID;
    }

    public static int getLastID() {
        return lastID;
    }
}
