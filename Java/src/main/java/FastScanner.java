import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Code partially borrowed from:
 * https://codeforces.com/blog/entry/77287?#comment-621048
 */
public class FastScanner {

  private final BufferedReader bufferedReader;
  private StringTokenizer stringTokenizer;

  public FastScanner() {
    this(System.in);
  }

  public FastScanner(final InputStream input) {
    this.bufferedReader = new BufferedReader(new InputStreamReader(input));
    this.stringTokenizer = new StringTokenizer("");
  }

  public String next() {
    while (!stringTokenizer.hasMoreTokens())
      try {
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
      } catch (IOException ignored) {
      }
    return stringTokenizer.nextToken();
  }

  public int nextInt() {
    return Integer.parseInt(next());
  }

  public long nextLong() {
    return Long.parseLong(next());
  }
}
