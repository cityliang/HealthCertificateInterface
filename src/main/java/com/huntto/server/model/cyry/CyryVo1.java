package com.huntto.server.model.cyry;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @ClassName  CyryVo1 
 * @Description 健康证查询人员信息Vo
 * @author 梁城市
 * @date  2019年3月25日 上午11:04:21
 */
@Data
@NoArgsConstructor
@XmlRootElement(name="REQUEST")
@XmlAccessorType(XmlAccessType.FIELD)
public class CyryVo1 implements Serializable {
	private static final long serialVersionUID = 1L;
	private String ID;// ID
    private String BH;// 编号
    private String XM;// 姓名
    private String XB;// 性别
    private Integer NL;// 年龄
    private String LB;// 类别
    private String TJJG;// 体检结果
    private String FZSJ;// 发证时间
    private String IDCARD;// 身份证号
    private byte[] PHOTO;// 图片
    private byte[] QRIMG;// 二维码

    private String FZJG;// 发证机构
    private String MEDI_INTID;// 体检机构ID


}
