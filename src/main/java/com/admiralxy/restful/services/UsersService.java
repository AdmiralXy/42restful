package com.admiralxy.restful.services;

import com.admiralxy.restful.dto.users.UserDto;
import com.admiralxy.restful.dto.users.UserRegisterDto;
import com.admiralxy.restful.entities.User;
import com.admiralxy.restful.exceptions.BadRequestException;
import com.admiralxy.restful.exceptions.NotFoundException;
import com.admiralxy.restful.mappers.users.UsersMapper;
import com.admiralxy.restful.repositories.UsersRepository;
import com.admiralxy.restful.services.interfaces.IUsersService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsersService implements IUsersService {

    private final UsersRepository repository;

    private final UsersMapper mapper;

    private final PasswordEncoder passwordEncoder;

    public Page<UserDto> findAll(int page, int size) {
        return repository.findAll(PageRequest.of(page, size)).map(mapper::toDto);
    }

    @Override
    public UserDto save(UserRegisterDto user) {
        User toCreate = mapper.toEntity(user);
        if (toCreate.getRole().getId() < 1 || toCreate.getRole().getId() > 3) {
            throw new BadRequestException("Bad request");
        }
        toCreate.setPassword(passwordEncoder.encode(user.getPassword()));
        return mapper.toDto(repository.save(toCreate));
    }

    @Override
    public UserDto findById(Long id) {
        return repository.findById(id).map(mapper::toDto).orElseThrow(() ->
                new NotFoundException("User not found"));
    }

    @Override
    public UserDto update(Long id, UserRegisterDto user) {
        if (!repository.existsById(id))
            throw new NotFoundException("User not found");
        User toUpdate = mapper.toEntity(user);
        toUpdate.setId(id);
        toUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
        return mapper.toDto(repository.save(toUpdate));
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id))
            throw new NotFoundException("User not found");
        repository.deleteById(id);
    }
}
