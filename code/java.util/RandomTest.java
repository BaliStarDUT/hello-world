import java.util.Random;

class RandomTest{
    public static void main(String []args){
        String base = "abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<10;i++){
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        System.out.println(sb.toString());
    }
}
