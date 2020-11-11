package com.raven.springcloud.dynamic.zuul.routing.entity;

import com.raven.springcloud.dynamic.zuul.routing.enums.InitEnums;
import com.raven.springcloud.dynamic.zuul.routing.enums.TableStatusEnum;
import com.raven.springcloud.dynamic.zuul.routing.utils.IdUtil;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @author: huorw
 * @create: 2019-09-16 21:57
 */
@MappedSuperclass
@Data
public class IdEntity implements Serializable {

    private static final long serialVersionUID = 4970643575970373543L;

    public IdEntity() {

        this.id= IdUtil.getUUID();
        this.dateLastUpdated=new Date();
        this.version=0L;
        this.dateCreated=new Date();
        this.updateBy= InitEnums.INIT.getValue();
        this.createdBy=InitEnums.INIT.getValue();
        this.status= TableStatusEnum.NORMAL_STATUS.getCode();
    }
    @Id
    @Column(name="id", columnDefinition =" varchar(40) NOT NULL comment 'id' ")
    protected String id;

    @Column(name="status" ,columnDefinition = " int(4) NOT NULL DEFAULT 1 comment '状态'")
    public Integer status;

    @Column(name="create_by" ,columnDefinition =" varchar(64) NOT NULL DEFAULT 'INIT' comment '创建人' ")
    @CreatedBy
    protected String createdBy;


    @Column(name="update_by",columnDefinition = " varchar(64) NOT NULL DEFAULT 'INIT' comment '更新人' ")
    @LastModifiedBy
    protected String updateBy;


    @Column(name="date_created", columnDefinition = " datetime DEFAULT CURRENT_TIMESTAMP  comment '创建时间'")
    @CreatedDate
    protected Date dateCreated;

    @Column(name="date_last_update",  columnDefinition = " datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '最后更新时间'")
    @LastModifiedDate
    protected Date dateLastUpdated;

    @Column(name="version", columnDefinition = " BIGINT(20) NOT NULL  comment '版本号'")
    @Version
    protected Long version = 0L;


    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }

        if (obj == null || getClass() != obj.getClass()){
            return false;
        }

        IdEntity idEntity = (IdEntity) obj;

        return id.equals(idEntity.id);

    }

    @Override
    public int hashCode() {
        if(id != null){
            return id.hashCode();
        }

        return 0;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }



    public static IdEntity  init(){
        IdEntity idEntity=new IdEntity();
        idEntity.setId(IdUtil.getUUID());
        idEntity.setDateLastUpdated(new Date());
        idEntity.setVersion(0L);
        idEntity.setDateCreated(new Date());
        idEntity.setCreatedBy(InitEnums.INIT.getValue());
        idEntity.setUpdateBy(InitEnums.INIT.getValue());
        idEntity.setStatus(TableStatusEnum.NORMAL_STATUS.getCode());
        return idEntity;
    }

}
