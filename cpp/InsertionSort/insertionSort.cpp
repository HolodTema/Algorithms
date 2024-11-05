#include <iostream>


void insertionSort(int *array, unsigned int len) {
	for (int i = 1; i<len; ++i) {
		for (int j = i; j>0 && array[j-1]>array[j]; --j) {
			std::swap(array[j-1], array[j]);
		}
	}
}

void printArray(int *array, unsigned int len) {
	for (int i = 0; i<len; ++i) {
		std::cout << array[i] << "\t";
	}
}

int main() {
	unsigned int len = 10;
	int *array = new int[len]{8, 1, 7, 3, 5, 2, 11, 9, 10, 52};
	
	insertionSort(array, len);
	printArray(array, len);
	return 0;
}
	


