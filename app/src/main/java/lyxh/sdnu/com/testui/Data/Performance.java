package lyxh.sdnu.com.testui.Data;

import java.util.List;

public class Performance {
    public Performance(List<SemesterPer> semestersPer, int currentPer, int currentTotalPer, int needTotalPer, int disparityPer) {
        this.semestersPer = semestersPer;
        this.currentPer = currentPer;
        this.currentTotalPer = currentTotalPer;
        this.needTotalPer = needTotalPer;
        this.disparityPer = disparityPer;
    }

    private List<SemesterPer> semestersPer;
    private int currentPer;
    private int currentTotalPer;
    private int needTotalPer;
    private int disparityPer;

    public Performance() {
    }

    public List<SemesterPer> getSemestersPer() {
        return semestersPer;
    }

    public void setSemestersPer(List<SemesterPer> semestersPer) {
        this.semestersPer = semestersPer;
    }

    public int getCurrentPer() {
        return currentPer;
    }

    public void setCurrentPer(int currentPer) {
        this.currentPer = currentPer;
    }

    public int getCurrentTotalPer() {
        return currentTotalPer;
    }

    public void setCurrentTotalPer(int currentTotalPer) {
        this.currentTotalPer = currentTotalPer;
    }

    public int getNeedTotalPer() {
        return needTotalPer;
    }

    public void setNeedTotalPer(int needTotalPer) {
        this.needTotalPer = needTotalPer;
    }

    public int getDisparityPer() {
        return disparityPer;
    }

    public void setDisparityPer(int disparityPer) {
        this.disparityPer = disparityPer;
    }
}
