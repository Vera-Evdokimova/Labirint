// класс для чтения координат и поля из одного файла

public class Triplet {
    final Cell from;
    final Cell to;
    final int[][] labyrinth;

    public Triplet(Cell from, Cell to, int[][] labyrinth){
        this.from = from;
        this.to = to;
        this.labyrinth = labyrinth;
    }

}
