class SemaphoreTest{

}
private static class Account{
  private static Semaphore semaphore = new Semaphore(1);
  private int balance = 0;

  public int getBalance(){
    return balance;
  }

  
}
