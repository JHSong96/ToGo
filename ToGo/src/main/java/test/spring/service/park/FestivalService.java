package test.spring.service.park;

import java.util.List;

import test.spring.component.park.FstvlDTO;


public interface FestivalService {
	public List<FstvlDTO> fstvlList(FstvlDTO dto);
//	public FstvlDTO testCrawling(String testURL);
//	public void saveFestivals(FstvlDTO dto);
}
