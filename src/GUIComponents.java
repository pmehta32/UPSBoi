import javax.swing.*;
import java.awt.*;

public class GUIComponents {

    public static void setStaticComponents(Container container) {
        setTitle(container);
        setDistanceLabel(container);
        setRouteLabel(container);
        setMapLabel(container);
    }

    public static void setRouteScrollPane(Container container, PathDisplay travellingPath) {
        JScrollPane travellingPathScroll = new JScrollPane (travellingPath,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        travellingPathScroll.setLocation(400, 150);
        travellingPathScroll.setSize(400, 200);
        container.add(travellingPathScroll);
    }

    private static void setTitle(Container container) {
        JLabel title = new JLabel("TSP");
        title.setFont(new Font("Arial", Font.PLAIN, 40));
        title.setSize(300, 30);
        title.setLocation(300, 30);
        container.add(title);
    }

    private static void setDistanceLabel(Container container) {
        JLabel totalDistance = new JLabel("Total Distance:");
        totalDistance.setFont(new Font("Arial", Font.PLAIN, 20));
        totalDistance.setSize(200, 20);
        totalDistance.setLocation(100, 120);
        container.add(totalDistance);
    }

    private static void setRouteLabel(Container container) {
        JLabel route = new JLabel("Route:");
        route.setFont(new Font("Arial", Font.PLAIN, 20));
        route.setSize(200, 20);
        route.setLocation(100, 150);
        container.add(route);
    }

    private static void setMapLabel(Container container) {
        JLabel map = new JLabel("Map:");
        map.setFont(new Font("Arial", Font.PLAIN, 20));
        map.setSize(200, 20);
        map.setLocation(100, 370);
        container.add(map);
    }
}
