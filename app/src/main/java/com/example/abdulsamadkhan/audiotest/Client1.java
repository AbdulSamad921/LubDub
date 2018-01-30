package com.example.abdulsamadkhan.audiotest;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Abdul Samad Khan on 5/22/2017.
 */

public class Client1 extends AsyncTask<Object, Object, String> {

    String dstAddress;
    int dstPort;
    String response = "";

    String reply;

    Client1(String addr, int port, String textResponse) {
        dstAddress = addr;
        dstPort = port;
        reply=textResponse;

    }

    @Override
    protected String doInBackground(Object... arg0) {

        Socket socket = null;

        try {

            socket = new Socket(dstAddress, dstPort);


            SocketClientReplyThread socketClientReplyThread =
                    new SocketClientReplyThread(socket);
            socketClientReplyThread.run();

			/*
             * notice: inputStream.read() will block if no data return
			 */



        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response = "UnknownHostException: " + e.toString();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response = "IOException: " + e.toString();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return response;
    }
    private class SocketClientReplyThread extends Thread {

        private Socket hostThreadSocket;


        SocketClientReplyThread(Socket socket) {
            hostThreadSocket = socket;

        }

        @Override
        public void run() {
            OutputStream outputStream;


            try {
                outputStream = hostThreadSocket.getOutputStream();
                PrintStream printStream = new PrintStream(outputStream);
                printStream.print(reply);
                printStream.close();


            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();

            }


        }

    }



}