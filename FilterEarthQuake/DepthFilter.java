package FilterEarthQuake;

public class DepthFilter implements Filter {
    private double min;
    private double max;
    private String name ="Depth";
    DepthFilter(double minDep, double maxDep) {
        min = minDep;
        max = maxDep;
    }
    public String getName() { return name; }


    public boolean satisfies(QuakeEntry qe) {
        return qe.getDepth() <= max && qe.getDepth() >= min;
    }
}
