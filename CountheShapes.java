import java.util.*;

public class CountTheShapes {
    static class Point {
        int x, y;
        Point(int x, int y) { this.x = x; this.y = y; }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Point)) return false;
            Point p = (Point) o;
            return x == p.x && y == p.y;
        }
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        Map<Point, List<Point>> graph = new HashMap<>();
        List<Point[]> edges = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int x1 = sc.nextInt(), y1 = sc.nextInt();
            int x2 = sc.nextInt(), y2 = sc.nextInt();

            Point p1 = new Point(x1, y1);
            Point p2 = new Point(x2, y2);

            edges.add(new Point[]{p1, p2});

            graph.putIfAbsent(p1, new ArrayList<>());
            graph.putIfAbsent(p2, new ArrayList<>());
            graph.get(p1).add(p2);
            graph.get(p2).add(p1);
        }

        int V = graph.size();
        int E = N;
        int C = countComponents(graph);

        int closedFigures = E - V + C;
        System.out.print(closedFigures);
    }

    static int countComponents(Map<Point, List<Point>> graph) {
        Set<Point> visited = new HashSet<>();
        int count = 0;
        for (Point p : graph.keySet()) {
            if (!visited.contains(p)) {
                count++;
                dfs(p, visited, graph);
            }
        }
        return count;
    }

    static void dfs(Point p, Set<Point> visited, Map<Point, List<Point>> graph) {
        visited.add(p);
        for (Point nei : graph.get(p)) {
            if (!visited.contains(nei)) {
                dfs(nei, visited, graph);
            }
        }
    }
}