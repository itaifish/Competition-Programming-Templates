import java.util.LinkedList;

public class Street {
  final int endIntersection;
  final String name;
  final int time;
  final LinkedList<Car> cars;
  int trafficLightTime;

  public Street(final String name, final int time, final int endIntersection) {
    this.endIntersection = endIntersection;
    this.name = name;
    this.time = time;
    this.cars = new LinkedList<>();
    this.trafficLightTime = 0;
  }

  @Override
  public String toString() {
    return "Street{" +
        "name='" + name + '\'' +
        ", time=" + time +
        ", cars=" + cars +
        ", trafficLightTime=" + trafficLightTime +
        '}';
  }
}