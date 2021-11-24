/** 
 * Nie jestem autorem tego pliku, ani żadnych innych plików w tym pakiecie.
 * Ten plik (i żaden inny plik w tym pakiecie) nie powinien podlegać ocenie.
 * Źródło: https://github.com/greg19/mimuwcube
 */

package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static java.util.Map.entry;

import concurrentcube.Cube;

/**
 * A utilities class, that parses Rubik's cube notation, and applies moves to a
 * cube instance.
 */
public class Mover {

    private Mover() {
    };

    private static void rotate(Cube cube, int side, int layer) {
        try {
            cube.rotate(side, layer);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void smallLetter(Cube cube, int side) {
        if (cube.getSize() == 3) {
            rotate(cube, side, 0);
            rotate(cube, side, 1);
        }
        else if (cube.getSize() == 4) {
            rotate(cube, side, 1);
        }
        else {
            throw new IllegalArgumentException("Small letters can only be applied to 3x3 and 4x4 cubes");
        }
    };

    private static final Map<String, Consumer<Cube>> moves = Map.ofEntries(
            entry("U", cube -> rotate(cube, 0, 0)),
            entry("L", cube -> rotate(cube, 1, 0)),
            entry("F", cube -> rotate(cube, 2, 0)),
            entry("R", cube -> rotate(cube, 3, 0)),
            entry("B", cube -> rotate(cube, 4, 0)),
            entry("D", cube -> rotate(cube, 5, 0)),
            entry("u", cube -> smallLetter(cube, 0)),
            entry("l", cube -> smallLetter(cube, 1)),
            entry("f", cube -> smallLetter(cube, 2)),
            entry("r", cube -> smallLetter(cube, 3)),
            entry("b", cube -> smallLetter(cube, 4)),
            entry("d", cube -> smallLetter(cube, 5)),
            entry("M", cube -> rotate(cube, 1, cube.getSize()/2)),
            entry("S", cube -> rotate(cube, 2, cube.getSize()/2)),
            entry("E", cube -> rotate(cube, 5, cube.getSize()/2)));

    /**
     * Applies a single move to a cube.
     * @param cube cube to move
     * @param move move to apply
     */
    private static void motion(Cube cube, String move) {
        if (!moves.containsKey(move))
            throw new IllegalArgumentException("Invalid single motion '" + move + "'");
        moves.get(move).accept(cube);
    }

    /**
     * Parses a composed move, and applies it to a cube. Composed move is a move,
     * with one of the modifiers at the end: ', 2 or w
     * @param cube cube to move
     * @param move move to apply
     */
    private static void composedMotion(Cube cube, String move) {
        if (move.length() == 1) {
            motion(cube, move);
            return;
        }
        if (move.charAt(move.length() - 1) == '2') {
            composedMotion(cube, move.substring(0, move.length() - 1));
            composedMotion(cube, move.substring(0, move.length() - 1));
            return;
        }
        if (move.charAt(move.length() - 1) == '\'') {
            composedMotion(cube, move.substring(0, move.length() - 1));
            composedMotion(cube, move.substring(0, move.length() - 1));
            composedMotion(cube, move.substring(0, move.length() - 1));
            return;
        }
        if (move.length() == 2 && move.charAt(1) == 'w') {
            motion(cube, move.substring(0, 1));
            motion(cube, move.substring(0, 1).toLowerCase());
            return;
        }
    }

    /**
     * Applies a sequence of moves to a cube. Consecutive moves should be separated
     * with space.
     * @param cube     cube to move
     * @param sequence space separated sequence of moves
     * @return final view of the cube, or empty string if thread was interrupted.
     */
    public static String move(Cube cube, String sequence) {
        for (String move : sequence.split(" "))
            composedMotion(cube, move);
        try {
            return cube.show();
        } catch (InterruptedException e) {
            return "";
        }
    }

    /**
     * Applies a sequence of moves to a cube. Consecutive moves should be separated
     * with space.
     * @param cube     cube to move
     * @param sequence space separated sequence of moves
     * @return list of cube views after each single move (plus at the beginning)
     */
    public static List<String> states(Cube cube, String sequence) throws InterruptedException {
        List<String> states = new ArrayList<>();
        states.add(cube.show());
        for (String move : sequence.split(" ")) {
            motion(cube, move);
            states.add(cube.show());
        }
        return states;
    }

}
