package test.spring.mapper.park;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import test.spring.component.park.CmBoardDTO;
@Mapper
public interface CmMapper {
	// �ֱ� �Խù� ��ȣ
	public Long selectBoardMax();
	// �ֱ� ��� ��ȣ
	public Long selectCommentMax(CmBoardDTO dto);
	// �Խù� ���
	public int insertBoard(CmBoardDTO dto);
	// �Խù� �Ѱ� ��ȸ
	public CmBoardDTO selectBoardDetail(Long cm_no);
	// �Խù� ��� ��ȸ
	public List<CmBoardDTO> selectBoardList(CmBoardDTO dto);
	// ��� ����Ʈ
	public List<CmBoardDTO> selectCommentList(CmBoardDTO dto);
	// �Խñ��� ������ ��ȸ
	public int selectBoardTotalCount(CmBoardDTO dto);
	// �Խù� ����
	public int updateBoard(CmBoardDTO dto);
	// �Խù� ����
	public int deleteBoard(CmBoardDTO dto);
	// ���� �� �� ��ȸ
	public List<CmBoardDTO> selectMypost(CmBoardDTO dto);
	// ���� �� ���� ������ ��ȸ CmBoardDTO
	public int selectMyPostTotalCount(CmBoardDTO dto);
	// ��� �� ��ȸ
	public int commentCnt(Long cm_no);
	// �Խù� ��ȸ�� ����
	public int updatereadcnt(Long cm_no);
}
