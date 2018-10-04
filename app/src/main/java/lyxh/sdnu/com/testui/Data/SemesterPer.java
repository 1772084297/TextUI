package lyxh.sdnu.com.testui.Data;

public class SemesterPer {
    public SemesterPer(int targetPer, int actualPer) {
        this.targetPer = targetPer;
        this.actualPer = actualPer;
    }

    private int targetPer;
    private int actualPer;

    public int getTargetPer() {
        return targetPer;
    }

    public void setTargetPer(int targetPer) {
        this.targetPer = targetPer;
    }

    public int getActualPer() {
        return actualPer;
    }

    public void setActualPer(int actualPer) {
        this.actualPer = actualPer;
    }
}
