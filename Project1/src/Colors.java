/**
 * this enum is for generating different colors in the console
 *
 * @author Mohammadreza Hassanzadeh
 * @since 18 Nov 2020
 * @version 1.0
 */

public enum Colors {
    RESET("\033[0m"),

    BLACK("\033[1;90m"),    // BLACK
    RED("\033[1;91m"),      // RED
    GREEN("\033[1;92m"),    // GREEN
    YELLOW("\033[1;93m"),   // YELLOW
    BLUE("\033[1;94m"),     // BLUE
    MAGENTA("\033[1;95m"),  // MAGENTA
    CYAN("\033[1;96m"),     // CYAN
    WHITE("\033[1;97m"),    // WHITE

    // Bold
    BLACK_BOLD("\033[1;30m"),   // BLACK
    RED_BOLD("\033[1;31m"),     // RED
    GREEN_BOLD("\033[1;32m"),   // GREEN
    YELLOW_BOLD("\033[1;33m"),  // YELLOW
    BLUE_BOLD("\033[1;34m"),    // BLUE
    MAGENTA_BOLD("\033[1;35m"), // MAGENTA
    CYAN_BOLD("\033[1;36m"),    // CYAN
    WHITE_BOLD("\033[1;37m"),   // WHITE
    ;

    private final String color;

    Colors(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return color;
    }
}
