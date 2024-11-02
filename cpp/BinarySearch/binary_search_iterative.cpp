#include<iostream>
#include<vector>
using namespace std;

int binary_search(vector<int> vec, int x) {
	int low = 0;
	int high = vec.size()-1;
	while(low<=high) {
		int m = (high+low)/2;
		if(x<vec[m]) {
			high = m-1;
		}
		else if(x>vec[m]) {
			low = m+1;
		}
		else {
			return m;
		}
	}
	return -1;
}

int main() {
	vector<int> vec = {1, 13, 43, 94, 430, 1000, 15000};
	cout << binary_search(vec, 94) << endl;
	return 0;
}
