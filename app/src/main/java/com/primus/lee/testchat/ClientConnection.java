package com.primus.lee.testchat;

import com.primus.lee.testchat.data.Obj;
import com.primus.lee.testchat.data.Protocol;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SocketChannel;

/**
 * Created by Lee on 2015-08-26.
 */
public class ClientConnection extends Thread{
    private Socket msocket;

    //member
    private boolean mEnd = true;
    private SocketChannel mSC = null;

    public String   m_ServerIP;
    public int      m_ServerPort;

    public ClientConnection() { }

    public int Begin(String ip, int port){
        InetSocketAddress address = new InetSocketAddress(ip, port);
        try {
            mSC = SocketChannel.open(address);
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
        start();
        return 1;
    }

    public void End() {
        mEnd = true;
        try {
            mSC.finishConnect();
            mSC.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        // System.out.println("ClientConnection::run()");
        mEnd = false;
        ByteBuffer bb = ByteBuffer.allocate(4096);
        bb.order(ByteOrder.LITTLE_ENDIAN);

        while (!mEnd) {
            try {
                if (mSC.read(bb) == -1) {
                    break;
                }

                CheckPacket(mSC, bb);

                Thread.sleep(0,1);
            }
            catch ( SocketTimeoutException ste) {
                if( Thread.currentThread().isInterrupted() == true ){
                    ste.printStackTrace();
                    mEnd = true;
                }
                break;
            }
            catch (Exception e ) {
                 e.printStackTrace();
                break;
            }

        }

        try {
            mSC.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        bb.clear();
    }


    /////////////////////////////// UTIL
    // Send
    public void Send(ByteBuffer data) {
        try {
            int s = mSC.write(data);
        } catch (Exception e) {
            e.printStackTrace();
            this.End();
        }
    }

    public void makePacket_Send(short PacketNum, Obj object) {
        // buffer loading
        ByteBuffer buf = ByteBuffer.allocate(1024);
        buf.clear();
        buf.order(ByteOrder.LITTLE_ENDIAN);

        buf.putShort((short) 0);
        buf.putShort(PacketNum);
        buf.putInt(0); // serial

        // Context
        object.putbuf(buf);
        short len = (short) (buf.position());
        buf.putShort(0, len);
        buf.flip();

        Send(buf);
    }

    public void CheckPacket(SocketChannel sc, ByteBuffer bb) {
        bb.flip();
        while (bb.remaining() > 2) {
            bb.mark();
            short len = bb.getShort();
            bb.reset();

            if (bb.remaining() < len) {
                break;
            }

            byte[] data = new byte[len];
            bb.get(data);

            OnRecv(sc, data);
        }
        bb.compact();
    }

    public void OnRecv(SocketChannel sc, byte[] data) {
        ByteBuffer bb = ByteBuffer.wrap(data);
        bb.order(ByteOrder.LITTLE_ENDIAN);

        short len = bb.getShort();
        short msg = bb.getShort();
        int Serial = bb.getInt();

        switch (msg) {
            // case TestProtocol.GC_LOGIN:
            case Protocol.SC_LOGIN:
               // Recv_MsgLogin(sc, bb);
                break;
            case Protocol.SC_MAKE_ROOM:
               // Recv_MsgMakeRoom( sc, bb);
                break;
            case Protocol.SC_JOIN_ROOM:
               // Recv_MsgJoinRoom( sc, bb);
                break;
            case Protocol.SC_LEAVE_ROOM:
              //  Recv_MsgLeaveRoom( sc, bb);
                break;
            case Protocol.SC_START_ROOM:
              //  Recv_MsgStartRoom( sc,bb);
                break;

            default:
                break;
        }



    }


}
