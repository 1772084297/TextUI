package lyxh.sdnu.com.testui.Data;

public class AchievementInfo {
    private String StudentNum;
    private String CourseName;
    private String Score;

    public AchievementInfo(String studentNum, String courseName, String score) {
        StudentNum = studentNum;
        CourseName = courseName;
        Score = score;
    }
    public int getScoreInt(){
        int score=Integer.parseInt(Score);;
        return score;
    }

    public String getStudentNum() {

        return StudentNum;
    }

    public void setStudentNum(String studentNum) {
        StudentNum = studentNum;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public String getScore() {
        return Score;
    }

    public void setScore(String score) {
        Score = score;
    }
}
