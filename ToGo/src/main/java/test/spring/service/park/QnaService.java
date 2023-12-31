package test.spring.service.park;

import java.util.List;

import test.spring.component.park.QnaDTO;

public interface QnaService {
	public void qnaInsert(QnaDTO dto);			
	public List<QnaDTO> qnaList(QnaDTO dto);
	public QnaDTO qnaDetail(int num);			
	public void qnaUpdate(QnaDTO dto);			
	public void qnaDelete(int num);				
	public void qnaRead(int num);				
	public void qnaReplyInsert(QnaDTO dto);		
	public int totalList(QnaDTO dto);
	public List<QnaDTO> qnaMyList(QnaDTO dto);
	public int totalMyList(String id);
	public List<QnaDTO> qnaWaiting();
	public int totalWaitingList();
}
