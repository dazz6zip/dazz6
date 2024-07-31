package pack;

public class main {

	public static void main(String[] args) {
		int i, j, k = 0;
		for (i = 0; i <= 10; i++) {
			for (j = 0; j < 10; ++j) {
				if (j >= 5) {
					break;
				}
				k += 1;
			}
			if (i > 6) {
				continue;
			}
		}
		System.out.println(k);
	}

}
