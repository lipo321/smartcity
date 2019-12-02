package cn.smartcity.relationtran.oldModel;

/**
 * @Description
 * @Author 苏州中科蓝迪公司所有(c)2016-2021
 * @Date Appleyk Created on 下午 12:09 2018-10-11
 */
public class RPoint {

    private Double x;
    private Double y;
    private Double z;

    public  RPoint(){

    }

    public  RPoint(double x, double y,double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getZ() {
        return z;
    }

    public void setZ(Double z) {
        this.z = z;
    }
}
