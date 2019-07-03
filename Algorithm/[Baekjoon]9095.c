#include <stdio.h>
#include <stdlib.h>

// adding 1, 2, 3 

int get_ans(int data) {
	int ret;

	if (data == 1) {
		ret = 1;
	}
	else if(data == 2){
		ret = 2;
	}
	else if(data == 3){
		ret = 4;
	}
	else {
		ret = get_ans(data - 1) + get_ans(data - 2) + get_ans(data - 3);
	}
	return ret;

}

int main(void) {
	int n;
	int* arr;
	int i = 0;

	scanf_s("%d", &n);
	arr = (int*)malloc(sizeof(int) * n);
	for (i = 0; i < n; i++) {
		scanf_s("%d", &arr[i]);
	}

	for (i = 0; i < n; i++) {
		printf("%d\n", get_ans(arr[i]));
	}

}