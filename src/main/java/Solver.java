import java.util.*;

public class Solver {

    public static final int WALL = 1;
    public static final int EMPTY = 0;

    private int[][] labyrinth;

    public Solver(int[][] labyrinth) {
        this.labyrinth = labyrinth;
    }

    List<Cell> findPath(int fromX, int fromY, int toX, int toY) {
        int x = labyrinth[0].length;
        int y = labyrinth.length;
        if (fromX >= x || fromX < 0 || fromY >= y || fromY < 0) throw new IllegalArgumentException("Код ошибки 1:начальная точка выходит за пределы лабиринта");
        if (toX >= x || toX < 0 || toY >= y || toY < 0) throw new IllegalArgumentException("Код ошибки 2:конечная точка выходит за пределы лабиринта");
        if (labyrinth[fromY][fromX]==WALL){
            return Collections.emptyList();
        }
        int[][] tmp = new int[y][x];
        Queue<Cell> queue = new ArrayDeque<>();
        if (tmp[fromY][fromX] == WALL) return Collections.emptyList();
        tmp[fromY][fromX] = 2; // условие не позволяет начать с 0 или 1
        queue.add(new Cell(fromX, fromY));
        while (!queue.isEmpty()) {
            Cell current = queue.poll();
            int curX = current.x;
            int curY = current.y;
            if (curX == toX && curY == toY) break;
            for (Cell cell : getNeighbours(current)) {
                if (tmp[cell.y][cell.x] == EMPTY && labyrinth[cell.y][cell.x] == EMPTY) {
                    tmp[cell.y][cell.x] = tmp[curY][curX] + 1;
                    queue.add(cell);
                }
            }
        }


        if (tmp[toY][toX] < 2) {
            return Collections.emptyList();
        }


        List<Cell> cells = new ArrayList<>();
        Cell current = new Cell(toX, toY);
        cells.add(current);

        while (!(current.x == fromX && current.y == fromY)) {
            Cell prev = getPrev(current, tmp);
            cells.add(prev);
            current = prev;
        }

        return cells;
    }

    private Cell getPrev(Cell current, int[][] tmp) {
        for (Cell neighbour : getNeighbours(current)) {
            if (tmp[neighbour.y][neighbour.x] == tmp[current.y][current.x] - 1) {
                return neighbour;
            }
        }
        return null;
    }

    public int[][] getLabyrinthWithPath(int fromX, int fromY, int toX, int toY) {
        List<Cell> path = findPath(fromX, fromY, toX, toY);
        int x = labyrinth[0].length;
        int y = labyrinth.length;
        int[][] res = new int[y][x];
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (path.contains(new Cell(j, i))) res[i][j] = 7; // символ пути
                else res[i][j] = labyrinth[i][j];
            }
        }
        return res;

    }

    private List<Cell> getNeighbours(Cell cell) {
        int curX = cell.x;
        int curY = cell.y;
        int x = labyrinth[0].length;
        int y = labyrinth.length;
        List<Cell> res = new ArrayList<>();
        if (curX + 1 < x) {
            res.add(new Cell(curX + 1, curY));
        }
        if (curX - 1 >= 0) {
            res.add(new Cell(curX - 1, curY));
        }
        if (curY + 1 < y) {
            res.add(new Cell(curX, curY + 1));
        }
        if (curY - 1 >= 0) {
            res.add(new Cell(curX, curY - 1));
        }

        return res;
    }
}
