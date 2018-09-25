package lyxh.sdnu.com.testui;

//[{"StudentNum":"201611010218","StudentName":"王栋","CardId":"55151","Date":null,"Bb":2.3,"Hb":9.5,"Fb":7.8},{"StudentNum":"201611010218","StudentName":"王栋","CardId":"55151","Date":null,"Bb":2.2,"Hb":7.8,"Fb":10.0},{"StudentNum":"201611010218","StudentName":"王栋","CardId":"55151","Date":null,"Bb":3.5,"Hb":6.8,"Fb":9.0},{"StudentNum":"201611010218","StudentName":"王栋","CardId":"55151","Date":null,"Bb":2.0,"Hb":8.0,"Fb":6.5},{"StudentNum":"201611010218","StudentName":"王栋","CardId":"55151","Date":null,"Bb":3.0,"Hb":5.0,"Fb":4.8}]
public class GsonConsume {
    private String StudentNum;
    private String StudentName;
    private String CardId;
    private String Bb;
    private String Hb;
    private String Fb;

    public String getStudentNum() {
        return StudentNum;
    }

    public void setStudentNum(String studentNum) {
        StudentNum = studentNum;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public String getCardId() {
        return CardId;
    }

    public void setCardId(String cardId) {
        CardId = cardId;
    }

    public String getBb() {
        return Bb;
    }

    public String getHb() {
        return Hb;
    }

    public String getFb() {
        return Fb;
    }
//    public Float getBb() {
//        return Bb;
//    }
//
//    public void setBb(Float bb) {
//        Bb = bb;
//    }
//
//    public Float getHb() {
//        return Hb;
//    }
//
//    public void setHb(Float hb) {
//        Hb = hb;
//    }
//
//    public Float getFb() {
//        return Fb;
//    }
//
//    public void setFb(Float fb) {
//        Fb = fb;
//    }
}
