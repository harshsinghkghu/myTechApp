package com.tech.dynamicapp.controller;
 
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.dynamicapp.entity.AppObject;
import com.tech.dynamicapp.entity.AppObjectField;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
import com.tech.dynamicapp.entity.Company;
import com.tech.dynamicapp.entity.Profile;
import com.tech.dynamicapp.entity.Schema;
import com.tech.dynamicapp.entity.SchemaRecord;
import com.tech.dynamicapp.entity.SchemaRecordFieldValue;
import com.tech.dynamicapp.entity.User;
import com.tech.dynamicapp.repository.AppObjectFieldRespository;
import com.tech.dynamicapp.repository.AppObjectRepository;
import com.tech.dynamicapp.repository.CompanyRepository;
import com.tech.dynamicapp.repository.ProfileRepository;
import com.tech.dynamicapp.repository.SchemaRecordFieldValueRepository;
import com.tech.dynamicapp.repository.SchemaRecordRepository;
import com.tech.dynamicapp.repository.SchemaRepository;
import com.tech.dynamicapp.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponentModule;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 5600)
@RequestMapping("/dynamicapp")
public class ApiController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    AppObjectRepository appObjectRepository;
    @Autowired
    AppObjectFieldRespository appObjectFieldRespository;
    @Autowired
    SchemaRepository schemaRepository;
    @Autowired
    SchemaRecordRepository schemaRecordRepository;
    @Autowired
    SchemaRecordFieldValueRepository schemaRecordFieldValueRepository;

    @GetMapping("/hello")
    public String hello(){
        return "Hello, I am from madhyam!!!";
    }

    /* *** get all Customer*/
    @GetMapping("/getAllUsersByCustomer")
    public List<User> getAllUsersByCustomer(){
        return userRepository.getAllUsersByCustomer();
    }  

    /* *** get all Super User*/
    @GetMapping("/getAllSuperUser")
    public List<User> getAllSuperUser(){
        return userRepository.getAllSuperUser();
    }
    
    @GetMapping("/getAllUsersByType/{id}/{type}")
    public List<User> getAllUsersByType(@PathVariable long id, @PathVariable String type){
        return userRepository.findByType(type, id);
    }     

    /* *** User related API start  */
    @GetMapping("/getUserById/{id}")
    public User getUserById(@PathVariable long id){
        return userRepository.findById(id).get();
    }

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user){
        return userRepository.save(user);
    }


    @DeleteMapping("/deleteUserById/{id}")
    public void deleteUserById(@PathVariable long id){
        userRepository.deleteById(id);
    }

   @PostMapping("/createUpdateCompany")
    public Company createUpdateCompany(@RequestPart("logo") MultipartFile logo, 
                                      @RequestPart("companywrapper") String companywrapper) throws IOException {
        byte[] companylogo = logo.getBytes();
        ObjectMapper mapper = new ObjectMapper().registerModule(new JsonComponentModule());
        Company company = mapper.readValue(companywrapper,  Company.class);
        company.setLogo(companylogo);
        return companyRepository.save(company);
    }

    @DeleteMapping("/deleteCompanyById/{id}")
    public void deleteCompanyById(@PathVariable long id){
        companyRepository.deleteById(id);
    }

    
    @PostMapping("/createUpdateProfile")
    public Profile createUpdateProfile(@RequestBody Profile profile){
        return profileRepository.save(profile);
    }
    
    @DeleteMapping("/deleteProfileById/{id}")
    public void deleteProfileById(@PathVariable long id){
        profileRepository.deleteById(id);
    }    

    @PostMapping("/createUpdateAppObject")
    public AppObject createUpdateAppObject(@RequestBody AppObject appObject) throws IOException{
        /*byte[] fileData = file.getBytes();
        ObjectMapper mapper = new ObjectMapper().registerModule(new JsonComponentModule());
        AppObject wrap = mapper.readValue(appObjectWrapper, AppObject.class);
        wrap.setLogo(fileData);*/
        return appObjectRepository.save(appObject);
    } 

    @DeleteMapping("/deleteAppObjectById/{id}")
    public void deleteAppObjectById(@PathVariable long id){
        appObjectRepository.deleteById(id);
    }      
    

    @PostMapping("/createUpdateAppObjField")
    public AppObjectField createUpdateAppObjField(@RequestBody AppObjectField appObjField){
        return appObjectFieldRespository.save(appObjField);
    }


    @DeleteMapping("/deleteAppObjFieldById/{id}")
    public void deleteAppObjFieldById(@PathVariable long id){
        appObjectFieldRespository.deleteById(id);
    }      

    @PostMapping("/createUpdateSchema")
    public Schema createUpdateSchema(@RequestBody Schema schema){
        Schema schemaRecord = schemaRepository.save(schema);
        schemaRecord.profile = profileRepository.findById(schemaRecord.profile.id).get();
        schemaRecord.company = companyRepository.findById(schemaRecord.company.id).get();
        schemaRecord.appobject = appObjectRepository.findById(schemaRecord.appobject.id).get();
        schemaRecord.objectfield = appObjectFieldRespository.findById(schemaRecord.objectfield.id).get();
        return schemaRecord;
    } 

    @DeleteMapping("/deleteSchemaById/{id}")
    public void deleteSchemaById(@PathVariable long id){
        schemaRepository.deleteById(id);
    }

    @DeleteMapping("/deleteSchemaRecordById/{id}")
    public void deleteSchemaRecordById(@PathVariable long id){
        schemaRecordRepository.deleteById(id);
    }
    
    @GetMapping("/getAllCompanys") 
    public List<Company> getAllCompanys(){
        return companyRepository.findAll();
    }  
    
    @GetMapping("/getAllProfiles") 
    public List<Profile> getAllProfile(){
        return profileRepository.findAll();
    }   
    
    @GetMapping("/getAllObjects") 
    public List<AppObject> getAllObjects(){
        return appObjectRepository.findAll();
    }
    
    @GetMapping("/getAllFields") 
    public List<AppObjectField> getAllFields(){
        return appObjectFieldRespository.findAll();
    }

    
    @GetMapping("/getSchemas") 
    public List<Schema> getSchemas(){
        return schemaRepository.findAll();
    }

    @GetMapping("/getSchemaTabs/{companyid}/{profileid}") 
    public List<SchemaTabWrapper> getSchemaTabs(@PathVariable long companyid,@PathVariable long profileid){
        return schemaRepository.getSchemaTabs(companyid, profileid);
    }  
    
    @GetMapping("/getSchemaObjFields/{companyid}/{profileid}/{tabid}") 
    public List<SchemaFieldWrapper> getSchemaObjFields(@PathVariable long companyid, 
                                           @PathVariable long profileid, 
                                           @PathVariable long tabid){
        return schemaRepository.getSchemaObjFields(companyid, profileid, tabid);
    }

    @PostMapping("/createUpdateSchemaRecord")
    public SchemaRecord createUpdateSchemaRecord(@RequestBody SchemaRecord schemaRecord){
        return schemaRecordRepository.save(schemaRecord);
    }

 
    @GetMapping("/getSchemaRecordIdBasedonTabId/{companyid}/{profileid}/{tabid}")
    public SchemaRecord getSchemaRecordIdBasedonTabId(@PathVariable long companyid, 
                                                      @PathVariable long profileid, 
                                                      @PathVariable long tabid){
        List<SchemaRecord> schemaRecordList =  schemaRecordRepository.getSchemaRecordIdBasedonTabId(companyid, profileid, tabid);
        return schemaRecordList.get(0);
    }
 /* */   
    @PostMapping("/createUpdateSchemaRecordFieldValue")
    public SchemaRecordFieldValue createUpdateSchemaRecordFieldValue(@RequestBody SchemaRecordWrapper schemaRecordWrapper){
        SchemaRecord schemaRecord = schemaRecordWrapper.schemaRecord;
        schemaRecord = schemaRecordRepository.save(schemaRecord);

        SchemaRecordFieldValue schemaRecordFieldValue = new SchemaRecordFieldValue();
        schemaRecordFieldValue.setSchemarecord(schemaRecord);
        schemaRecordFieldValue.setAllfieldvalue(schemaRecordWrapper.fieldsvalues);
        schemaRecordFieldValue.setCreatedby(schemaRecord.createdby);
        schemaRecordFieldValue.setCreateddate(schemaRecord.createddate);
        schemaRecordFieldValue.setLastmodifiedby(schemaRecord.lastmodifiedby);
        schemaRecordFieldValue.setLastmodifieddate(schemaRecord.lastmodifieddate);

        return schemaRecordFieldValueRepository.save(schemaRecordFieldValue);
    }

    public class SchemaRecordWrapper{
        public SchemaRecord schemaRecord;
        public String fieldsvalues;
    }
}
