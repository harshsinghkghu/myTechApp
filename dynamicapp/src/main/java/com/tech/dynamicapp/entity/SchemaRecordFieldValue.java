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
@Table(name="SchemaRecordFieldValue")
public class SchemaRecordFieldValue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    @ManyToOne
    private SchemaRecord schemarecord;
    @Column(columnDefinition = "JSON")
    private String allfieldvalue;
    @ManyToOne
    public User createdby;
    @ManyToOne
    public User lastmodifiedby;
    public Date createddate;
    public Date lastmodifieddate;  
}
