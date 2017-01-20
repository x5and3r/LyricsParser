import java.util.Map;

import static java.lang.System.exit;

public class ProgramExecutor {

    public static final int WORDS = 1;

    public static void main(String[] args) {
        if (args.length == 0) {
            exit(1);
        }
        String filePath = args[0];
        Integer words = args.length > 1 ? Integer.parseInt(args[1]) : WORDS;
        final FileParser parser = new FileParser(filePath, words);
        final Map<String, Integer> sortedWords = parser.sortWordsByInputCount();
        final ResultProcessor resultProcessor = new ResultProcessor(sortedWords);
        resultProcessor.showResults();

    }
}
