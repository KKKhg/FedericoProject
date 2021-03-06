package service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j;
import mapperInterface.HeadOfficeMapper;
import paging.SearchCriteria;
import vo.ChartVO;
import vo.EventBoardVO;
import vo.FcOrderDetailVO;
import vo.FcOrderVO;
import vo.FranchiseVO;
import vo.HeadOfficeVO;
import vo.ItemInfoVO;
import vo.NoticeBoardVO;
import vo.StaffVO;

@Log4j
@Service
public class HeadOfficeServiceImpl implements HeadOfficeService {

	@Autowired
	HeadOfficeMapper dao;

	
	//회원 연령대 조회
	@Override
	public List<ChartVO> selectMemberAgeGroup() {
		return dao.selectMemberAgeGroup();
	}
	
	//연령대별 메뉴 판매량 by 지점
	@Override
	public List<ChartVO> selectStatsAgeGroupMenuSales(Map<String, Object> parmas) {
		return dao.selectStatsAgeGroupMenuSales(parmas);
	}
	
	//연령대별 매출 by 년도
	@Override
	public List<ChartVO> selectStatsAgeGroupSales(Map<String, Object> parmas) {
		return dao.selectStatsAgeGroupSales(parmas);
	}
	
	//가맹점 개설현황 - 매개변수로 where 조건
	@Override
	public ChartVO selectNumberOfFranchise(String s) {
		return dao.selectNumberOfFranchise(s);
	}
	
	// 본사 홈에 띄울 가맹점 미처리 자재발주
	@Override
	public List<FcOrderVO> selectFcOrderSumPirce() {
		return dao.selectFcOrderSumPirce();
	}
	
	//가맹점 자재발주 상세내역 등록
	@Override
	public int insertFcOrderDetail(List<FcOrderDetailVO> vo) {
		return dao.insertFcOrderDetail(vo);
	}
	
	
	// 가맹점 발주 등록
	@Override
	public int insertFcOrder(FcOrderVO vo) {
		return dao.insertFcOrder(vo);
	}
	
	// 분류로 자재 조회
	@Override
	public List<ItemInfoVO> selectItembyFlag(ItemInfoVO vo) {
		return dao.selectItembyFlag(vo);
	}
	
	// 가맹점 발주내역 처리완료로 변경
	@Override
	public int fcOrderSeqUpdate(Map<String, Object> params) {
		return dao.fcOrderSeqUpdate(params);
	}

	// 가맹점 발주내역 상세보기(발주번호 별로)
	@Override
	public List<FcOrderDetailVO> selectFcOrderDetailbyOrderNumber(FcOrderDetailVO vo) {

		return dao.selectFcOrderDetailbyOrderNumber(vo);
	}

	// 가맹점발주정보 전체조회
	@Override
	public List<FcOrderVO> selectFcOrder(String flag) {
		return dao.selectFcOrder(flag);
	}

	// 가맹점발주정보 조회,서치,페이징
	@Override
	public List<FcOrderVO> searchFcOrder(Map<String, Object> params) {
		return dao.searchFcOrder(params);
	}

	// 가맹점발주정보 Search 결과 Rows 조회
	@Override
	public int searchFcOrderRows(Map<String, Object> params) {
		return dao.searchFcOrderRows(params);
	}

	// 자재정보 삭제
	@Override
	public int itemdelete(ItemInfoVO vo) {
		return dao.itemdelete(vo);
	}

	// 자재정보 수정
	@Override
	public int itemUpdate(ItemInfoVO vo) {
		return dao.itemUpdate(vo);
	}

	// 자재정보 1건 출력
	@Override
	public ItemInfoVO selectOneItem(ItemInfoVO vo) {
		return dao.selectOneItem(vo);
	}

	// 자재정보 Search
	@Override
	public List<ItemInfoVO> searchItemList(SearchCriteria cri) {
		return dao.searchItemList(cri);
	}

	// 자재정보 Search 결과 Rows 조회
	@Override
	public int searchItemRows(SearchCriteria cri) {
		return dao.searchItemRows(cri);
	}

	// 자재정보 모두출력
	@Override
	public List<ItemInfoVO> selectAllItem() {
		return dao.selectAllItem();
	}

	// 본사: 자재입력기능 (강현구)
	@Override
	public int iteminsert(ItemInfoVO vo) {
		return dao.iteminsert(vo);
	}

	// Login 시 사용
	@Override
	public HeadOfficeVO loginSelectOne(HeadOfficeVO vo) {
		return dao.loginSelectOne(vo);
	}

	// LoginSelectOne에 정보 보낼때 사용, 스탭정보 가져올때 사용
	@Override
	public StaffVO selectOne(StaffVO vo) {
		return dao.selectOne(vo);
	}

	// Staff 계정생성
	@Override
	public int staffInsert(StaffVO vo) {
		return dao.staffInsert(vo);
	}

	// Staff 정보수정
	@Override
	public int staffUpdate(StaffVO vo) {
		return dao.staffUpdate(vo);
	}

	// Staff 정보삭제
	@Override
	public int staffDelete(StaffVO vo) {
		return dao.staffDelete(vo);
	}

	// HeadOffice Id, password 생성
	@Override
	public int headOfficeInsert(HeadOfficeVO vo) {
		return dao.headOfficeInsert(vo);
	}

	// HeadOffice 계정 삭제
	@Override
	public int headOfficeDelete(HeadOfficeVO vo) {
		return dao.headOfficeDelete(vo);
	}

	// 사원정보리스트
	@Override
	public List<StaffVO> selectMList(StaffVO vo) {
		return dao.selectMList(vo);
	}

	// password 변경
	@Override
	public int headOfficePwUpdate(HeadOfficeVO vo) {
		return dao.headOfficePwUpdate(vo);
	}

	// 스탭정보 Search
	@Override
	public List<StaffVO> searchStaffList(SearchCriteria cri) {
		return dao.searchStaffList(cri);
	}

	// 스탭정보 Search 결과 Rows 조회
	@Override
	public int searchStaffRows(SearchCriteria cri) {
		return dao.searchStaffRows(cri);
	}
	
	 // 가맹점정보 Search
	@Override
	public List<FranchiseVO> searchFcList(SearchCriteria cri) {
		return dao.searchFcList(cri);
	}
	// 가맹점정보 Search 결과 Rows 조회
	@Override
	public int searchFcRows(SearchCriteria cri) {
		return dao.searchFcRows(cri);
	}
	
	//공지사항 등록
	@Override
	public int noticeInsert(NoticeBoardVO vo) {
		return dao.noticeInsert(vo);
	}
	//공지사항 수정
	@Override
	public int noticeUpdate(NoticeBoardVO vo) {
		return dao.noticeUpdate(vo);
	}
	//공지사항 삭제
	@Override
	public int noticeDelete(NoticeBoardVO vo) {
		return dao.noticeDelete(vo);
	}
	
	
	
	
	
	//월별차트
	@Override
	public List<ChartVO> monthChart() {
		return dao.monthChart();
	}
	//요일별차트
	@Override
	public List<ChartVO> dayChart() {
		return dao.dayChart();
	}
	
	@Override
	public int eventInsert(EventBoardVO vo) {
		return dao.eventInsert(vo);
	}
	
	@Override
	public int eventUpdate(EventBoardVO vo) {
		return dao.eventUpdate(vo);
	}
	
	@Override
	public int eventDelete(EventBoardVO vo) {
		return dao.eventDelete(vo);
	}
	
	@Override
	public EventBoardVO selectDetailEventBoard(EventBoardVO vo) {
		return dao.selectDetailEventBoard(vo);
	}
	@Override
	public List<EventBoardVO> searchEventBoard(SearchCriteria cri) {
		return dao.searchEventBoard(cri);
	}
	
	@Override
	public int searchEventBoardRows(SearchCriteria cri) {
		return dao.searchEventBoardRows(cri);
	}
}
