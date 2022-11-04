package com.capstone.kimleejung.enterprise.service;

import com.capstone.kimleejung.enterprise.entity.DiscInfo;
import com.capstone.kimleejung.enterprise.entity.Kospi;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class EnterpriseService {

    private final StockDivideInfoRepository stockDivideInfoRepository;
    private final StockSecuritiesInfoRepository stockSecuritiesInfoRepository;
    private final DiscInfoRepository discInfoRepository;
    public EnterpriseService(StockDivideInfoRepository stock,
                             StockSecuritiesInfoRepository stockSecuritiesInfo,
                             DiscInfoRepository discInfoRepository) {
        this.stockDivideInfoRepository = stock;
        this.stockSecuritiesInfoRepository = stockSecuritiesInfo;
        this.discInfoRepository = discInfoRepository;
    }

    private String makeUrl(String company) throws UnsupportedEncodingException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1160100/service/GetStocDiviInfoService/getDiviInfo");
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + URLEncoder.encode("H8M+zTGzEGjkWijFKjq4wni6P6SKhzbEalrHnkrdexbFlgkuWgDMviKNbCN9tqt2I8tTF5dEedbjV3QMHIkqxA==","UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("resultType","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));
       // urlBuilder.append("&" + URLEncoder.encode("crno","UTF-8") + "=" + URLEncoder.encode("1101110057012", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("stckIssuCmpyNm","UTF-8") + "=" + URLEncoder.encode(company, "UTF-8"));
        return urlBuilder.toString();
    }

    private Integer convert(Object s){
        return Objects.equals(s.toString(), "") ? 0 : Integer.parseInt(s.toString()) ;
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
        System.out.println(sb.toString());

        return sb.toString();
    }

    public void saveDiviInfo() throws IOException, ParseException {
        List<StockDivideInfo> stockDivideInfos = new ArrayList<>();
        JSONParser jsonParser = new org.json.simple.parser.JSONParser();

        for(Kospi kospi: Kospi.values()){
            String sb = connectUrl(makeUrl(kospi.toString()));

        org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) jsonParser.parse(sb);
        JSONObject array = (JSONObject) jsonObject.get("response");
        JSONObject array1 = (JSONObject) array.get("body");
        JSONObject array2 = (JSONObject) array1.get("items");
        JSONArray array3 = (JSONArray) array2.get("item");

        for(int i=0;i<array3.size();i++) {
            JSONObject object = (JSONObject) array3.get(i);
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
        }
    }
    public void saveDiviDiscInfo() throws IOException, ParseException {
        List<DiscInfo> stock = new ArrayList<>();
        JSONParser jsonParser = new org.json.simple.parser.JSONParser();

        for(Kospi kospi: Kospi.values()) {
            String sb = connectUrl(makeUrl(kospi.toString()));

            org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) jsonParser.parse(sb);
            JSONObject array = (JSONObject) jsonObject.get("response");
            JSONObject array1 = (JSONObject) array.get("body");
            JSONObject array2 = (JSONObject) array1.get("items");
            JSONArray array3 = (JSONArray) array2.get("item");

            for(int i=0;i<array3.size();i++) {
                JSONObject object = (JSONObject) array3.get(i);
                stock.add(DiscInfo.builder().build());
            }

            //respo
        }
    }

    public void saveStockSecuritiesInfoService() throws IOException, ParseException {
        List<StockSecuritiesInfo> stock = new ArrayList<>();
        JSONParser jsonParser = new org.json.simple.parser.JSONParser();

        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1160100/service/GetStocDiviInfoService/getDiviInfo");
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + URLEncoder.encode("H8M+zTGzEGjkWijFKjq4wni6P6SKhzbEalrHnkrdexbFlgkuWgDMviKNbCN9tqt2I8tTF5dEedbjV3QMHIkqxA==","UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1795913", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("resultType","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));

        org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) jsonParser.parse(urlBuilder.toString());
        JSONObject array = (JSONObject) jsonObject.get("response");
        JSONObject array1 = (JSONObject) array.get("body");
        JSONObject array2 = (JSONObject) array1.get("items");
        JSONArray array3 = (JSONArray) array2.get("item");

        for (int i = 0; i < array3.size(); i++) {
            JSONObject object = (JSONObject) array3.get(i);
            stock.add(StockSecuritiesInfo.builder()
                    .basDt(Long.parseLong(object.get("basDt").toString()))
                    .clpr(convert(object.get("clpr")))
                    .build());
            }
        stockSecuritiesInfoRepository.saveAll(stock);
    }
}
