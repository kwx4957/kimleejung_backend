package com.capstone.kimleejung.enterprise.controller;

import com.capstone.kimleejung.enterprise.entity.InterestedEnterprise;
import com.capstone.kimleejung.enterprise.model.InterEnterpriseDto;
import com.capstone.kimleejung.enterprise.repositoy.InterestedEnterpriseRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EnterpriseController {

    private InterestedEnterpriseRepository interestedEnterpriseRepository;

    public EnterpriseController(InterestedEnterpriseRepository interestedEnterpriseRepository) {
        this.interestedEnterpriseRepository = interestedEnterpriseRepository;
    }

    @GetMapping("/interenterprise")
    public List<Long> getInterestEnter(@RequestParam long userId){
        return interestedEnterpriseRepository.findAllByUserId(userId)
                .stream()
                .map(InterestedEnterprise::getEnterpriseCode)
                .collect(Collectors.toList());
    }

    @PostMapping("/interenterprise")
    public ResponseEntity<?> createInterestEnter(@RequestBody InterEnterpriseDto dto){
        interestedEnterpriseRepository.save(dto.toEntity(dto));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/interenterprise")
    public ResponseEntity<?> deleteInterEnter(@RequestBody InterEnterpriseDto dto){

        if (interestedEnterpriseRepository.deleteByUserIdAndEnterpriseCode(dto.getUserId(),dto.getEnterpriseCode())){
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.noContent().build();
    }


}
