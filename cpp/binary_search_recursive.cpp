#include<iostream>
#include<vector>
using namespace std;


int binarySearch(vector<int> vec, int low, int high, int x) {
	int m = (low + high) / 2;
	if(x<vec[m]) {
		return binarySearch(vec, low, m-1, x);
	} 
	if(x>vec[m]) {
		return binarySearch(vec, m+1, high, x);
	} 
	if(x==vec[m]) {
		return m;
	}
	if(high==low) {
		return -1;
	}
}


int main() {
	vector<int> vec = {1, 3, 5, 10, 20, 100};
	cout << binarySearch(vec, 0, 5, 3) << "\n";
}


