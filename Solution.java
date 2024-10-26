
import java.util.Arrays;

public class Solution {

    private static final int FIRST_DAY = 1;

    public int countDays(int numberOfDays, int[][] meetings) {

        Arrays.sort(meetings, (x, y) -> x[0] - y[0]);
        int totalMinStartTime = meetings[0][0];
        int totalMaxEndTime = Integer.MIN_VALUE;

        for (int[] meeting : meetings) {
            totalMaxEndTime = Math.max(totalMaxEndTime, meeting[1]);
        }

        int latestMaxEndTime = meetings[0][1];
        int daysAvailableForWorkWithoutMeetings = (totalMinStartTime - FIRST_DAY) + (numberOfDays - totalMaxEndTime);

        for (int i = 1; i < meetings.length; ++i) {
            if (latestMaxEndTime < meetings[i][0]) {
                daysAvailableForWorkWithoutMeetings += meetings[i][0] - latestMaxEndTime - 1;
            }
            latestMaxEndTime = Math.max(latestMaxEndTime, meetings[i][1]);
        }

        return daysAvailableForWorkWithoutMeetings;
    }
}
