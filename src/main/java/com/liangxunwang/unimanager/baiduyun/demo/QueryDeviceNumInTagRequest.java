package com.liangxunwang.unimanager.baiduyun.demo;


import com.liangxunwang.unimanager.baiduyun.annotation.HttpParamKeyName;
import com.liangxunwang.unimanager.baiduyun.annotation.R;
import com.liangxunwang.unimanager.baiduyun.annotation.RangeRestrict;

public class QueryDeviceNumInTagRequest extends PushRequest {

	@HttpParamKeyName(name= BaiduPushConstants.TAG_NAME, param= R.REQUIRE)
	@RangeRestrict(minLength=1, maxLength=128)
	private String tagName = null;
	
	// get
	public String getTagName () {
		return tagName;
	}
	// set
	public void setTagName (String tagName) {
		this.tagName = tagName;
	}
	// add
	public QueryDeviceNumInTagRequest addTagName (String tagName) {
		this.tagName = tagName;
		return this;
	}
    public QueryDeviceNumInTagRequest addDeviceType (Integer deviceType) {
    	this.deviceType = deviceType;
    	return this;
    }
	public QueryDeviceNumInTagRequest addExpires(Long requestTimeOut) {
		this.expires = requestTimeOut;
		return this;
	}
}
