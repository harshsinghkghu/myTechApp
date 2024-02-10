package com.tech.service.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tech.service.entity.SchemaRecordFieldValue;

public interface SchemaRecordFieldValueRepository extends JpaRepository<SchemaRecordFieldValue, Long>{
    
    @Query("FROM SchemaRecordFieldValue WHERE schemarecord.company.id = :companyid AND"
    +" schemarecord.profile.id = :profileid AND schemarecord.appobject.id = :appobjectid")
	List<SchemaRecordFieldValue> getSchemaRecordFieldValue(@Param("companyid") long companyid, 
                                                          @Param("profileid") long profileid,
                                                          @Param("appobjectid") long appobjectid);   
                                                          
    @Query("FROM SchemaRecordFieldValue WHERE schemarecord.id = :schemaid")
	List<SchemaRecordFieldValue> getSchemaRecordFieldValueBySchemaId(@Param("schemaid") long schemaid);                                                            

}