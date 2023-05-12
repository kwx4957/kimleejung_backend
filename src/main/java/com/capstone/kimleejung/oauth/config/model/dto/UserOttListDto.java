package com.capstone.kimleejung.oauth.config.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;


@Data
@AllArgsConstructor
public class UserOttListDto {

    private Boolean netflix;
    private Boolean wavve;
    private Boolean tving;

    public UserOttListDto() {
        this.netflix = false;
        this.wavve = false;
        this.tving = false;
    }

    public Map<String, Boolean> toMap() {
        Map<String, Boolean> ottMap = new HashMap<String, Boolean>();
        ottMap.put("netflix", netflix);
        ottMap.put("wavve", wavve);
        ottMap.put("tving", tving);

        return ottMap;
    }

}
