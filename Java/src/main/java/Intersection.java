import java.util.ArrayList;

public class Intersection {
  public ArrayList<Street> incomingStreets;
  public ArrayList<Street> outgoingStreets;
  public int id;

  public Intersection(int id) {
    incomingStreets = new ArrayList<>();
    outgoingStreets = new ArrayList<>();
    this.id = id;
  }

  @Override
  public String toString() {
    return "Intersection{" +
        "incomingStreets=" + incomingStreets +
        ", outgoingStreets=" + outgoingStreets +
        ", id=" + id +
        '}';
  }
}
