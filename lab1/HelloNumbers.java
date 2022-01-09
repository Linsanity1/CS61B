public class HelloNumbers {
    public static void main(String[] args) {
        int x = 0;
	int acc_sum = 0;
        while (x < 10) {
	    acc_sum += x;
            System.out.print(acc_sum + " ");
            x = x + 1;
        }
	System.out.println();
    }
}