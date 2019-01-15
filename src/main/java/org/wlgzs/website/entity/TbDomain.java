package org.wlgzs.website.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zjg
 * @date 2018/8/9 19:50
 * @Description 子系统（域名）管理
 */
@Data
@TableName("tb_domain")
public class TbDomain implements Serializable {

    private int id;
    /**
     * 域名
     */
    private String domain;
    /**
     * 域名所在系统信息描述
     */
    private String describe;
    /**
     * 备份字段
     */
    private String remark;

    public TbDomain(String domain, String describe, String remark) {
        this.domain = domain;
        this.describe = describe;
        this.remark = remark;
    }

    public TbDomain () {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
