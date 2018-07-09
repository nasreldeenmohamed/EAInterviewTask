package ae.emiratesauction.eainterviewtask.utils;

public class TimeLeftCalculation {
    private boolean isLessThan5Mins = false;
    private String H = "", M = "", S = "";

    public TimeLeftCalculation() {

    }

    public String splitToComponentTimes(int longVal) {
        int hours = longVal / 3600;
        int remainder = longVal - hours * 3600;
        int mins = remainder / 60;
        remainder = remainder - mins * 60;
        int secs = remainder;

        if (hours == 0 && mins < 5)
            isLessThan5Mins = true;

        if (hours < 10)
            H = "0" + hours;
        else
            H = hours + "";

        if (hours < 10)
            M = "0" + mins;
        else
            M = mins + "";

        if (hours < 10)
            S = "0" + secs;
        S = secs + "";

        return H + ":" + M + ":" + S;
    }

    public boolean isLessThan5Mins() {
        return isLessThan5Mins;
    }
}
