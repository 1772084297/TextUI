package lyxh.sdnu.com.testui;

public class GsonStuNum {
    private String ClassName;
    private String Mannum;
    private String Womannum;

    public GsonStuNum() {
    }

    public GsonStuNum(String className, String mannum, String womannum) {
        ClassName = className;
        Mannum = mannum;
        Womannum = womannum;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public String getMannum() {
        return Mannum;
    }

    public void setMannum(String mannum) {
        Mannum = mannum;
    }

    public String getWomannum() {
        return Womannum;
    }

    public void setWomannum(String womannum) {
        Womannum = womannum;
    }
}
