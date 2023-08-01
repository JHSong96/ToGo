package test.spring.service.kim;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;

import test.spring.component.kim.CityAndPlaces;
import test.spring.component.kim.Pos;
import test.spring.component.kim.kimDTO;

public interface UserIpService {
    public List<kimDTO> reco_place();

    public List<kimDTO> reco_place_user_main(Map<String, Object> params);

    public List<kimDTO> reco_place_user_sub(Map<String, Object> params);
    
    public ArrayList<CityAndPlaces> getCityAndPlaces(Pos pos, boolean isMain, boolean isSub);

}