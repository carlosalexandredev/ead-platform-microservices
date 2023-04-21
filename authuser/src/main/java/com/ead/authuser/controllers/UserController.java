package com.ead.authuser.controllers;

import com.ead.authuser.dtos.UserDTO;
import com.ead.authuser.models.UserModel;
import com.ead.authuser.service.UserService;
import com.ead.authuser.specifications.SpecificationTemplate;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Log4j2
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    @Autowired
    UserService userService;
    @GetMapping
    public ResponseEntity<Page<UserModel>> getAllUsers(
            SpecificationTemplate.UserSpec spec,
            @PageableDefault(page = 0, size = 10, sort = "userId", direction = Sort.Direction.ASC) Pageable pageable){
        Page<UserModel> userModelPage = userService.findAll(pageable, spec);
        if(!userModelPage.isEmpty()){
            for(UserModel user : userModelPage.toList()){
                user.add(linkTo(methodOn(UserController.class).getOneUser(user.getUserId())).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(userModelPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneUser(@PathVariable(value = "id") UUID id){
        Optional<UserModel> userModelOptional = userService.findById(id);
        if(!userModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not foud.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(userModelOptional.get());
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "userId") UUID userId){
        log.debug("DELETE deleteUser userId received {} ", userId);
        Optional<UserModel> userModelOptional = userService.findById(userId);
        if(!userModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not foud.");
        }else {
            userService.delete(userModelOptional.get());
            log.debug("DELETE deleteUser userModel deleted {} ", userId);
            log.info("User deleted succesfully userId {} ", userId);
        }
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(
            @PathVariable(value = "id") UUID id,
            @RequestBody @Validated(UserDTO.UserView.UserPut.class)
            @JsonView(UserDTO.UserView.UserPut.class) UserDTO userDTO){
        log.debug("PUT updateUser userDTO received {} ", userDTO.toString());
        Optional<UserModel> userModelOptional = userService.findById(id);
        if(!userModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not foud.");
        }else {
            var userModel = userModelOptional.get();
            userModel.setFullName(userDTO.getFullName());
            userModel.setPhoneNunber(userDTO.getPhoneNumber());
            userModel.setCpf(userDTO.getCpf());
            userService.save(userModel);
            log.debug("PUT updateUser userModel updated {} ", userModel.toString());
            log.info("User updated succesfully userId {} ", userModel.getUserId());
        return ResponseEntity.status(HttpStatus.OK).body(userModel);
        }
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<Object> updatePassword(
            @PathVariable(value = "id") UUID id,
            @RequestBody @Validated(UserDTO.UserView.PasswordPut.class)
            @JsonView(UserDTO.UserView.PasswordPut.class) UserDTO userDTO){
        Optional<UserModel> userModelOptional = userService.findById(id);
        if(!userModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not foud.");
        }if(userModelOptional.get().getPassword().equals(userDTO.getOldPassword())){
            log.warn("Mismatched old password {} ", userDTO.getUserId());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Mismatched old password!");
        } else {
            var userModel = userModelOptional.get();
            userModel.setPassword(userDTO.getPassword());
            userService.save(userModel);
            return ResponseEntity.status(HttpStatus.OK).body("Password update successfully.");
        }
    }

    @PutMapping("/{id}/image")
    public ResponseEntity<Object> updateImage(
            @PathVariable(value = "id") UUID id,
            @RequestBody @Validated(UserDTO.UserView.ImagePut.class)
            @JsonView(UserDTO.UserView.ImagePut.class) UserDTO userDTO){
        Optional<UserModel> userModelOptional = userService.findById(id);
        if(!userModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not foud.");
        } else {
            var userModel = userModelOptional.get();
            userModel.setImageUrl(userDTO.getImageUrl());
            userService.save(userModel);
            return ResponseEntity.status(HttpStatus.OK).body("Image update successfully.");
        }
    }

}
