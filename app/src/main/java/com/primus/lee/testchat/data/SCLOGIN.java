package com.primus.lee.testchat.data;

import com.primus.lee.testchat.data.Obj;

import java.nio.ByteBuffer;
//m_ResultCode 0: ����  1: ����
  
public class SCLOGIN extends Obj { 
	 public short m_ResultCode; 
	 public boolean putbuf( ByteBuffer buf){ 
		 buf.putShort( m_ResultCode );
		 return true; 
	 }  
	 public boolean getbuf(ByteBuffer buf){ 
		 m_ResultCode = buf.getShort();
		 return true; 
	 }  
	 public void clear(){ 
		 m_ResultCode = 0;
	 }  
};  
