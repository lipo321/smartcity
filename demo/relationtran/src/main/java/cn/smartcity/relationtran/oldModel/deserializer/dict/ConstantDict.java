package cn.smartcity.relationtran.oldModel.deserializer.dict;

/**
 * @Description 常量字典 -- 用于JSON正反序列化的Map Key值
 * @Author Appleyk
 * @Blob https://blog.csdn.net/appleyk
 * @Date Created on 上午 9:42 2018-10-8
 */
public class ConstantDict {


    /**
     * ID标识
     */
    public static final String ID = "id";

    /**
     * 类型
     */
    public static final String TYPE = "type";

    /**
     * 位置类型
     */
    public static final String GEO_TYPE = "geotype";

    /**
     * 形态引用
     */
    public static final String FORM_REF = "formref";

    /**
     * 位置
     */
    public static final String GEOM = "geom";

    /**
     * 位置引用
     */
    public static final String GEOM_REF = "geomref";

    /**
     * 引用ID
     */
    public static final String REF_ID = "refid";

    /**
     * 几何对象
     */
    public static final String GEOMETRY = "geometry";

    /**
     * 形态样式
     */
    public static final String STYLE = "style";

    /**
     * BIM -- 顶点索引
     */
    public static final String INDICES = "indices";

    /**
     * BIM -- 文理索引
     */
    public static final String MATERIAL_INDICES = "materialIndices";

    /**
     * BIM -- 材质
     */
    public static final String MATERIALS = "materials";

    /**
     * BIM -- 法向量
     */
    public static final String NORMALS = "normals";

    /**
     * BIM -- 顶点
     */
    public static final String VERTICES = "vertices";

    /**
     * BIM -- 矩阵
     */
    public static final String MATRIX = "matrix";

    /**
     * x坐标
     */
    public static final String X = "x";

    /**
     * y坐标
     */
    public static final String Y = "y";

    /**
     * z坐标
     */
    public static final String Z = "z";

    /**
     * 名称
     */
    public static final String NAME = "name";

    /**
     * 描述
     */
    public static final String DES = "des";

    /**
     * File【文件】ID
     */
    public static final String FID = "fid";

    /**
     * File【文件】名称
     */
    public static final String F_NAME = "fname";

    /**
     * File【文件】扩展名
     */
    public static final String EXTENSION = "extension";

    /**
     * File【文件】修改时间
     */
    public static final String M_TIME = "mtime";

    /**
     * 形态 -- 最大可视范围
     */
    public static final String MAX_GRAIN = "maxGrain";

    /**
     * 形态 -- 最小可视范围
     */
    public static final String MIN_GRAIN = "minGrain";

    /**
     * 形态 -- 维度
     */
    public static final String DIM = "dim";

    /**
     * 维数 -- 1
     */
    public static final Integer DIM1 = 1;

    /**
     * 维数 -- 2
     */
    public static final Integer DIM2 = 2;

    /**
     * 维数 -- 3
     */
    public static final Integer DIM3 = 3;

    /**
     * 维数 -- 4
     */
    public static final Integer DIM4 = 4;

    /**
     * 点
     */
    public static final Integer VECTOR_POINT = 21;

    /**
     * 线
     */
    public static final Integer VECTOR_LINESTRING = 22;

    /**
     * 面
     */
    public static final Integer VECTOR_POLYGON = 23;

    /**
     * 复合类型 【多点、多线、多面....】
     */
    public static final Integer VECTOR_MULTI = 24;

    /**
     * 字符串 0
     */
    public static final String ZERO_STR = "0";

    /**
     * 构成一条线的最低Node的个数
     */
    public static final Integer NODES_SIZE_LIMIT = 2;

    /**
     * 构成一个闭合几何的Node的最低要求个数
     */
    public static final Integer NODES_SIZE_CLOSED_LIMIT = 4;

}
