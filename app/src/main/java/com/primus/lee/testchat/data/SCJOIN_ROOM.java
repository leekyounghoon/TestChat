package com.primus.lee.testchat.data;

import com.primus.lee.testchat.data.Obj;

import java.nio.ByteBuffer;
// ResultCode 0: ���� 1: ���� 2: ���� ã�� ������.
    
public class SCJOIN_ROOM extends Obj { 
	 public short m_ResultCode; 
	 public ArrayListT_USER_INFO m_UserInfo = new ArrayListT_USER_INFO(); 
	 public boolean putbuf( ByteBuffer buf){ 
		 buf.putShort( m_ResultCode );
		 m_UserInfo.putbuf( buf );
		 return true; 
	 }  
	 public boolean getbuf(ByteBuffer buf){ 
		 m_ResultCode = buf.getShort();
		 m_UserInfo.getbuf( buf );
		 return true; 
	 }  
	 public void clear(){ 
		 m_ResultCode = 0;
		 m_UserInfo.clear();
	 }  
};  
