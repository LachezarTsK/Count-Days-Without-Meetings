
using System;

public class Solution
{
    private static readonly int FIRST_DAY = 1;

    public int CountDays(int numberOfDays, int[][] meetings)
    {
        Array.Sort(meetings, (x, y) => x[0] - y[0]);
        int totalMinStartTime = meetings[0][0];
        int totalMaxEndTime = int.MinValue;

        foreach (int[] meeting in meetings)
        {
            totalMaxEndTime = Math.Max(totalMaxEndTime, meeting[1]);
        }

        int latestMaxEndTime = meetings[0][1];
        int daysAvailableForWorkWithoutMeetings = (totalMinStartTime - FIRST_DAY) + (numberOfDays - totalMaxEndTime);

        for (int i = 1; i < meetings.Length; ++i)
        {
            if (latestMaxEndTime < meetings[i][0])
            {
                daysAvailableForWorkWithoutMeetings += meetings[i][0] - latestMaxEndTime - 1;
            }
            latestMaxEndTime = Math.Max(latestMaxEndTime, meetings[i][1]);
        }

        return daysAvailableForWorkWithoutMeetings;
    }
}
