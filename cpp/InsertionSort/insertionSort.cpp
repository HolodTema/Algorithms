#include <iostream>


void insertionSort(int *array, unsigned int len) {

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
	


