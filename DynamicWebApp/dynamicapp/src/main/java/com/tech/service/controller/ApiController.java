package com.tech.service.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.service.entity.AppObject;
import com.tech.service.entity.AppObjectField;
import com.tech.service.entity.Company;
import com.tech.service.entity.Profile;
import com.tech.service.entity.Schema;
import com.tech.service.entity.SchemaRecord;
import com.tech.service.entity.SchemaRecordFieldValue;
import com.tech.service.entity.User;
import com.tech.service.repository.AppObjectFieldRespository;
import com.tech.service.repository.AppObjectRepository;
import com.tech.service.repository.CompanyRepository;
import com.tech.service.repository.ProfileRepository;
import com.tech.service.repository.SchemaRecordFieldValueRepository;
import com.tech.service.repository.SchemaRecordRepository;
import com.tech.service.repository.SchemaRepository;
import com.tech.service.repository.UserRepository;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponentModule;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 5600)
@RequestMapping("/techmadhyam")
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
        return "Hello, I am from In!!!";
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

    @PostMapping("/createUpdateUser")
    public User createUpdateUser(@RequestBody User appUser){
        User user = userRepository.save(appUser);
        user.profile = profileRepository.findById(user.profile.id).get();
        user.company = companyRepository.findById(user.company.id).get();
        System.out.println("user.profile---"+user.profile);
        System.out.println("user.company---"+user.company);
        return user;
        
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

    /*
    @GetMapping("/getAppUser/{username}/{password}") 
    public List<AppUser> getAppUserInfo(@PathVariable String username,@PathVariable String password){
        return appUserRespository.getAppUserByUserName(username, password); 
    }   

    @GetMapping("/getAppUserInfo") 
    public List<AppUser> getAppUserInfo(@RequestParam MultiValueMap<String, String> requestParams){
        System.out.println("requestParams---"+requestParams);
        System.out.println("check---"+requestParams.getFirst("username"));
        return appUserRespository.getAppUserByUserName(requestParams.getFirst("username"), 
                                                       requestParams.getFirst("password"));
    }
    */

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
    public SchemaRecordFieldValue createUpdateSchemaRecordFieldValue(@RequestBody String jsonWrapper) throws JsonMappingException, JsonProcessingException, JSONException{
        ObjectMapper mapper = new ObjectMapper().registerModule(new JsonComponentModule());
        JSONObject mJsonObject = new JSONObject(jsonWrapper);        
        SchemaRecord schemaRecord = mapper.readValue(mJsonObject.get("schemaRecord").toString(), SchemaRecord.class);
        schemaRecord = schemaRecordRepository.save(schemaRecord);
        System.out.println("schemaRecord----"+schemaRecord);
        SchemaRecordFieldValue schemaRecordFieldValue = new SchemaRecordFieldValue();
        schemaRecordFieldValue.setSchemarecord(schemaRecord);
        schemaRecordFieldValue.setAllfieldvalue(mJsonObject.get("schemaRecord").toString());
        schemaRecordFieldValue.setCreatedby(schemaRecord.createdby);
        schemaRecordFieldValue.setCreateddate(schemaRecord.createddate);
        schemaRecordFieldValue.setLastmodifiedby(schemaRecord.lastmodifiedby);
        schemaRecordFieldValue.setLastmodifieddate(schemaRecord.lastmodifieddate);
        System.out.println("schemaRecordFieldValue---"+schemaRecordFieldValue);
        return schemaRecordFieldValueRepository.save(schemaRecordFieldValue);
    }

    public class SchemaRecordWrapper{
        public SchemaRecord schemaRecord;
        public String fieldsvalues;
    }

    @GetMapping("/getSchemaRecordFieldValueBySchemaId/{id}")
    public List<SchemaRecordFieldValue> getSchemaRecordFieldValueBySchemaId(@PathVariable long id){
       return schemaRecordFieldValueRepository.getSchemaRecordFieldValueBySchemaId(id);
    }     

    @GetMapping("/getSchemaRecordFieldValue/{companyid}/{profileid}/{tabid}")
    public List<SchemaRecordFieldValue> getSchemaRecordFieldValue(@PathVariable long companyid, 
                                                      @PathVariable long profileid, 
                                                      @PathVariable long tabid){
       return schemaRecordFieldValueRepository.getSchemaRecordFieldValue(companyid,profileid,tabid);
    }    
}