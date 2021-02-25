import java.util.LinkedList;

public class Car {
  public Street currentStreet;
  public LinkedList<Street> path;

  public Car(Street currentStreet) {
    this.path = new LinkedList<>();
    this.currentStreet = currentStreet;
  }

  public String toString() {
    StringBuilder output = new StringBuilder();
    path.forEach(street -> {
      output.append(street.name).append("->");
    });
    return output.toString();
  }

}
