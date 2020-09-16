public class HelloNumbers {
    public static void main(String[] args) {
        int x = 1, sum = 0;
        while (x <= 10) {
            System.out.print(sum + " ");
            sum += x;
            x = x + 1;
        }
    }
}