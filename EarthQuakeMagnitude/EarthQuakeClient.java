package EarthQuakeMagnitude;

import java.util.ArrayList;

public class EarthQuakeClient {

      private ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData, double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<>();
        for (int i=0; i<quakeData.size(); i++) {
            QuakeEntry curr = quakeData.get(i);
            if (curr.getMagnitude() > magMin) {
                answer.add(curr);
            }
        }

        return answer;
    }

    private ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, double distMax, Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
       for (QuakeEntry qe : quakeData) {
           Location loc = qe.getLocation();
           if (loc.distanceTo(from) < distMax) {
               answer.add(qe);
           }
       }

        return answer;
    }

    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth) {
        ArrayList<QuakeEntry> answer = new ArrayList<>();
        for (QuakeEntry qe : quakeData) {
            double depth = qe.getDepth();
            if (depth<maxDepth && depth>minDepth) {
                answer.add(qe);
            }
        }
        return answer;
    }

    private ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String named, String phrase) {
        ArrayList<QuakeEntry> answer = new ArrayList<>();
        for (QuakeEntry qe : quakeData) {
            String title = qe.getInfo();


            switch (named) {
                case "start":
                    if(title.startsWith(phrase)) answer.add(qe);
                    break;
                case "end":
                    if(title.endsWith(phrase)) answer.add(qe);
                    break;
                case "any":
                    if(title.contains(phrase)) answer.add(qe);
                    break;
            }
            }
        return answer;
    }

    public void quakesByPhrase() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "EarthQuakeMagnitude/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        ArrayList<QuakeEntry> phr = filterByPhrase(list, "end", "California");
        for (QuakeEntry qe : phr) {
            System.out.println(qe);
        }
        System.out.println("Found " + phr.size() + " quakes that match California at end");


        phr = filterByPhrase(list, "any", "can");
        for (QuakeEntry qe : phr) {
            System.out.println(qe);
        }
        System.out.println("Found " + phr.size() + " quakes that match can at any");


        phr = filterByPhrase(list, "any", "Creek");
        for (QuakeEntry qe : phr) {
            System.out.println(qe);
        }
        System.out.println("Found " + phr.size() + " quakes that match Creek at any");

    }
    public void quakesOfDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "EarthQuakeMagnitude/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> dep = filterByDepth(list, -10000.0, -8000.0);
        for (QuakeEntry qe : dep) {
            System.out.println(qe);
        }
        System.out.println("total " + dep.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "EarthQuakeMagnitude/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        ArrayList<QuakeEntry> bigList = filterByMagnitude(list, 5.0);
        for (QuakeEntry qe : bigList) {
            System.out.println(qe);
        }
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "EarthQuakeMagnitude/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        Location durham = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
       //  Location city =  new Location(38.17, -118.82);
        System.out.println("For Durham>>");
         ArrayList<QuakeEntry> dist = filterByDistanceFrom(list, 1000*1000, durham);
        for (QuakeEntry qe : dist) {
            System.out.println(qe.getLocation().distanceTo(durham)/1000 + "\t" + qe.getInfo());
        }
        System.out.println("For Bridgeport>>");
        Location city =  new Location(38.17, -118.82);
      dist = filterByDistanceFrom(list, 1000*1000, city);
        for (QuakeEntry qe : dist) {
            System.out.println(qe.getLocation().distanceTo(city)/1000 + "\t" + qe.getInfo());
        }

    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "EarthQuakeMagnitude/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }



    
}
