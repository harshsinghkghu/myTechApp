package com.tech.service.entity;
import java.util.Date;

import javax.persistence.*;
import lombok.*;

@Entity
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="schemaobjfield")
public class Schema {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    public Company company;
    @ManyToOne
    public Profile profile;
    @ManyToOne
    public AppObject appobject;
    @ManyToOne
    public AppObjectField objectfield;
    public boolean isrequired;
    public boolean isvisible;
    public String placeholder;
    public String description;
    public Date createddate;
    public Date lastmodifieddate;   
    @ManyToOne
    public User createdby;
    @ManyToOne
    public User lastmodifiedby;         
}
