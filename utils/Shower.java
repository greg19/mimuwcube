/** 
 * Nie jestem autorem tego pliku, ani żadnych innych plików w tym pakiecie.
 * Ten plik (i żaden inny plik w tym pakiecie) nie powinien podlegać ocenie.
 * Źródło: https://github.com/greg19/mimuwcube
 */

package utils;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * A utilities class, that beautifies a Cube.show() result. Combine different
 * modes with | operator.
 */
public class Shower {

    private Shower() {
    };

    /** Does not affect the view. */
    public static final int NONE = 0;
    /** Shows the view in a nice grid. */
    public static final int GRID = 1 << 0;
    /** Colors the numbers. Does nothing if SQUARES modifier is present. */
    public static final int COLOR = 1 << 1;
    /** Replaces the numbers with colored squares. */
    public static final int SQUARES = 1 << 2;

    private static final String[] COLORS = { "\u001B[37m", "\033[0;35m", "\u001B[32m", "\u001B[31m", "\u001B[34m",
            "\u001B[33m" };
    private static final String RESET = "\u001B[0m";

    private static int defaultMode = NONE;

    public static void setDefaultMode(int mode) {
        defaultMode = mode;
    }

    private static int getSize(int length) {
        int result = (int) Math.round(Math.sqrt(length / 6));
        if (6*result*result != length)
            throw new IllegalArgumentException("Given string length is not correct for any cube size");
        return result;
    }

    /**
     * Puts two strings side by side. Strings need to have the same number of rows.
     * @param s1      first string
     * @param s2      second string
     * @param joining a delimiter put in between each pair of rows
     * @return connected strings
     * @throws IllegalArgumentException if strings have different number of rows
     */
    public static String connect(String s1, String s2, String joining) {
        String[] t1 = s1.split("\n");
        String[] t2 = s2.split("\n");
        if (t1.length != t2.length)
            throw new IllegalArgumentException("Both strings should have the same number of rows");
        return IntStream.range(0, t1.length).mapToObj(i -> t1[i] + joining + t2[i]).collect(Collectors.joining("\n")) + "\n";
    }

    /**
     * Puts many strings side by side. Strings need to have the same number of rows.
     * @param joining a delimiter put in between each pair of rows
     * @param strings strings to connect
     * @return connected strings
     * @throws IllegalArgumentException if strings have different number of rows
     */
    public static String connectMany(String joining, String... strings) {
        String act = strings[0];
        for (int i = 1; i < strings.length; i++)
            act = connect(act, strings[i], joining);
        return act;
    }

    private static String grid(String str) {
        int size = getSize(str.length());
        String[] faces = str.split("(?<=\\G.{" + size * size + "})");
        String offset = (" ".repeat(size) + "\n").repeat(size);
        for (int i = 0; i < 6; i++)
            faces[i] = String.join("\n", faces[i].split("(?<=\\G.{" + size + "})")) + "\n";
        return connectMany(" ", offset, faces[0], offset, offset) + 
            connectMany(" ", faces[1], faces[2], faces[3], faces[4]) +
            connectMany(" ", offset, faces[5], offset, offset);
    }

    private static String map(String str, Function<Character, String> f) {
        return str.chars().mapToObj(c -> (char) c).map(c -> (c >= '0' && c <= '5' ? f.apply(c) : c.toString()))
                .collect(Collectors.joining());
    }

    /**
     * Beautifies given cube view using given mode.
     * @param view cube view
     * @param mode mode to apply
     * @return beautified cube view
     */
    public static String show(String view, int mode) {
        if ((mode & GRID) > 0)
            view = grid(view);
        if ((mode & SQUARES) > 0)
            view = map(view, c -> COLORS[c - '0'] + "▣" + RESET);
        else if ((mode & COLOR) > 0)
            view = map(view, c -> COLORS[c - '0'] + c.toString() + RESET);
        return view;
    }

    /**
     * Beautifies given cube view using default mode.
     * @param view cube view
     * @return beautified cube view
     */
    public static String show(String view) {
        return show(view, defaultMode);
    }

}
