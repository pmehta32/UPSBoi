import java.util.ArrayList;

public class MinMaxScaler {
    float min = 0;
    float max = 0;

    public MinMaxScaler(ArrayList coordinates) {
        setScaler(coordinates);
    }

    private void setScaler(ArrayList coordinates) {
        float maxNum = -1;
        float minNum = -1;
        for(int i=0; i<coordinates.size(); i++) {
            ArrayList point = (ArrayList) coordinates.get(i);
            if(maxNum < Math.max((float)point.get(0), (float)point.get(1))) {
                maxNum = Math.max((float)point.get(0), (float)point.get(1));
            }
            if (minNum == -1 || minNum > Math.min((float)point.get(0), (float)point.get(1))) {
                minNum = Math.min((float)point.get(0), (float)point.get(1));
            }
        }
        System.out.println("Max number: " + maxNum);
        System.out.println("Min number: " + minNum);
        max = maxNum;
        min = minNum;
    }

    public float scaleValue(float x) {
        return (x-min)/(max-min);
    }

}
