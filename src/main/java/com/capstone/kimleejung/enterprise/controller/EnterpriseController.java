package com.capstone.kimleejung.enterprise.controller;

import com.capstone.kimleejung.enterprise.entity.DiscInfo;
import com.capstone.kimleejung.enterprise.entity.InterestedEnterprise;
import com.capstone.kimleejung.enterprise.entity.StockDivideInfo;
import com.capstone.kimleejung.enterprise.entity.StockSecuritiesInfo;
import com.capstone.kimleejung.enterprise.model.InterEnterpriseDto;
import com.capstone.kimleejung.enterprise.repositoy.DiscInfoRepository;
import com.capstone.kimleejung.enterprise.repositoy.InterestedEnterpriseRepository;
import com.capstone.kimleejung.enterprise.repositoy.StockDivideInfoRepository;
import com.capstone.kimleejung.enterprise.repositoy.StockSecuritiesInfoRepository;
import com.capstone.kimleejung.enterprise.service.EnterpriseService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EnterpriseController {

    @Autowired
    private InterestedEnterpriseRepository interestedEnterpriseRepository;
    @Autowired
    private StockDivideInfoRepository stockDivideInfoRepository;
    @Autowired
    private StockSecuritiesInfoRepository stockSecuritiesInfoRepository;
    @Autowired
    private DiscInfoRepository discInfoRepository;

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

    @GetMapping("/lastest") ///lastest?enterprise=삼성전자
    public StockDivideInfo getLastestData(@RequestParam("enterprise") String enterprise){
        return stockDivideInfoRepository.findTop1ByStckIssuCmpyNmOrderByCashDvdnPayDtDesc(enterprise);
    }


    @GetMapping("/lastest/disinfo") ///lastest/disinfo?crno=1111
    public DiscInfo getLastestDisc(@RequestParam("crno") Long crno){
        DiscInfo discInfo = discInfoRepository.findTop1ByCrnoOrderByBasDtDesc(crno);
        System.out.println(discInfo.toString());
        return discInfo;
    }

    @GetMapping("/lastest/StockSecuritiesInfo") ///lastest/StockSecuritiesInfo?itmsNm= 최근 10개 데이터
    public List<StockSecuritiesInfo> getLastestDataStock(@RequestParam("itmsNm") String itmsNm){
        return stockSecuritiesInfoRepository.findTop12ByItmsNmOrderByBasDtDesc(itmsNm);
    }

    @GetMapping("/data/enterprise/{kind}") // /data/enterprise/diviInfo?company=SK하이닉스&crno=
    public void saveDataApi(@PathVariable(value = "kind",required = false) String kind,
                            @RequestParam(value = "company",required = false) String company,
                            @RequestParam(value = "crno",required = false) String crno) throws IOException, ParseException {
        if (kind.equals("diviInfo")){
            enterpriseService.saveDiviInfo(company);
        }else if(kind.equals("dividiscInfo")){
            enterpriseService.saveDiviDiscInfo(crno);
        }else if(kind.equals("stockinfo")) {
            enterpriseService.saveStockSecuritiesInfoService(company);
        }

    }
}
