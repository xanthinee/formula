package qualifyingresults;

public class Utils {

    public static String repeat(char symbol, int numOfSymbols) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < numOfSymbols; i++) {
            sb.append(symbol);
        }
        return sb.toString();
    }
}
