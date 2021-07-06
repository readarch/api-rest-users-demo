package apirestusers.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name="TBL_PHONE")
public class Phone {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    @Column(name="number")
    private String number;

    @Column(name="citycode")
    private String citycode;

    @Column(name="contrycode")
    private String contrycode;

    public void setUser(User user) {
        this.user = user;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public void setContrycode(String contrycode) {
        this.contrycode = contrycode;
    }
}