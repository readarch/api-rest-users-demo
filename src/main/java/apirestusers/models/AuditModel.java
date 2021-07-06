package apirestusers.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"created", "modified", "last_login"},
        allowGetters = true
)
public abstract class AuditModel implements Serializable {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false, updatable = false)
    @CreatedDate
    protected Date created;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified", insertable = false)
    @LastModifiedDate
    protected Date modified;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastlogin", nullable = false)
    @LastModifiedDate
    protected Date lastLogin;

}