import java.io.*;

public class WriteObjectToFile {
    private ObjectOutputStream out;

    public WriteObjectToFile(String fileAddress) throws FileNotFoundException, IOException {
        out = new ObjectOutputStream(new FileOutputStream(new File(fileAddress)));
    }

    public void writeToFile(Object o) throws IOException {
        out.writeObject(o);
    }

    public void closeConnection() throws IOException {
        out.close();
    }
}