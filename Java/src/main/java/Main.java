import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main {
  public static void main(String args[]) throws FileNotFoundException {

    final FastScanner scanner = new FastScanner();
    final FastScanner fileScanner = new FastScanner(new FileInputStream(new File("src/main/resources/test.txt")));
    System.out.println(fileScanner.nextInt());
    System.out.println(fileScanner.nextInt());
    System.out.println(fileScanner.nextInt());

  }
}
