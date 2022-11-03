package com.capstone.kimleejung.enterprise.controller;

import com.capstone.kimleejung.enterprise.entity.InterestedEnterprise;
import com.capstone.kimleejung.enterprise.entity.StockDivideInfo;
import com.capstone.kimleejung.enterprise.model.InterEnterpriseDto;
import com.capstone.kimleejung.enterprise.repositoy.InterestedEnterpriseRepository;
import com.capstone.kimleejung.enterprise.repositoy.StockDivideInfoRepository;
import com.capstone.kimleejung.enterprise.service.EnterpriseService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class EnterpriseController {

    @Autowired
    private InterestedEnterpriseRepository interestedEnterpriseRepository;
    @Autowired
    private StockDivideInfoRepository stockDivideInfoRepository;

    @Autowired
    private EnterpriseService enterpriseService;
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

    // 최근 4개 데이터
    @GetMapping("/lastest") ///lastest?enterprise=삼성전자
    public List<StockDivideInfo> getLastestData(@RequestParam("enterprise") String enterprise){
        List<StockDivideInfo> stockDivideInfos = stockDivideInfoRepository.findAllByStckIssuCmpyNm(enterprise);
        return stockDivideInfos;
    }
    @GetMapping("/data/enterprise")
    public void saveDataApi() throws IOException, ParseException {
        enterpriseService.saveData();
        }
}






