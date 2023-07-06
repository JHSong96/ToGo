package test.spring.controller.park;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
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

import test.spring.component.park.FstvlDTO;

@Controller
@RequestMapping("/fstvl/*")
public class fstvlTest {
    @RequestMapping("/test")
    public String getList(Model model) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B551011/KorService1"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=g%2BdzVqHbtyZ4yDYOaF3yYrZr0sZPNvlIWf2PAg2uvpPpjJav%2Fm%2B%2Bbyjs5mbKyj1W17CfFilBfaxHTpMupA6%2FxQ%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); 
        urlBuilder.append("&" + URLEncoder.encode("MobileOS","UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("MobileApp","UTF-8") + "=" + URLEncoder.encode("AppTest", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("listYN","UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("arrange","UTF-8") + "=" + URLEncoder.encode("A", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("eventStartDate","UTF-8") + "=" + URLEncoder.encode("20170901", "UTF-8")); 

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
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
        List<FstvlDTO> festivals = parseFestivalData(response);
        
        model.addAttribute("festivals", festivals);
        
        return "/festivals";
    }

    private List<FstvlDTO> parseFestivalData(String response) {
        List<FstvlDTO> festivalData = new ArrayList<>();

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
                    String title = getElementValue(itemElement, "title");
                    String firstimage = getElementValue(itemElement, "firstimage");
                    String eventstartdate = getElementValue(itemElement, "eventstartdate");
                    String eventenddate = getElementValue(itemElement, "eventenddate");

                    // Festival ��ü�� �����ϰ� ����Ʈ�� �߰��մϴ�.
                    FstvlDTO festival = new FstvlDTO();
                    if (title != null) {
                        festival.setTitle(title);
                    }
                    if (firstimage != null) {
                        festival.setFirstimage(firstimage);
                    }
                    if (eventstartdate != null) {
                        festival.setEventstartdate(eventstartdate);
                    }
                    if (eventenddate != null) {
                        festival.setEventenddate(eventenddate);
                    }

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
            return ""; // ������Ʈ ���� ���� ��� �� ���ڿ��� ��ȯ�ϵ��� ����
        }
    }

}
