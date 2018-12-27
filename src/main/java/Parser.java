import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class Parser {


    public static Triplet readWithCoords(String fileName) throws FileNotFoundException {
        int y = 0;
        int x = new Scanner(new FileReader(fileName)).nextLine().split(" ").length;
        int[][] labyrinth;
        Cell from;
        Cell to;
        Pattern pattern = Pattern.compile("\\(\\d,\\d\\)");
        List<String[]> strings = new ArrayList<>();

        try (Scanner reader = new Scanner(new FileReader(fileName))) {
            while (reader.hasNextLine()) {
                String str = reader.nextLine();
                String[] line = str.split(" ");
                if (line.length != x && !pattern.matcher(str).matches() && str.equals(" "))//проверка одинаковой длины всех строк
                    throw new IllegalArgumentException();
                strings.add(line);
                y++;
            }
            if (y == 0) { // проверка, что лабиринт не пустой
                throw new IllegalArgumentException("Код ошибки 3:лабиринт пустой");
            }

            labyrinth = new int[y-4][x];
            for (int i = 0; i < y - 4; i++) {
                String[] s = strings.get(i);
                for (int j = 0; j < x; j++) {
                    int symbol = Integer.parseInt(s[j]);
                    if (symbol == Solver.EMPTY || symbol == Solver.WALL) labyrinth[i][j] = symbol;
                    else throw new IllegalArgumentException("Код ошибки 4:введен некорректный символ");
                }
            }
            String fromCoords = strings.get(y - 3)[0];
            if (!pattern.matcher(fromCoords).matches()) {
                throw new IllegalArgumentException("Код ошибки 5:начальная координата не соответствует заданному формату");
            }
            int fromX = Character.getNumericValue(fromCoords.charAt(1));
            int fromY = Character.getNumericValue(fromCoords.charAt(3));
            from = new Cell(fromX, fromY);
            String toCoords = strings.get(y - 1)[0];
            if (!pattern.matcher(toCoords).matches()) {
                throw new IllegalArgumentException("Код ошибки 6:конечная координата не соответствует заданному формату");
            }
            int toX = Character.getNumericValue(toCoords.charAt(1));
            int toY = Character.getNumericValue(toCoords.charAt(3));
            to = new Cell(toX, toY);
        }
        return new Triplet(from, to, labyrinth);
    }

}