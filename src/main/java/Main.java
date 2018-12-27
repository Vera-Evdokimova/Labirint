import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.*;
import java.util.Arrays;

public class Main {


    @Argument(required = true, metaVar = "InputName", usage = "Input file name")
    private String inputFileName;

    @Option(name = "-o", metaVar = "OutputName", usage = "Output file name")
    private String outputFileName;


    public static void main(String[] args) {
        new Main().launch(args);
    }

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("");
            parser.printUsage(System.err);
            return;
        }

        if (outputFileName == null) {
            outputFileName = getOutputFileName();
        }
        int res[][] = null;

        Triplet triplet = null;
        try {
            try {
                triplet = Parser.readWithCoords(inputFileName);
            } catch (FileNotFoundException e) {
                System.out.println("Код ошибки 7:ошибка ввода для файла "+ inputFileName);
                e.printStackTrace();
            }
            Solver solver = new Solver(triplet.labyrinth);
            res = solver.getLabyrinthWithPath(triplet.from.x, triplet.from.y, triplet.to.x, triplet.to.y);

            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFileName)))) {
                for (int[] r : res) {
                    writer.write(Arrays.toString(r));
                    writer.newLine();
                }
            } catch (IOException e) {

                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("Код ошибки 8:ошибка вывода для файла "+ outputFileName);
            System.out.println(e.getMessage());
        }
    }

    private String getOutputFileName() {
        return inputFileName + "solved.txt";
    }



}
