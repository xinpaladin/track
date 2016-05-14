package cn.xjtu.track.entity;

public class DataItem {
    private Long id;

    private String driverName;

    private String fileName;

    private Integer type;

    private Boolean isAvailable;

    private Boolean isTranBaidu;

    private String comfortable;

    private String speed;

    private String locus;

    private String overallEval;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName == null ? null : driverName.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Boolean getIsTranBaidu() {
        return isTranBaidu;
    }

    public void setIsTranBaidu(Boolean isTranBaidu) {
        this.isTranBaidu = isTranBaidu;
    }

    public String getComfortable() {
        return comfortable;
    }

    public void setComfortable(String comfortable) {
        this.comfortable = comfortable == null ? null : comfortable.trim();
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed == null ? null : speed.trim();
    }

    public String getLocus() {
        return locus;
    }

    public void setLocus(String locus) {
        this.locus = locus == null ? null : locus.trim();
    }

    public String getOverallEval() {
        return overallEval;
    }

    public void setOverallEval(String overallEval) {
        this.overallEval = overallEval == null ? null : overallEval.trim();
    }
}