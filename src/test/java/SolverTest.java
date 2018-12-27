import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class SolverTest {

    int[][] lab1 = {{0, 0, 0, 1, 0, 1, 0, 0, 0},
            {1, 0, 1, 1, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0}};


    @Test
    public void findPath() {
    }

    @Test
    public void getLabyrinthWithPath() {
        Solver solver = new Solver(lab1);
        List<Cell> path1 = new ArrayList<>();
        path1.add(new Cell(2, 2));
        path1.add(new Cell(1, 2));
        path1.add(new Cell(1, 1));
        path1.add(new Cell(1, 0));
        path1.add(new Cell(0, 0));
        assertEquals(path1, solver.findPath(0, 0, 2, 2));

        assertEquals(Collections.emptyList(), solver.findPath(0, 0, 3, 0));

        assertEquals(Collections.emptyList(), solver.findPath(3, 0, 6, 0));

        List<Cell> path2 = new ArrayList<>();
        path2.add(new Cell(0,0));
        assertEquals(path2, solver.findPath(0,0,0,0));


        try {
            solver.findPath(-1, 6, 4, 0) ;
        } catch (IllegalArgumentException e){
            assertEquals("Код ошибки 1:начальная точка выходит за пределы лабиринта", e.getMessage());
        }

        try {
            solver.findPath(0, 2, 8, 0) ;
        } catch (IllegalArgumentException e){
            assertEquals("Код ошибки 2:конечная точка выходит за пределы лабиринта", e.getMessage());
        }


    }


}