package com.jiafuwei.spring.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jiafuwei.spring.dao.IQRCodeDao;
import com.jiafuwei.spring.po.QRCode;
import com.jiafuwei.spring.service.IQRCodeService;

@Service("qRCodeService")
public class QRCodeServiceImpl implements IQRCodeService {
	@Resource
	private IQRCodeDao qRCodeDao;

	public QRCode getQRCodeById(String key_id) {
		// TODO Auto-generated method stub
		QRCode qRCode = qRCodeDao.selectByPrimaryKey(key_id);
		return qRCode;
	}

	public int insert(QRCode qRCode) {
		// TODO Auto-generated method stub
		int key_id = qRCodeDao.insert(qRCode);
		return key_id;
	}
	
	

}  
