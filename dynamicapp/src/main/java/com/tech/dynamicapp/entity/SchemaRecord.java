package com.tech.dynamicapp.entity;
import java.util.Date;

import javax.persistence.*;
import lombok.*;

@Entity
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="SchemaRecord")
public class SchemaRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    public String recordid;
    @ManyToOne
    public Company company;
    @ManyToOne
    public Profile profile;
    @ManyToOne
    public AppObject appobject;
    @ManyToOne
    public User createdby;
    @ManyToOne
    public User lastmodifiedby;
    public Date createddate;
    public Date lastmodifieddate;
}