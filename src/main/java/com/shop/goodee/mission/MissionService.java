package com.shop.goodee.mission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.goodee.purchase.PurchaseVO;

@Service
public class MissionService {
	
	@Autowired
	private MissionMapper missionMapper;
	
	//지원하기-추첨형
	public int setApply(MissionVO missionVO) throws Exception{
		return missionMapper.setApply(missionVO);
	}
	
	//지원하기-즉석추첨형
	public int setApply_baro(MissionVO missionVO) throws Exception {
		return missionMapper.setApply_baro(missionVO);
	}
	
	//지원내역
	public MissionVO getApply(MissionVO missionVO) throws Exception {
		return missionMapper.getApply(missionVO);
	}
	
	//중복지원확인
	public Long getApplyCheck(MissionVO missionVO) throws Exception {
		return missionMapper.getApplyCheck(missionVO);
	}
	
	//지원취소
	public int setCancel(MissionVO missionVO) throws Exception {
		return missionMapper.setCancel(missionVO);
	}
	
	//네이버 닉네임 등록
	public int setNicN(PurchaseVO purchaseVO) throws Exception {
		return missionMapper.setNicN(purchaseVO);
	}
	
	//쿠팡 닉네임 등록
	public int setNicC(PurchaseVO purchaseVO) throws Exception {
		return missionMapper.setNicC(purchaseVO);
	}
	
	//미션 상황 status 변경
	public int setMiStatus1(PurchaseVO purchaseVO) throws Exception{
		return missionMapper.setMiStatus1(purchaseVO);
	}
	
	public int setMiStatus2(PurchaseVO purchaseVO) throws Exception{
		return missionMapper.setMiStatus2(purchaseVO);
	}
	
	//모집률
	public int getApplyRate(MissionVO missionVO) throws Exception {
		return missionMapper.getApplyRate(missionVO);
	}
}
