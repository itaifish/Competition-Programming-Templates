import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
  public static void main(String args[]) throws IOException {
    String[] filenames = new String[]{"a", "b", "c", "d", "e", "f"};
    for(String file : filenames) {
      run(file);
    }
    //run("a");
  }

  public static void run(String filename) throws IOException {
    final FastScanner fileScanner = new FastScanner(new FileInputStream(new File("src/main/resources/" + filename + ".txt")));
    final int duration = fileScanner.nextInt();
    final int intersections = fileScanner.nextInt();
    final int streets = fileScanner.nextInt();
    final int cars = fileScanner.nextInt();
    final int finishBonus = fileScanner.nextInt();
    Map<String, Street> streetMap = new HashMap<>();
    Map<String, Double> streetDensity = new HashMap<>();
    Intersection[] intersectionObjs = new Intersection[intersections];
    ArrayList<Car> carsList = new ArrayList<>();
    Map<String, Integer> carsWaitingAtStart = new HashMap<>();

    for(int i = 0; i < streets; i++) {
      final int startIntersection = fileScanner.nextInt();
      final int endIntersection = fileScanner.nextInt();
      final String name = fileScanner.next();
      final int time = fileScanner.nextInt();
      if(!streetMap.containsKey(name)) {
        streetMap.put(name, new Street(name, time, endIntersection));
        streetDensity.put(name, 0.0);
        carsWaitingAtStart.put(name, 0);
      }
      if(intersectionObjs[startIntersection] == null) {
        intersectionObjs[startIntersection] = new Intersection(startIntersection);
      }
      intersectionObjs[startIntersection].outgoingStreets.add(streetMap.get(name));
      if(intersectionObjs[endIntersection] == null) {
        intersectionObjs[endIntersection] = new Intersection(endIntersection);
      }
      intersectionObjs[endIntersection].incomingStreets.add(streetMap.get(name));
    }

    for(int i = 0; i < cars; i++) {
      final int numStreetsForCar = fileScanner.nextInt();
      Car car = null;
      for(int j = 0; j < numStreetsForCar; j++) {
        final String streetName = fileScanner.next();
        final Street str = streetMap.get(streetName);
        streetDensity.put(streetName, streetDensity.get(streetName) + 1);
        if(j == 0) {
          car = new Car(str);
          carsList.add(car);
          str.cars.add(car);
          carsWaitingAtStart.put(streetName, carsWaitingAtStart.get(streetName) + 1);
        } else {
          car.path.add(streetMap.get(streetName));
        }
      }
    }

//    for(Car car : carsList) {
//      double totalDistance = 0;
//      for(Street street : car.path) {
//        totalDistance += street.time;
//      }
//      for(Street street : car.path) {
//        streetDensity.put(street.name, streetDensity.get(street.name) + ( Math.sqrt(totalDistance)));
//      }
//    }


    // DONE PROCESSING DATA
    Map<Integer, ArrayList<Street>> result = new HashMap<>();
    // Now to compute answer
    final int TOTAL_CYCLE_TIME = Math.min(4, duration);
    for(Intersection intersection : intersectionObjs) {
      double totalDensity = 0;
      for(Street street : intersection.incomingStreets) {
        totalDensity += Math.sqrt(streetDensity.get(street.name));
      }
      if(totalDensity == 0) {
        continue;
      }
      result.put(intersection.id, new ArrayList<>());
      intersection.incomingStreets.sort((street1, street2) ->
          carsWaitingAtStart.get(street2.name) - carsWaitingAtStart.get(street1.name));
      for(Street street : intersection.incomingStreets) {
        if(streetDensity.get(street.name) == 0) {
          continue;
        }
        int trafficTime = (int)(TOTAL_CYCLE_TIME * (Math.sqrt(streetDensity.get(street.name)) / (double)totalDensity));
        street.trafficLightTime = Math.max(trafficTime, 1);
        //System.out.println(street.toString() + "\nDensity: " + streetDensity.get(street.name) + "\nTotal Density: " + totalDensity);
        result.get(intersection.id).add(street);
      }
    }
    //output
    final StringBuilder output = new StringBuilder();
    output.append(result.keySet().size()).append("\n");
    result.forEach((key, value) -> {
      output.append(key).append("\n");
      output.append(value.size()).append("\n");
      value.forEach( street -> {
        output.append(street.name).append(" ").append(street.trafficLightTime).append("\n");
      });
    });
    FileOutputStream outputStream = new FileOutputStream(new File("src/main/resources/out/" + filename + ".out.txt"));
    outputStream.write(output.toString().getBytes());
    outputStream.close();
  }
}
