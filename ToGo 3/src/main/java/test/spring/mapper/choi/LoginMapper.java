package test.spring.mapper.choi;

import org.springframework.stereotype.Repository;

import test.spring.component.choi.KakaoDTO;
@Repository
public interface LoginMapper {
	public KakaoDTO getUserInfo(String access_Token);
	public KakaoDTO kakaoNumber(KakaoDTO userInfo);
	public void kakaoInsert (KakaoDTO dto);
	public int check(String id);
}
