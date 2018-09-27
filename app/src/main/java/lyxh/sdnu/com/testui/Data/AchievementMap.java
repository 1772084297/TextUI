package lyxh.sdnu.com.testui.Data;

public class AchievementMap {
    private String name;
    private double score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public AchievementMap(String name, double score) {
        this.name = name;
        this.score = score;
    }
}
