package test.spring.mapper.park;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import test.spring.component.park.QnaDTO;
import test.spring.component.park.QnaPage;
@Mapper
public interface QnaMapper {
	public void qnaInsert(QnaDTO dto);			//�� ����
	public List<QnaDTO> qnaList();				//��� ��ȸ
	public QnaPage qnaList(QnaPage page);		//������ ó�� �� ������ ��� ��ȸ
	public QnaDTO qnaDetail(int num);			//�� ��ȸ
	public void qnaUpdate(QnaDTO dto);			//�� ����
	public void qnaDelete(int no);				//�� ����
	public void qnaRead(int no);				//��ȸ�� ���� ó��
	public void qnaReplyInsert(QnaDTO dto);		//��� ����
	public int totalList(QnaPage page);
	public List<QnaDTO> list(QnaPage page);
}
