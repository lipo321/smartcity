package cn.smartcity.relationtran.oldModel.reference;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 空间参考引用
 *
 * @author 苏州中科蓝迪公司所有(c)2016-2021
 */
public class SpatialRefSys implements Serializable {

    private static final long serialVersionUID = -6726386270958011495L;

    private Integer srId;

    private String authName;

    private Integer authSrId;

    @JsonIgnore
    private String srText;

    @JsonIgnore
    private String proj4Text;

    private String name;

    private final Pattern pet2 = Pattern.compile("\"(.*?)\"");

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getSrId() {
        return srId;
    }

    public void setSrId(Integer srId) {
        this.srId = srId;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public Integer getAuthSrId() {
        return authSrId;
    }

    public void setAuthSrId(Integer authSrId) {
        this.authSrId = authSrId;
    }

    public String getSrText() {
        return srText;
    }

    public void setSrText(String srText) {
        this.srText = srText;
    }

    public String getProj4Text() {
        return proj4Text;
    }

    public void setProj4Text(String proj4Text) {
        this.proj4Text = proj4Text;
    }

    public String getName() {
        boolean nameIsValid = (name == null || "".equals(name)) && srText != null && !"".equals(srText);
        if (nameIsValid) {

            Matcher match2 = pet2.matcher(srText);
            if (match2.find()) {
                name = match2.group(0).replace("\"", "");
            }
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
