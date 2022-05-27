package teiluebungen.fileio.logger;

public class LoggerDemo {

    public static void main(String[] args) {

        Logger logger = new Logger(".\\data\\log.txt");

        logger.logFatal("Seems like you messed up bro :)");
        logger.logError("Whoops, couldn't go more wrong, could it?");
        logger.logInfo("Now I don't want to tell you anymore.");
        logger.logDebug("Haha and you wondered why the performance sucks.");

    }

}
