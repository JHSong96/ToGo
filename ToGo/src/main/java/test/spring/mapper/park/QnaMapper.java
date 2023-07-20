package test.spring.mapper.park;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import test.spring.component.park.QnaDTO;
@Mapper
public interface QnaMapper {
	public void qnaInsert(QnaDTO dto);			//�� ����
	public List<QnaDTO> qnaList(QnaDTO dto);	//��� ��ȸ
	public QnaDTO qnaDetail(int num);			//�� ��ȸ
	public void qnaUpdate(QnaDTO dto);			//�� ����
	public void qnaDelete(int num);				//�� ����
	public void qnaRead(int num);				//��ȸ�� ���� ó��
	public void qnaReplyInsert(QnaDTO dto);		//��� ����
	public int totalList(QnaDTO dto);
}
