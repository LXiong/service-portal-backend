package com.ai.paas.ipaas.user.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ai.paas.ipaas.PaaSMgmtConstant;
import com.ai.paas.ipaas.PaasException;
import com.ai.paas.ipaas.user.dubbo.interfaces.IaasConsoleDubboSv;
import com.ai.paas.ipaas.user.dubbo.vo.ResponseHeader;
import com.ai.paas.ipaas.user.dubbo.vo.SelectWithNoPageRequest;
import com.ai.paas.ipaas.user.dubbo.vo.SelectWithNoPageResponse;
import com.ai.paas.ipaas.user.dubbo.vo.UserProdInstVo;
import com.ai.paas.ipaas.user.service.IDssConsoleSv;
import com.ai.paas.ipaas.user.service.IIaasConsoleSv;
import com.ai.paas.ipaas.util.StringUtil;
import com.alibaba.dubbo.config.annotation.Service;
@Service
public class IaasConsoleDubboSvImpl implements IaasConsoleDubboSv {
	
	private final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private IIaasConsoleSv iIaasConsoleSv;

	@Override
	public SelectWithNoPageResponse<UserProdInstVo> selectUserProdInsts(
			SelectWithNoPageRequest<UserProdInstVo> request) {
		// TODO Auto-generated method stub
		ResponseHeader responseHeader = new ResponseHeader();	
		List<UserProdInstVo>   resultList  = new ArrayList<UserProdInstVo>();
		try{
			this.validate(request.getSelectRequestVo());
			resultList = iIaasConsoleSv.selectUserProdInsts(request.getSelectRequestVo());
			responseHeader.setResultCode(PaaSMgmtConstant.REST_SERVICE_RESULT_SUCCESS);
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			responseHeader.setResultCode(PaaSMgmtConstant.REST_SERVICE_RESULT_FAIL);
			responseHeader.setResultMessage(e.getMessage());
		}		
		SelectWithNoPageResponse<UserProdInstVo> response = new SelectWithNoPageResponse<UserProdInstVo>();
		response.setResultList(resultList);
		response.setResponseHeader(responseHeader);		
		return response;
	}
	
	
	private void validate(UserProdInstVo vo) throws  PaasException{
		if(null == vo){
			throw new PaasException("入参对象为空！");
		}
		if(StringUtil.isBlank(vo.getUserId())){
			throw new PaasException("用户编码为空！");
		}
	}
	

}
