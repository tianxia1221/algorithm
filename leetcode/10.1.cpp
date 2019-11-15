class Solution {
public:
	bool isMatch(string s, string p) {
		//  if (p.empty()) return s.empty();
		return helper(s, 0, p, 0);
	}

	bool helper(string &s, int sIndex, string & p, int pIndex) {

		// cout << "sIndex: " << sIndex  << " pIndex: " << pIndex  << endl;

		if (pIndex == p.size()) return sIndex == s.size();
		bool first_match = (sIndex < s.size() &&
			(p[pIndex] == s[sIndex] || p[pIndex] == '.'));

		if (pIndex + 1 < p.size() && p[pIndex + 1] == '*') {
			//0 time or multiple times
			return (helper(s, sIndex, p, pIndex + 2) ||
				(first_match &&helper(s, sIndex + 1, p, pIndex)));
		}
		else {
			return first_match && helper(s, sIndex + 1, p, pIndex + 1);
		}
	}
};