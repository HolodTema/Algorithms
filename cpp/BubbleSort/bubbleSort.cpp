#include <iostream>

void bubbleSort(int *array, unsigned int len) {
	int temp = 0;
	for (int i = 0; i<len; ++i) {
		for (int j = 0; j<len-1-i; ++j) {
			if (array[j]>array[j+1]) {
				temp = array[j+1];
				array[j+1] = array[j];
				array[j] = temp;
			}
		}
	}
}

void printArray(int *array, unsigned int len) {
	for (int i = 0; i<len; ++i) {
		std::cout << array[i] << "\t";
	}
}

int main() {
	int *array = new int[]{1, 10, 8, 3, 5, 1, 9};
	bubbleSort(array, 7);
	printArray(array, 7);
	return 0;
}


