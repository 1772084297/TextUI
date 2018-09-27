package lyxh.sdnu.com.testui.Data;

public class CardsModel {
    //姓名 学号 排名 头像 学院 班级
    private String stuName;
    private String rank;

    private String imageResId;

    private String stuCollege;
    private String stuClass;

    private int backgroundColorResId;

    //简化构造方法，原来使用builder来创建
    public CardsModel(String stuName,
                      String rank,
                      String imageResId,
                      String stuCollege,
                      String stuClass,
                      int backgroundColorResId){
        this.stuName = stuName;
        this.rank  = rank;
        this.imageResId = imageResId;
        this.stuCollege = stuCollege;
        this.stuClass = stuClass;
        this.backgroundColorResId = backgroundColorResId;
    }


    public String getStuName() {
        return stuName;
    }

    public String getRank() {
        return rank;
    }

    public String getImageResId() {
        return imageResId;
    }

    public String getStuCollege() {
        return stuCollege;
    }

    public String getStuClass() {
        return stuClass;
    }

    public int getBackgroundColorResId() {
        return backgroundColorResId;
    }

}
