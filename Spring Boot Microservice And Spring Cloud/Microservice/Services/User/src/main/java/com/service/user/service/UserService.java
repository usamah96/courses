package com.service.user.service;

import com.service.user.dto.AlbumResponseModel;
import com.service.user.dto.UserDto;
import com.service.user.entities.UserEntity;
import com.service.user.feign.AlbumFeignClient;
import com.service.user.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private RestTemplate restTemplate;
    private AlbumFeignClient albumFeignClient;
    private Environment environment;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder encoder, RestTemplate restTemplate, AlbumFeignClient albumFeignClient, Environment environment){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = encoder;
        this.restTemplate = restTemplate;
        this.albumFeignClient = albumFeignClient;
        this.environment = environment;
    }

    public UserEntity createUser(UserEntity userEntity){
        userEntity.setEncryptedPassord(this.bCryptPasswordEncoder.encode(userEntity.getPassword()));
        return userRepository.save(userEntity);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findUserEntityByEmail(s);
        if(userEntity != null){
            return new User(userEntity.getEmail(), userEntity.getEncryptedPassord(), true, true, true, true, new ArrayList<>());
        } else throw new UsernameNotFoundException("User Not Found");
    }

    public UserEntity findUserByEmail(String email) {
        UserEntity userEntity = userRepository.findUserEntityByEmail(email);
        if(userEntity != null) return userEntity;
        else throw new UsernameNotFoundException("User Not Found");
    }

    public UserDto findUserById(Long id){
        UserEntity userEntity = userRepository.findById(id).orElse(null);

        String albumUrl = String.format(environment.getProperty("album.ws.url"), id);
        ResponseEntity<List<AlbumResponseModel>> albumResponse = restTemplate.exchange(albumUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<AlbumResponseModel>>() {});

        UserDto userDto = new ModelMapper().map(userEntity, UserDto.class);
        userDto.setAlbums(albumResponse.getBody());

        return userDto;
    }

    public UserDto findUserByIdUsingFeignClient(Long id){
        UserEntity userEntity = userRepository.findById(id).orElse(null);

        System.out.println("Before Album Micro Service");
        List<AlbumResponseModel> albumResponseModel = albumFeignClient.getAlbums(id);
        System.out.println("After Album Micro Service");

        UserDto userDto = new ModelMapper().map(userEntity, UserDto.class);
        userDto.setAlbums(albumResponseModel);

        return userDto;
    }
}
