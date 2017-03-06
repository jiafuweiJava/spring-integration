package com.jiafuwei.spring.dao;

import com.jiafuwei.spring.po.QRCode;


public interface IQRCodeDao {

	QRCode selectByPrimaryKey(String key_id);

	int insert(QRCode qRCode);
}
