package com.blue.scale;

public class ScaleDef {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column scale_def.SCALE_ID
     *
     * @ibatorgenerated Mon Jul 29 11:36:07 CST 2013
     */
    private Integer scaleId;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column scale_def.SCALE_DESC
     *
     * @ibatorgenerated Mon Jul 29 11:36:07 CST 2013
     */
    private String scaleDesc;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column scale_def.SCALE
     *
     * @ibatorgenerated Mon Jul 29 11:36:07 CST 2013
     */
    private String scale;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column scale_def.SCALE_ID
     *
     * @return the value of scale_def.SCALE_ID
     *
     * @ibatorgenerated Mon Jul 29 11:36:07 CST 2013
     */
    public Integer getScaleId() {
        return scaleId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column scale_def.SCALE_ID
     *
     * @param scaleId the value for scale_def.SCALE_ID
     *
     * @ibatorgenerated Mon Jul 29 11:36:07 CST 2013
     */
    public void setScaleId(Integer scaleId) {
        this.scaleId = scaleId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column scale_def.SCALE_DESC
     *
     * @return the value of scale_def.SCALE_DESC
     *
     * @ibatorgenerated Mon Jul 29 11:36:07 CST 2013
     */
    public String getScaleDesc() {
        return scaleDesc;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column scale_def.SCALE_DESC
     *
     * @param scaleDesc the value for scale_def.SCALE_DESC
     *
     * @ibatorgenerated Mon Jul 29 11:36:07 CST 2013
     */
    public void setScaleDesc(String scaleDesc) {
        this.scaleDesc = scaleDesc == null ? null : scaleDesc.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column scale_def.SCALE
     *
     * @return the value of scale_def.SCALE
     *
     * @ibatorgenerated Mon Jul 29 11:36:07 CST 2013
     */
    public String getScale() {
        return scale;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column scale_def.SCALE
     *
     * @param scale the value for scale_def.SCALE
     *
     * @ibatorgenerated Mon Jul 29 11:36:07 CST 2013
     */
    public void setScale(String scale) {
        this.scale = scale == null ? null : scale.trim();
    }
}