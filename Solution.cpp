
#include <vector>
#include <limits>
#include <ranges>
#include <algorithm>
using namespace std;

/*
The code will run faster with ios::sync_with_stdio(0).
However, this should not be used in production code and interactive problems.
In this particular problem, it is ok to apply ios::sync_with_stdio(0).

Many of the top-ranked C++ solutions for time on leetcode apply this trick,
so, for a fairer assessment of the time percentile of my code
you could outcomment the lambda expression below for a faster run.
*/

/*
const static auto speedup = [] {
    ios::sync_with_stdio(0);
    return nullptr;
}();
*/

class Solution {

    static const int FIRST_DAY = 1;

public:
    int countDays(int numberOfDays, vector<vector<int>>& meetings) const {

        ranges::sort(meetings, [](const auto& x, const auto& y) {return x[0] < y[0]; });
        int totalMinStartTime = meetings[0][0];
        int totalMaxEndTime = numeric_limits<int>::min();

        for (const auto& meeting : meetings) {
            totalMaxEndTime = max(totalMaxEndTime, meeting[1]);
        }

        int latestMaxEndTime = meetings[0][1];
        int daysAvailableForWorkWithoutMeetings = (totalMinStartTime - FIRST_DAY) + (numberOfDays - totalMaxEndTime);

        for (int i = 1; i < meetings.size(); ++i) {
            if (latestMaxEndTime < meetings[i][0]) {
                daysAvailableForWorkWithoutMeetings += meetings[i][0] - latestMaxEndTime - 1;
            }
            latestMaxEndTime = max(latestMaxEndTime, meetings[i][1]);
        }

        return daysAvailableForWorkWithoutMeetings;
    }
};
