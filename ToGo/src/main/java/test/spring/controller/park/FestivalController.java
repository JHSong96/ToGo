package test.spring.controller.park;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import test.spring.component.park.FestivalDTO;
@Controller
@RequestMapping("/fstvl/*")
public class FestivalController {
	@RequestMapping("/list")
    public String getList(Model model) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://api.data.go.kr/openapi/tn_pubr_public_cltur_fstvl_api"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=g%2BdzVqHbtyZ4yDYOaF3yYrZr0sZPNvlIWf2PAg2uvpPpjJav%2Fm%2B%2Bbyjs5mbKyj1W17CfFilBfaxHTpMupA6%2FxQ%3D%3D"); /*Service Key*/
//        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*������ ��ȣ*/
//       urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("100", "UTF-8")); /*�� ������ ��� ��*/
      urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8")); /*XML/JSON ����*/
//        urlBuilder.append("&" + URLEncoder.encode("fstvlNm","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*������*/
//        urlBuilder.append("&" + URLEncoder.encode("opar","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*�������*/
//        urlBuilder.append("&" + URLEncoder.encode("fstvlStartDate","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*������������*/
//        urlBuilder.append("&" + URLEncoder.encode("fstvlEndDate","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*������������*/
//        urlBuilder.append("&" + URLEncoder.encode("fstvlCo","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*��������*/
//        urlBuilder.append("&" + URLEncoder.encode("mnnst","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*�ְ����*/
//        urlBuilder.append("&" + URLEncoder.encode("auspcInstt","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*���ֱ��*/
//        urlBuilder.append("&" + URLEncoder.encode("suprtInstt","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*�Ŀ����*/
//        urlBuilder.append("&" + URLEncoder.encode("phoneNumber","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*��ȭ��ȣ*/
//        urlBuilder.append("&" + URLEncoder.encode("homepageUrl","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*Ȩ�������ּ�*/
//        urlBuilder.append("&" + URLEncoder.encode("relateInfo","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*��������*/
//        urlBuilder.append("&" + URLEncoder.encode("rdnmadr","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*���������θ��ּ�*/
//        urlBuilder.append("&" + URLEncoder.encode("lnmadr","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*�����������ּ�*/
//        urlBuilder.append("&" + URLEncoder.encode("latitude","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*����*/
//        urlBuilder.append("&" + URLEncoder.encode("longitude","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*�浵*/
//        urlBuilder.append("&" + URLEncoder.encode("referenceDate","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*�����ͱ�������*/
//        urlBuilder.append("&" + URLEncoder.encode("instt_code","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*��������ڵ�*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
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
        String response = sb.toString();
        
     // ������ �Ľ��Ͽ� festivalData ����Ʈ�� �����ϴ� �ڵ�
        List<FestivalDTO> festivalData = parseFestivalData(response);
        
        model.addAttribute("festivalData", festivalData);
        
        return "/park/fstvl";
    }
	

	private List<FestivalDTO> parseFestivalData(String response) {
	    List<FestivalDTO> festivalData = new ArrayList<>();

	    try {
	        // XML �ļ� ����
	        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder builder = factory.newDocumentBuilder();
	        ByteArrayInputStream input = new ByteArrayInputStream(response.getBytes("UTF-8"));
	        Document document = builder.parse(input);

	        // "response" ������Ʈ �Ʒ��� "body" ������Ʈ�� ã���ϴ�.
	        Element responseElement = document.getDocumentElement();
	        Element bodyElement = (Element) responseElement.getElementsByTagName("body").item(0);

	        // "body" ������Ʈ �Ʒ��� "items" ������Ʈ�� ã���ϴ�.
	        Element itemsElement = (Element) bodyElement.getElementsByTagName("items").item(0);

	        // "items" ������Ʈ �Ʒ��� ��� "item" ������Ʈ�� ã���ϴ�.
	        NodeList itemNodeList = itemsElement.getElementsByTagName("item");

	        for (int i = 0; i < itemNodeList.getLength(); i++) {
	            Node itemNode = itemNodeList.item(i);
	            if (itemNode.getNodeType() == Node.ELEMENT_NODE) {
	                Element itemElement = (Element) itemNode;

	                // "item" ������Ʈ���� �ʿ��� �����͸� �����մϴ�.
	                String fstvlNm = getElementValue(itemElement, "fstvlNm");
	                String opar = getElementValue(itemElement, "opar");
	                String fstvlStartDate = getElementValue(itemElement, "fstvlStartDate");
	                String fstvlEndDate = getElementValue(itemElement, "fstvlEndDate");
	                String fstvlCo = getElementValue(itemElement, "fstvlCo");
	                String mnnst = getElementValue(itemElement, "mnnst");
	                String suprtInstt = getElementValue(itemElement, "suprtInstt");
	                String phoneNumber = getElementValue(itemElement, "phoneNumber");
	                String homepageUrl = getElementValue(itemElement, "homepageUrl");

	                // Festival ��ü�� �����ϰ� ����Ʈ�� �߰��մϴ�.
	                FestivalDTO festival = new FestivalDTO();
	                festival.setFstvlNm(fstvlNm);
	                festival.setOpar(opar);
	                festival.setFstvlStartDate(fstvlStartDate);
	                festival.setFstvlEndDate(fstvlEndDate);
	                festival.setFstvlCo(fstvlCo);
	                festival.setMnnst(mnnst);
	                festival.setSuprtInstt(suprtInstt);
	                festival.setPhoneNumber(phoneNumber);
	                festival.setHomepageUrl(homepageUrl);

	                festivalData.add(festival);
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return festivalData;
	}

	// XML ������Ʈ�� ���� �������� ��ƿ��Ƽ �޼ҵ�
	private String getElementValue(Element element, String tagName) {
	    NodeList nodeList = element.getElementsByTagName(tagName);
	    if (nodeList.getLength() > 0) {
	        Node node = nodeList.item(0);
	        return node.getTextContent();
	    } else {
	        System.out.println("Element value for tag " + tagName + " not found.");
	    }
	    return "";
	}
}