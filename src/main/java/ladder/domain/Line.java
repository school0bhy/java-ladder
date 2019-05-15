package ladder.domain;

import java.util.ArrayList;
import java.util.List;

public class Line {
    private List<Boolean> points = new ArrayList<>();

    public Line(int lineLength, LadderRule rule) {
        generatePoints(lineLength,rule);
    }

    private void generatePoints(int lineLength, LadderRule rule){
        boolean before = false;
        for (int i = 0; i < lineLength - 1; i++) {
            points.add(!before && rule.isAvailablePoint());
            before = points.get(i);
        }
        points.add(false);
    }

    public int getLength() {
        return points.size();
    }

    public List<Boolean> getPoints() {
        return points;
    }

    public int move(int endPoint) {
        if(endPoint > 0) {
            if(points.get(endPoint-1)) return endPoint-1;
        }
        if(points.get(endPoint)){
            return endPoint+1;
        }
        return endPoint;
    }
}
