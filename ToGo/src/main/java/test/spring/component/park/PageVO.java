package test.spring.component.park;

public class PageVO {
	private int pageList = 10; //�������� ��ϼ�
	private int blockPage = 10; //���� ��������
	private int totalList; //�Ѹ�ϼ�
	private int totalPage; //����������
	//157 ������ = �Ѹ�ϼ�/�������� ��ϼ� + �������� ������+1
	private int totalBlock; //�Ѻ���
	//16 �� = ����������/���� �������� + �������� ������+1
	private int curPage; //������������ȣ
	private int beginList, endList; //������������ ����/�� ��Ϲ�ȣ
	private int beginPage, endPage; //������� ����/�� ��������ȣ
	
	private String search, keyword; //�˻�����, �˻���
	
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getPageList() {
		return pageList;
	}
	public void setPageList(int pageList) {
		this.pageList = pageList;
	}
	public int getBlockPage() {
		return blockPage;
	}
	public void setBlockPage(int blockPage) {
		this.blockPage = blockPage;
	}
	public int getTotalList() {
		return totalList;
	}
	public void setTotalList(int totalList) {
		this.totalList = totalList;
		
		//����������=�Ѹ�ϼ�/�������纸������ϼ�
		//576/10 --> 57 ..6 -> 58������
		totalPage = totalList / pageList;
		if( totalList % pageList >0 ) ++totalPage;
		
		//�Ѻ���=����������/���纸������������
		//58/10 --> 5..8 -> 6��
		totalBlock = totalPage / blockPage;
		if( totalPage % blockPage > 0 ) ++totalBlock;
		
		//����/�� ��Ϲ�ȣ
		//����Ϲ�ȣ: 576, 566, 556, 
		endList = totalList - (curPage-1) * pageList;
		//���۸�Ϲ�ȣ: 567, 557, 547, 
		//= ����Ϲ�ȣ - (�������纸������ϼ�-1) 
		beginList = endList - (pageList-1);
		
		//���� ����ȣ 
		curBlock = curPage / blockPage;
		if( curPage % blockPage > 0 ) ++curBlock;
		//����/�� ��������ȣ
		//����������ȣ: 10, 20, 30, ...
		endPage = curBlock * blockPage;
		//������������ȣ : 1, 11, 21, ...
		beginPage = endPage - (blockPage-1);
		
		//2048�� �� 1������ : 2048 ~ 2039, 1 ~ 10
		//			205������ : 8 ~ 1, 51 ~ 58
		//�� ������ ��ȣ�� �� ������ ��ȣ���� ũ�� �� ������ ��ȣ�� �� ������ ��ȣ�̴�.
		if(endPage > totalPage) {endPage = totalPage; }
	}
	private int curBlock; //�������ȣ 
	
	public int getCurBlock() {
		return curBlock;
	}
	public void setCurBlock(int curBlock) {
		this.curBlock = curBlock;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalBlock() {
		return totalBlock;
	}
	public void setTotalBlock(int totalBlock) {
		this.totalBlock = totalBlock;
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getBeginList() {
		return beginList;
	}
	public void setBeginList(int beginList) {
		this.beginList = beginList;
	}
	public int getEndList() {
		return endList;
	}
	public void setEndList(int endList) {
		this.endList = endList;
	}
	public int getBeginPage() {
		return beginPage;
	}
	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
}
