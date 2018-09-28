package lyxh.sdnu.com.testui.Data;

import java.util.List;

import lyxh.sdnu.com.testui.Data.AchievementInfo;

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
    private String name;
    private String avaUrl;
    private String schoolId;
    private String cardId;
    private int balance;
    private int transitionBalance;
    private int cardStatus;
    //学霸
    private String straightName;
    private String straightAvaUrl;
    //数量（班级）
    private int numStu;
    private int statusNum=NUM_CLASS;
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

    public int getNumStu() {
        return numStu;
    }

    public void setNumStu(int numStu) {
        this.numStu = numStu;
    }

    public int getStatusNum() {
        return statusNum;
    }

    public void setStatusNum(int statusNum) {
        this.statusNum = statusNum;
    }

    public String getStraightName() {
        return straightName;
    }

    public void setStraightName(String straightName) {
        this.straightName = straightName;
    }

    public String getStraightAvaUrl() {
        return straightAvaUrl;
    }

    public void setStraightAvaUrl(String straightAvaUrl) {
        this.straightAvaUrl = straightAvaUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvaUrl() {
        return avaUrl;
    }

    public void setAvaUrl(String avaUrl) {
        this.avaUrl = avaUrl;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getTransitionBalance() {
        return transitionBalance;
    }

    public void setTransitionBalance(int transitionBalance) {
        this.transitionBalance = transitionBalance;
    }

    public int getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(int cardStatus) {
        this.cardStatus = cardStatus;
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
