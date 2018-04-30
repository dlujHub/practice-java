package FilterEarthQuake;

public class DistanceFilter implements Filter {
    private int dist;
    private Location loc;
    private String name ="Distance";
    DistanceFilter(int distance, Location location) {
        dist = distance;
       loc = location;
    }
    public String getName() { return name; }
    public boolean satisfies(QuakeEntry qe) {
        return qe.getLocation().distanceTo(loc) < dist;
    }
}
