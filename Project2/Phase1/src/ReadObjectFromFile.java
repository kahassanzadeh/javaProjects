import java.io.*;

public class ReadObjectFromFile {
    private ObjectInputStream in;

    public ReadObjectFromFile(String address) throws FileNotFoundException, IOException {
        in = new ObjectInputStream(new FileInputStream(new File(address)));
    }

    public Object readFromFile() throws ClassNotFoundException, IOException {
        return in.readObject();
    }

    public void closeConnection() throws IOException {
        in.close();
    }
}
