package com.primus.lee.testchat.data;

import java.nio.ByteBuffer;

public abstract class Obj {
	public Obj(){}
	public boolean putbuf(ByteBuffer buf){return true;};
	public boolean getbuf(ByteBuffer buf){return true;};
}
