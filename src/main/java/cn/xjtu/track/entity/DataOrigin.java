package cn.xjtu.track.entity;

public class DataOrigin {
	/** */
    private Long id;
    /** 同步字1*/
    private String synWord1;
    /** 同步字2*/
    private String synWord2;
    /** 帧序号*/
    private String frameNum;
    /** 状态字*/
    private String state;
    /** 故障字1*/
    private String defWord1;
    /** 故障字1*/
    private String defWord2;
    /** 经度*/
    private String longitude;
    /** 纬度*/
    private String latitude;
    /** 高度*/
    private String height;
    /** 东向速度*/
    private String velE;
    /** 北向速度*/
    private String velN;
    /** 天向速度*/
    private String velU;
    /** 横滚角*/
    private String roll;
    /** 俯仰角*/
    private String pitch;
    /** 惯导航向角*/
    private String course;
    /** 对准时间*/
    private String aliTime;
    /** 对准状态字*/
    private String aliStateWord;
    /** 纵向加速度*/
    private String logAcce;
    /** 横向加速度*/
    private String lateralAcce;
    /** 法向加速度*/
    private String norAcce;
    /** 北京时间年*/
    private String timeYear;
    /** 北京时间月*/
    private String timeMonth;
    /** 北京时间日*/
    private String timeDay;
    /** 北京时间时*/
    private String timeHour;
    /** 北京时间分*/
    private String timeMinute;
    /** 北京时间秒*/
    private String timeSecond;
    /** 有效字*/
    private String effiWord;
    /** 磁差*/
    private String magVir;
    /** 校验和*/
    private String checkSum;
    /** item ID*/
    private Long dataItemId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSynWord1() {
        return synWord1;
    }

    public void setSynWord1(String synWord1) {
        this.synWord1 = synWord1 == null ? null : synWord1.trim();
    }

    public String getSynWord2() {
        return synWord2;
    }

    public void setSynWord2(String synWord2) {
        this.synWord2 = synWord2 == null ? null : synWord2.trim();
    }

    public String getFrameNum() {
        return frameNum;
    }

    public void setFrameNum(String frameNum) {
        this.frameNum = frameNum == null ? null : frameNum.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getDefWord1() {
        return defWord1;
    }

    public void setDefWord1(String defWord1) {
        this.defWord1 = defWord1 == null ? null : defWord1.trim();
    }

    public String getDefWord2() {
        return defWord2;
    }

    public void setDefWord2(String defWord2) {
        this.defWord2 = defWord2 == null ? null : defWord2.trim();
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height == null ? null : height.trim();
    }

    public String getVelE() {
        return velE;
    }

    public void setVelE(String velE) {
        this.velE = velE == null ? null : velE.trim();
    }

    public String getVelN() {
        return velN;
    }

    public void setVelN(String velN) {
        this.velN = velN == null ? null : velN.trim();
    }

    public String getVelU() {
        return velU;
    }

    public void setVelU(String velU) {
        this.velU = velU == null ? null : velU.trim();
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll == null ? null : roll.trim();
    }

    public String getPitch() {
        return pitch;
    }

    public void setPitch(String pitch) {
        this.pitch = pitch == null ? null : pitch.trim();
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course == null ? null : course.trim();
    }

    public String getAliTime() {
        return aliTime;
    }

    public void setAliTime(String aliTime) {
        this.aliTime = aliTime == null ? null : aliTime.trim();
    }

    public String getAliStateWord() {
        return aliStateWord;
    }

    public void setAliStateWord(String aliStateWord) {
        this.aliStateWord = aliStateWord == null ? null : aliStateWord.trim();
    }

    public String getLogAcce() {
        return logAcce;
    }

    public void setLogAcce(String logAcce) {
        this.logAcce = logAcce == null ? null : logAcce.trim();
    }

    public String getLateralAcce() {
        return lateralAcce;
    }

    public void setLateralAcce(String lateralAcce) {
        this.lateralAcce = lateralAcce == null ? null : lateralAcce.trim();
    }

    public String getNorAcce() {
        return norAcce;
    }

    public void setNorAcce(String norAcce) {
        this.norAcce = norAcce == null ? null : norAcce.trim();
    }

    public String getTimeYear() {
        return timeYear;
    }

    public void setTimeYear(String timeYear) {
        this.timeYear = timeYear == null ? null : timeYear.trim();
    }

    public String getTimeMonth() {
        return timeMonth;
    }

    public void setTimeMonth(String timeMonth) {
        this.timeMonth = timeMonth == null ? null : timeMonth.trim();
    }

    public String getTimeDay() {
        return timeDay;
    }

    public void setTimeDay(String timeDay) {
        this.timeDay = timeDay == null ? null : timeDay.trim();
    }

    public String getTimeHour() {
        return timeHour;
    }

    public void setTimeHour(String timeHour) {
        this.timeHour = timeHour == null ? null : timeHour.trim();
    }

    public String getTimeMinute() {
        return timeMinute;
    }

    public void setTimeMinute(String timeMinute) {
        this.timeMinute = timeMinute == null ? null : timeMinute.trim();
    }

    public String getTimeSecond() {
        return timeSecond;
    }

    public void setTimeSecond(String timeSecond) {
        this.timeSecond = timeSecond == null ? null : timeSecond.trim();
    }

    public String getEffiWord() {
        return effiWord;
    }

    public void setEffiWord(String effiWord) {
        this.effiWord = effiWord == null ? null : effiWord.trim();
    }

    public String getMagVir() {
        return magVir;
    }

    public void setMagVir(String magVir) {
        this.magVir = magVir == null ? null : magVir.trim();
    }

    public String getCheckSum() {
        return checkSum;
    }

    public void setCheckSum(String checkSum) {
        this.checkSum = checkSum == null ? null : checkSum.trim();
    }

    public Long getDataItemId() {
        return dataItemId;
    }

    public void setDataItemId(Long dataItemId) {
        this.dataItemId = dataItemId;
    }
}