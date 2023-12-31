package test.spring.service.choi;

import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import test.spring.component.choi.KakaoDTO;

//@Repository
public class MemberRepository {

	@Autowired
	private SqlSessionTemplate sql;
	
	// 정보 저장
	public void kakaoinsert(HashMap<String, Object> userInfo) {
		sql.insert("Member.kakaoInsert",userInfo);
	}

	// 정보 확인
	public KakaoDTO findkakao(HashMap<String, Object> userInfo) {
		System.out.println("RN:"+userInfo.get("nickname"));
		System.out.println("RE:"+userInfo.get("email"));
		return sql.selectOne("Member.findKakao", userInfo);
	}

}
