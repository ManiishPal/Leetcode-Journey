
class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice,
                                 String arriveBob, String leaveBob) {
        int aliceStart = toDay(arriveAlice);
        int aliceEnd = toDay(leaveAlice);
        int bobStart = toDay(arriveBob);
        int bobEnd = toDay(leaveBob);

        int count = 0;

        for (int day = 1; day <= 365; day++) {
            if (day >= aliceStart && day <= aliceEnd &&
                day >= bobStart && day <= bobEnd) {
                count++;
            }
        }

        return count;
    }

    private int toDay(String date) {
        int[] daysInMonth =
            {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));

        for (int i = 0; i < month - 1; i++) {
            day += daysInMonth[i];
        }

        return day;
    }
}
