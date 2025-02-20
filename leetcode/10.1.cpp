//Runtime: 32 ms, faster than 29.18% of C++ online submissions for Regular Expression Matching.
//Memory Usage: 8.2 MB, less than 100.00% of C++ online submissions for Regular Expression Matching.

class Solution {
public:
	bool isMatch(string s, string p) {
		//  if (p.empty()) return s.empty();
		return helper(s, 0, p, 0);
	}

	bool helper(string &s, int i, string & p, int j) {
		if (j == p.size()) return i == s.size();
		bool first_match = (i < s.size() &&
			(p[j] == s[i] || p[j] == '.'));

		if (j + 1 < p.size() && p[j + 1] == '*') {
			//0 time or multiple times
			return (helper(s, i, p, j + 2) ||
				(first_match &&helper(s, i + 1, p, j)));
		}
		else {
			return first_match && helper(s, i + 1, p, j + 1);
		}
	}
};