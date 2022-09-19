package com.capstone.kimleejung.enterprise.model;

import com.capstone.kimleejung.enterprise.entity.InterestedEnterprise;
import lombok.Data;

@Data
public class InterEnterpriseDto {
    long userId;
    long EnterpriseCode;

    public InterestedEnterprise toEntity(InterEnterpriseDto dto){
        return new InterestedEnterprise(dto.getUserId(), dto.getEnterpriseCode());
    }
}
