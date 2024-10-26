
/**
 * @param {number} numberOfDays
 * @param {number[][]} meetings
 * @return {number}
 */
var countDays = function (numberOfDays, meetings) {

    const FIRST_DAY = 1;
    meetings.sort((x, y) => x[0] - y[0]);
    let totalMinStartTime = meetings[0][0];
    let totalMaxEndTime = Number.MIN_SAFE_INTEGER;

    for (let meeting of meetings) {
        totalMaxEndTime = Math.max(totalMaxEndTime, meeting[1]);
    }

    let latestMaxEndTime = meetings[0][1];
    let daysAvailableForWorkWithoutMeetings = (totalMinStartTime - FIRST_DAY) + (numberOfDays - totalMaxEndTime);

    for (let i = 1; i < meetings.length; ++i) {
        if (latestMaxEndTime < meetings[i][0]) {
            daysAvailableForWorkWithoutMeetings += meetings[i][0] - latestMaxEndTime - 1;
        }
        latestMaxEndTime = Math.max(latestMaxEndTime, meetings[i][1]);
    }

    return daysAvailableForWorkWithoutMeetings;
};
