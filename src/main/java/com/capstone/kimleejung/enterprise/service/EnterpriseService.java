package com.capstone.kimleejung.enterprise.service;

import com.capstone.kimleejung.enterprise.entity.DiscInfo;
import com.capstone.kimleejung.enterprise.entity.StockDivideInfo;
import com.capstone.kimleejung.enterprise.entity.StockSecuritiesInfo;
import com.capstone.kimleejung.enterprise.repositoy.DiscInfoRepository;
import com.capstone.kimleejung.enterprise.repositoy.StockDivideInfoRepository;
import com.capstone.kimleejung.enterprise.repositoy.StockSecuritiesInfoRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class EnterpriseService {
    private final StockDivideInfoRepository stockDivideInfoRepository;
    private final StockSecuritiesInfoRepository stockSecuritiesInfoRepository;
    private final DiscInfoRepository discInfoRepository;

    public EnterpriseService(StockDivideInfoRepository stockDivideInfoRepository,
                             StockSecuritiesInfoRepository stockSecuritiesInfoRepository,
                             DiscInfoRepository discInfoRepository) {
        this.stockDivideInfoRepository = stockDivideInfoRepository;
        this.stockSecuritiesInfoRepository = stockSecuritiesInfoRepository;
        this.discInfoRepository = discInfoRepository;
    }
    private String makeUrl(String company) throws UnsupportedEncodingException {
        String urlBuilder = "http://apis.data.go.kr/1160100/service/GetStocDiviInfoService/getDiviInfo" + "?" + URLEncoder.encode("serviceKey", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("H8M+zTGzEGjkWijFKjq4wni6P6SKhzbEalrHnkrdexbFlgkuWgDMviKNbCN9tqt2I8tTF5dEedbjV3QMHIkqxA==", StandardCharsets.UTF_8) +
                "&" + URLEncoder.encode("pageNo", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("1", StandardCharsets.UTF_8) +
                "&" + URLEncoder.encode("numOfRows", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("1000", StandardCharsets.UTF_8) +
                "&" + URLEncoder.encode("resultType", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("json", StandardCharsets.UTF_8) +
                // urlBuilder.append("&" + URLEncoder.encode("crno","UTF-8") + "=" + URLEncoder.encode("1101110057012", "UTF-8"));
                "&" + URLEncoder.encode("stckIssuCmpyNm", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(company, StandardCharsets.UTF_8);
        return urlBuilder;
    }

    private Integer convert(Object s){
        return Objects.equals(s.toString(), "") ? 0 : Integer.parseInt(s.toString()) ;
    }
    private Double convertDob(Object s){
        return Objects.equals(s.toString(), "") ? 0 : Double.parseDouble(s.toString()) ;
    }


    private String connectUrl(String makeurl) throws IOException {
        URL url = new URL(makeurl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        return sb.toString();
    }

    public void saveDiviInfo(String company) throws IOException, ParseException {
        List<StockDivideInfo> stockDivideInfos = new ArrayList<>();
        JSONParser jsonParser = new org.json.simple.parser.JSONParser();

//        for(Kospi kospi: Kospi.values()){
//            String sb = connectUrl(makeUrl(kospi.toString()));
            String sb = connectUrl(makeUrl(company));

        org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) jsonParser.parse(sb);
        JSONObject array = (JSONObject) jsonObject.get("response");
        JSONObject array1 = (JSONObject) array.get("body");
        JSONObject array2 = (JSONObject) array1.get("items");
        JSONArray array3 = (JSONArray) array2.get("item");

        for (Object o : array3) {
            JSONObject object = (JSONObject) o;
            stockDivideInfos.add(
                    StockDivideInfo.builder().basDt(Integer.parseInt(object.get("basDt").toString()))
                            .crno(object.get("crno").toString())
                            .stckIssuCmpyNm(object.get("stckIssuCmpyNm").toString())
                            .dvdnBasDt(Integer.parseInt(object.get("basDt").toString()))
                            .cashDvdnPayDt(convert(object.get("cashDvdnPayDt")))
                            .stckHndvDt(convert(object.get("stckHndvDt")))
                            .stckDvdnRcd(convert(object.get("stckDvdnRcd")))
                            .stckDvdnRcdNm(object.get("stckDvdnRcdNm").toString())
                            .stckGenrDvdnAmt(convert(object.get("stckGenrDvdnAmt")))
                            .stckGenrCashDvdnRt(Double.valueOf(object.get("stckGenrCashDvdnRt").toString()))
                            .stckGenrDvdnRt(Double.valueOf(object.get("stckGenrDvdnRt").toString()))
                            .cashGrdnDvdnRt(Double.valueOf(object.get("cashGrdnDvdnRt").toString()))
                            .stckGrdnDvdnRt(convert(object.get("stckGrdnDvdnRt")))
                            .stckParPrc(convert(object.get("stckParPrc")))
                            .stckStacMd(convert(object.get("stckStacMd")))
                            .build());
            stockDivideInfoRepository.saveAll(stockDivideInfos);
        }
//        }
    }
    public void saveDiviDiscInfo(String CRNO) throws IOException, ParseException {
        List<DiscInfo> stock = new ArrayList<>();
        JSONParser jsonParser = new org.json.simple.parser.JSONParser();

//        for(Kospi kospi: Kospi.values()) {
//            String sb = connectUrl(makeUrl(kospi.toString()));

        String urlBuilder = "http://apis.data.go.kr/1160100/service/GetDiscInfoService/getDiviDiscInfo" + "?" + URLEncoder.encode("serviceKey", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("H8M+zTGzEGjkWijFKjq4wni6P6SKhzbEalrHnkrdexbFlgkuWgDMviKNbCN9tqt2I8tTF5dEedbjV3QMHIkqxA==", StandardCharsets.UTF_8) +
                "&" + URLEncoder.encode("pageNo", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("1", StandardCharsets.UTF_8) +
                "&" + URLEncoder.encode("numOfRows", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("1000", StandardCharsets.UTF_8) +
                "&" + URLEncoder.encode("crno", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(CRNO, StandardCharsets.UTF_8) +
                "&" + URLEncoder.encode("resultType", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("json", StandardCharsets.UTF_8);


        String sb = connectUrl(urlBuilder);

            org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) jsonParser.parse(sb);
            JSONObject array = (JSONObject) jsonObject.get("response");
            JSONObject array1 = (JSONObject) array.get("body");
            JSONObject array2 = (JSONObject) array1.get("items");
            JSONArray array3 = (JSONArray) array2.get("item");

        for (Object o : array3) {
            JSONObject object = (JSONObject) o;
            stock.add(DiscInfo.builder()
                    .crno(Long.valueOf(object.get("crno").toString()))
                    .basDt(Integer.valueOf(object.get("basDt").toString()))
                    .bpvtrCashDvdnTndnCtt(convertDob(object.get("bpvtrCashDvdnTndnCtt").toString()))
                    .bpvtrOnskCashDvdnAmt(Integer.parseInt(object.get("bpvtrOnskCashDvdnAmt").toString()))
                    .bpvtrOnskCashDvdnBnfRt(Double.parseDouble(object.get("bpvtrOnskCashDvdnBnfRt").toString()))
                    .crtmCashDvdnTndnCtt(convertDob(object.get("crtmCashDvdnTndnCtt").toString()))
                    .crtmOnskCashDvdnAmt(Integer.valueOf(object.get("crtmOnskCashDvdnAmt").toString()))
                    .crtmOnskCashDvdnBnfRt(Double.parseDouble(object.get("crtmOnskCashDvdnBnfRt").toString()))
                    .pvtrCashDvdnTndnCtt(convertDob((object.get("pvtrCashDvdnTndnCtt").toString())))
                    .pvtrOnskCashDvdnAmt(Integer.valueOf(object.get("pvtrOnskCashDvdnAmt").toString()))
                    .pvtrOnskCashDvdnBnfRt(Double.parseDouble(object.get("pvtrOnskCashDvdnBnfRt").toString()))
                    .build());

        }
            discInfoRepository.saveAll(stock);

    }

    public void saveStockSecuritiesInfoService(String company) throws IOException, ParseException {
        List<StockSecuritiesInfo> stock = new ArrayList<>();
        JSONParser jsonParser = new org.json.simple.parser.JSONParser();

        String urlBuilder = "http://apis.data.go.kr/1160100/service/GetStockSecuritiesInfoService/getStockPriceInfo" +
                "?" + URLEncoder.encode("serviceKey", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("H8M+zTGzEGjkWijFKjq4wni6P6SKhzbEalrHnkrdexbFlgkuWgDMviKNbCN9tqt2I8tTF5dEedbjV3QMHIkqxA==", StandardCharsets.UTF_8) +
                "&" + URLEncoder.encode("pageNo", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("1", StandardCharsets.UTF_8) +
                "&" + URLEncoder.encode("numOfRows", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("1000", StandardCharsets.UTF_8) +
                "&" + URLEncoder.encode("itmsNm", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(company, StandardCharsets.UTF_8) +
                "&" + URLEncoder.encode("resultType", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("json", StandardCharsets.UTF_8);

        System.out.println(urlBuilder.toString());
        String sb = connectUrl(urlBuilder);
        org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) jsonParser.parse(sb);
        JSONObject array = (JSONObject) jsonObject.get("response");
        JSONObject array1 = (JSONObject) array.get("body");
        JSONObject array2 = (JSONObject) array1.get("items");
        JSONArray array3 = (JSONArray) array2.get("item");

        System.out.println(array3.toString());
        for (Object o : array3) {
            JSONObject object = (JSONObject) o;
            stock.add(StockSecuritiesInfo.builder()
                    .basDt(Long.parseLong(object.get("basDt").toString()))
                    .clpr(convert(object.get("clpr")))
                    .itmsNm(object.get("itmsNm").toString())
                    .build());

        }
        stockSecuritiesInfoRepository.saveAll(stock);

    }
}
