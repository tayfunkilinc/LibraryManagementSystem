package com.tpe.service;

import com.tpe.domain.Owner;
import com.tpe.dto.OwnerDto;
import com.tpe.exception.ConflictException;
import com.tpe.exception.OwnerNotFoundException;
import com.tpe.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnerService {
    private final OwnerRepository ownerRepository;

    //2-b
    public List<Owner> getAll() {
        List<Owner> ownerList = ownerRepository.findAll();
        if (ownerList.isEmpty()){
            throw new OwnerNotFoundException("Hic Uye Bulunamadi");
        }
        return ownerList;
    }
    //1-b
    public void saveOwner(@Valid OwnerDto ownerDto) {
        boolean exists = ownerRepository.existsByEmail(ownerDto.getEmail()); // parametrede verilen dto'nun email degeri var mi yokmu diye kontrol ettiriyoruz
        if (exists){
            throw new ConflictException("Bu email zaten Kullaniliyor!!");
        }

        //dto-->entity:mapOwnerDtoOwner(maper methodlar)
        Owner owner = new Owner();
        owner.setName(ownerDto.getName());
        owner.setLastName(ownerDto.getLastName());
        owner.setPhoneNumber(ownerDto.getPhoneNumber());
        owner.setEmail(ownerDto.getEmail());

        ownerRepository.save(owner);

    }

    //3-b
    //1.yöntem:repodan entity-->DTO
    //2.yöntem:JPQL ile doğrudan DTO
    public OwnerDto getOwnerDtoById(Long id) {
        OwnerDto ownerDTO=ownerRepository.findOwnerDTOById(id).
                orElseThrow(()->new OwnerNotFoundException("Üye bulunamadı. ID : "+id));
        return ownerDTO;
    }

    //id'si verilen owneri entity olarak alalim
    public Owner getOwnerById(Long id){
        return ownerRepository.findById(id).
                orElseThrow(()->new OwnerNotFoundException("Owner bulunamadı. ID : "+id));
    }

}
