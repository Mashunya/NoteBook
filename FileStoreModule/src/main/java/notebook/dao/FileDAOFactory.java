package notebook.dao;

import notebook.PropsLoader;
import notebook.dao.exception.ContextException;
import notebook.dao.exception.ExecuteException;
import notebook.dao.exception.FindDAOException;
import notebook.entity.Record;
import notebook.exception.PropFileLoadException;
import notebook.exception.ResourceNotFoundException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Маша on 25.06.2017.
 */
public class FileDAOFactory extends DAOFactory<File> {

    public FileDAOFactory() throws ContextException, ResourceNotFoundException, PropFileLoadException {
        File context = getContext();
        DAOMap = new HashMap<>();
        DAOMap.put(Record.class, new FileRecordDAO(context));
    }

    @Override
    public File getContext() throws ContextException, PropFileLoadException, ResourceNotFoundException {
        PropsLoader propsLoader = new PropsLoader("config.properties");
        String filename = propsLoader.loadProp("filename");
        File file = new File(filename);
        try {
            if (!file.exists()) {
                createNewFile(file);
            }
            return file;
        } catch(IOException ex) {
            throw new ContextException(ex);
        }
    }

    private void createNewFile(File file) throws IOException {
        file.createNewFile();
        FileOutputStream fileOut = new FileOutputStream(file);
        ObjectOutputStream outputStream = new ObjectOutputStream(fileOut);
        outputStream.writeObject(new ArrayList<Record>());
        outputStream.close();
        fileOut.close();
    }
}
