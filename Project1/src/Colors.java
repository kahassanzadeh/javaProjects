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
