#include <stdio.h>
#include <stdlib.h>

int main(void) {
	int n;
	int cnt;
	int ans = 0;
	int a3, a2, a1;
	
	scanf_s("%d", &n);
	
	if (n < 100) {
		printf("%d\n", n);
		return 0;
	}
	else {
		ans = 99;
		cnt = 100;
		while (cnt <= n) {
			a3 = cnt / 100;
			a2 = (cnt % 100) / 10;
			a1 = cnt % 10;
			if (2 * a2 == a1 + a3) {
				ans++;
			}
		//	printf("%d\n", cnt);
			cnt++;
		}
	}

	printf("%d\n",ans);
	
	
	return 0;
}