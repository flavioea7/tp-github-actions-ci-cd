public final class Logger {
    // Single instance of Logger (lazy initialization)
    private static Logger instance;

    // Private constructor to prevent instantiation
    private Logger() {} /*
    Method to get the single instance of Logger:
    -Prevent anyone from using `new Logger()` from outside the system.
    -Therefore, the only way to obtain a Logger is through `getInstance()`.
    Método para obtener una única instancia de Logger:
    - Impide que cualquier persona utilice `new Logger()` desde fuera del sistema.
    - Por lo tanto, la única forma de obtener un Logger es mediante `getInstance()`.
    */ 

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
        /*
        -If the instance does not yet exist (null), it is created.
        -If it already exists, it is returned.
        -This ensures that the same instance is always used.
        -Si la instancia aún no existe (es nula), se crea.
        -Si ya existe, se devuelve.
        -Esto garantiza que siempre se utilice la misma instancia.
        */
    }
    
    //ANSI colors definitions
    private static final String RESET = "\u001B[0m";
    private static final String YELLOW = "\u001B[33m";
    private static final String GREEN = "\u001B[32m";
    private static final String GRAY = "\u001B[37m";
    private static final String RED = "\u001B[31m";
    
    //methods to log messages with different levels
    public void logWarning(String msg) {
        Message(YELLOW , "[WARN]" , msg);
    }

    public void logDebug(String msg) {
        Message(GREEN , "[DEBUG]" , msg);
    }

    public void logInfo(String msg) {
        Message(GRAY , "[INFO]" , msg);
    }

    public void logError(String msg) {
        Message(RED , "[ERROR]" , msg);
    }
      //Method to format and print the log message with timestamp
    private void Message(String color,String prefix,String msg) {
        String timestamp = java.time.LocalDateTime.now().toString();
        System.out.println(color + prefix + " " + timestamp + " - " + msg + RESET);
    }
      
}
