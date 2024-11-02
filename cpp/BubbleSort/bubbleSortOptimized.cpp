#include <iostream>

void bubbleSortOptimized(int *array, unsigned int len) {
	bool isSorted = true;
	int temp = 0;

	for (int i = 0; i<len; ++i) {
		isSorted = true;
		for (int j = 0; j<len-1-i; ++j) {
			if (array[j]>array[j+1]) {
				temp = array[j+1];
				array[j+1] = array[j];
				array[j] = temp;
				isSorted = false;
			}
		}
		if (isSorted) {
			break;
		}
	}
}

void printArray(int *array, unsigned int len) {
	for (int i = 0; i<len; ++i) {
		std::cout << array[i] << "\t";
	}
	std::cout << "\n";
}

int main() {
	unsigned int len = 10;
	int *array = new int[len]{5, 3, 7, 1, 10, 11, 9, 52, 0, 7};

	bubbleSortOptimized(array, len);
	printArray(array, len);
	return 0;
}

