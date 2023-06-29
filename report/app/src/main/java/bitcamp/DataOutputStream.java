package bitcamp;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataOutputStream extends FileOutputStream {

  public DataOutputStream(String name) throws FileNotFoundException {
    super(name);

  }

  public void writeInt(int v) throws IOException {
    this.write(v >> 24);
    this.write(v >> 16);
    this.write(v >> 8);
    this.write(v);
  }
}
