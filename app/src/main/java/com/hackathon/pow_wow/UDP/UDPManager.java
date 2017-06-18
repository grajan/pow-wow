package com.hackathon.pow_wow.UDP;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Rajan on 6/18/17.
 */

public class UDPManager {

    public static void startServer() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    DatagramSocket serverSocket = new DatagramSocket(9876);
                    byte[] receiveData = new byte[1024];
                    byte[] sendData = new byte[1024];
                    while (true) {
                        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                        serverSocket.receive(receivePacket);
                        String sentence = new String(receivePacket.getData());
                        System.out.println("RECEIVED: " + sentence);
                        InetAddress IPAddress = receivePacket.getAddress();

//                        InetAddress IPAddress = InetAddress.getByName("255.255.255.255");
                        int port = receivePacket.getPort();
//                        String capitalizedSentence = sentence.toUpperCase();
//                        sendData = capitalizedSentence.getBytes();
                        DatagramPacket sendPacket =
                                new DatagramPacket(sendData, sendData.length, IPAddress, port);
                        serverSocket.send(sendPacket);
                    }
                }
                catch (Exception e) {
                    Log.i("Exception", "startServer: ");
                }
            }
        }).start();
    }

    public static void startClient() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {

//            BufferedReader inFromUser =
//                    new BufferedReader(new InputStreamReader(System.in));
                    DatagramSocket clientSocket = new DatagramSocket();
                    InetAddress IPAddress = InetAddress.getByName("255.255.255.255");
                    byte[] sendData = new byte[1024];
                    byte[] receiveData = new byte[1024];
                    String sentence = "Samp data";
                sendData = sentence.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
        clientSocket.send(sendPacket);
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    clientSocket.receive(receivePacket);
                    String modifiedSentence = new String(receivePacket.getData());
                    System.out.println("FROM SERVER:" + modifiedSentence);
                    clientSocket.close();
                }
                catch (Exception e) {
                    Log.i("Exception", "startServer: ");
                }

            }
        }).start();

    }


}
