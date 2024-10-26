
import kotlin.math.max

class Solution {

    private companion object {
        const val FIRST_DAY = 1
    }

    fun countDays(numberOfDays: Int, meetings: Array<IntArray>): Int {

        meetings.sortWith() { x, y -> x[0] - y[0] }
        var totalMinStartTime = meetings[0][0]
        var totalMaxEndTime = Int.MIN_VALUE

        for (meeting in meetings) {
            totalMaxEndTime = max(totalMaxEndTime, meeting[1])
        }

        var latestMaxEndTime = meetings[0][1]
        var daysAvailableForWorkWithoutMeetings = (totalMinStartTime - FIRST_DAY) + (numberOfDays - totalMaxEndTime)

        for (i in 1..<meetings.size) {
            if (latestMaxEndTime < meetings[i][0]) {
                daysAvailableForWorkWithoutMeetings += meetings[i][0] - latestMaxEndTime - 1
            }
            latestMaxEndTime = max(latestMaxEndTime, meetings[i][1])
        }

        return daysAvailableForWorkWithoutMeetings
    }
}
