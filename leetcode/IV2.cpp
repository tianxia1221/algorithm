#include "pch.h"
#include <iostream>
#include <algorithm>  
#include <string>  
#include <vector>
#include<queue>
using namespace std;
class IV2 {
public:
	static int method(int rows, int columns, vector<vector<int>> grid) {
		int ret = 0;
		int newx = 0;
		int newy = 0;
		pair<int, int> tmp;
		queue<pair<int, int>> q;

		int dx[] = { -1, 1, 0, 0 };
		int dy[] = { 0, 0, -1, 1 };
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (grid[i][j] == 1) {
					q.push(pair<int, int>(i, j));
				}
			}
		}
		q.push(pair<int, int>(-1, -1));
		ret++;

		while (q.size() != 0) {
			tmp = q.front();
			q.pop();
			if (tmp.first == -1) {
				if (q.empty()) {
					break;
				}
				print(grid);
				q.push(pair<int, int>(-1, -1));
				ret++;
				continue;
			}

			for (int i = 0; i < 4; i++) {
				newx = tmp.first + dx[i];
				newy = tmp.second + dy[i];
				if (newx >= 0 && newx < rows && newy >= 0 && newy < columns && grid[newx][newy] == 0) {
					grid[newx][newy] = 1;
					q.push(pair<int, int>(newx, newy));
				}
			}
		}

		return --ret;

	}

	static void print(vector<vector<int>> grid) {
		cout << "---------------" << endl;
		for (int i = 0; i < grid.size(); i++) {
			for (int j = 0; j < grid[0].size(); j++) {
				cout << grid[i][j];
				cout << ",";
			}
			cout << endl;
		}

	}


};


int main() {

	int arr1[5][5] = {
	{1, 0, 0, 0, 0},
	{0, 1, 0, 0, 0},
	{0, 0, 1, 0, 0},
	{0, 0, 0, 1, 0},
	{0, 0, 0, 0, 1},
	};
	vector<vector<int>> v1(5, vector<int>(5));
	for (int i = 0; i < 5; i++) {
		for (int j = 0; j < 5; j++) {
			v1[i][j] = arr1[i][j];
		}
	}

	int ret = IV2::method(5, 5, v1);
	cout << ret << endl;

	int arr2[5][6] = {
		{0, 0, 1, 0, 0, 0},
		{0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 1},
		{0, 0, 0, 0, 0, 0},
		{0, 1, 0, 0, 0, 0},
	};
	vector<vector<int>> v2(5, vector<int>(6));
	for (int i = 0; i < 5; i++) {
		for (int j = 0; j < 6; j++) {
			v2[i][j] = arr2[i][j];
		}
	}
	ret = IV2::method(5, 6, v2);
	cout << ret << endl;

	return 0;

}



