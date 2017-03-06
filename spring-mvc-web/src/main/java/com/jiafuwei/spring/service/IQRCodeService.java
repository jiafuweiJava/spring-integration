package com.jiafuwei.spring.service;

import com.jiafuwei.spring.po.QRCode;


public interface IQRCodeService {
	
	public QRCode getQRCodeById(String key_id);
	
	public int insert(QRCode qRCode);
}
