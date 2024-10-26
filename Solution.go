
package main

import (
    "fmt"
    "math"
    "sort"
)

const FIRST_DAY = 1

func countDays(numberOfDays int, meetings [][]int) int {

    sort.Slice(meetings, func(i int, j int) bool { return meetings[i][0] < meetings[j][0] })
    totalMinStartTime := meetings[0][0]
    totalMaxEndTime := math.MinInt32

    for _, meeting := range meetings {
        totalMaxEndTime = max(totalMaxEndTime, meeting[1])
    }

    latestMaxEndTime := meetings[0][1]
    daysAvailableForWorkWithoutMeetings := (totalMinStartTime - FIRST_DAY) + (numberOfDays - totalMaxEndTime)

    for i := range meetings {
        if latestMaxEndTime < meetings[i][0] {
            daysAvailableForWorkWithoutMeetings += meetings[i][0] - latestMaxEndTime - 1
        }
        latestMaxEndTime = max(latestMaxEndTime, meetings[i][1])
    }

    return daysAvailableForWorkWithoutMeetings
}
