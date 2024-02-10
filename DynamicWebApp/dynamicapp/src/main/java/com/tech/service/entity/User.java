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
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    public Profile profile;
    @ManyToOne
    public Company company;       
    private String companyName;
    private String gstNumber;
    private String userName;
    private String pandcard;
    private String password;
    private String firstName;
    private String lastName;
    private String emailId;
    private String mobile;
    private String address;
    private String city;
    private String state;
    private String country;
    private String pincode;
    private Date createdDate;
    private Date updatedDate;
    private boolean isactive;
    public boolean iscustomer;
    public boolean issuperuser;
    @ManyToOne
    public User createdby;
    @ManyToOne
    public User lastmodifiedby;
}
