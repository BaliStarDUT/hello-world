public abstract class AbstractLogger{
  public static int DEBUG = 1;
  public static int INFO = 2;
  public static int ERROR = 3;

  protected int level;
  protected AbstractLogger nextLogger;

  public void setNextLogger(AbstractLogger logger){
    this.nextLogger = logger;
  }
  public void logMessage(int level,String message){
    if(this.level <= level){
      write(message);
    }
    if(nextLogger != null){
      nextLogger.logMessage(level,message);
    }
  }
  abstract protected void write(String message);
}
