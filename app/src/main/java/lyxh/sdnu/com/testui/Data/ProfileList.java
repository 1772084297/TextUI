package lyxh.sdnu.com.testui.Data;

import java.util.List;

import lyxh.sdnu.com.testui.Data.AchievementInfo;

//全局变量 用来存储必要的信息 如登陆后主页面中的数据
public class ProfileList {
    public static final int VIEW_CARD_SOLUTION=0;
    public static final int VIEW_SEX=1;
    public static final int VIEW_STRAIGHT_STU=2;
    public static final int VIEW_ACHIEVEMENT_STU=3;
    public static final int VIEW_NUM_STU=4;
    public static final int VIEW_COMMONLY_URL=5;

    public static final int CARD_NORMAL=10;
    public static final int CARD_LOSS=11;
    public static final int CARD_COLD=12;

    public static final int NUM_CLASS=20;
    public static final int NUM_DEPARTMENT=21;
    public static final int NUM_SCHOOL=22;

    private int status;
    private int SexProportionNum;//性别比例
    //个人信息
    private String Name;
    private String AvaUrl;
    private String SchoolId;
    private String CardId;
    private String Balance;
    private String TransitionBalance;
    private String CardStatus;


    //学霸
    private String straightName;
    private String straightAvaUrl;
    //数量（班级）
    private String NumStu;
    private String StatusNum;
    //成绩
    private List<AchievementInfo> achievementInfoList;
    private Performance performance;

    public Performance getPerformance() {
        return performance;
    }

    public void setPerformance(Performance performance) {
        this.performance = performance;
    }
    //网址
//    private String webUrl;

    public List<AchievementInfo> getAchievementInfoList() {
        return achievementInfoList;
    }

    public void setAchievementInfoList(List<AchievementInfo> achievementInfoList) {
        this.achievementInfoList = achievementInfoList;
    }

    public String getName() {
        return Name;
    }

    public String getAvaUrl() {
        return AvaUrl;
    }

    public String getSchoolId() {
        return SchoolId;
    }

    public String getCardId() {
        return CardId;
    }

    public String getBalance() {
        return Balance;
    }

    public String getTransitionBalance() {
        return TransitionBalance;
    }

    public String getCardStatus() {
        return CardStatus;
    }

    public String getStraightName() {
        return straightName;
    }

    public String getStraightAvaUrl() {
        return straightAvaUrl;
    }

    public String getNumStu() {
        return NumStu;
    }

    public String getStatusNum() {
        return StatusNum;
    }

    public ProfileList(int status){
        this.status=status;
    }
    public ProfileList(){ }
    public int getSexProportionNum() {
        return SexProportionNum;
    }

    public void setSexProportionNum(int sexProportionNum) {
        SexProportionNum = sexProportionNum;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
