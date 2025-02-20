
#include "pch.h"
#include<queue>
#include<vector>
#include<iostream>
#include<queue>
using namespace std;

struct struct_cmp {
	bool operator()(int a, int b) {
		return a > b;
	}
};

class class_cmp {
public:
	bool operator()(int a, int b) {
		return a > b;
	}
};

bool fun_cmp(int a, int b) {
	return a > b;
}


int main()
{
	vector<int> coins;
	coins.push_back(10);
	coins.push_back(20);
	coins.push_back(0);
	cout << "---------------" << endl;
	
	sort(coins.begin(), coins.end());
	for (auto i : coins) cout << i << " :default" << endl;
	cout << "---------------" << endl;

	sort(coins.begin(), coins.end(), fun_cmp);
	for (auto i : coins) cout << i << " :fun_cmp" << endl;
	cout << "---------------" << endl;
	
	sort(coins.begin(), coins.end(), class_cmp());
	for (auto i : coins) cout << i << " :class_cmp()" << endl;
	cout << "---------------" << endl;

	sort(coins.begin(), coins.end(), struct_cmp());
	for (auto i : coins) cout << i << " struct_cmp()" << endl;
	cout << "---------------" << endl;

	sort(coins.begin(), coins.end(), greater<int>());
	for (auto i : coins) cout << i << " greater<int>()" << endl; //result oriented, not compare sign
	cout << "---------------" << endl;
	return 0;

//	---------------
//    0 :default
//    10 :default
//    20 :default
//    ---------------
//    20 :fun_cmp
//    10 :fun_cmp
//    0 :fun_cmp
//    ---------------
//    20 :class_cmp()
//    10 :class_cmp()
//    0 :class_cmp()
//    ---------------
//    20 struct_cmp()
//    10 struct_cmp()
//    0 struct_cmp()
//    ---------------
//    20 greater<int>()
//    10 greater<int>()
//    0 greater<int>()
//    ---------------
}