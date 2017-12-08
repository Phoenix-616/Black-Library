/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nskgoserver;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author phoenix
 */
public class NetWorker {

    private DBWorker db = null;

    private final String negativeAnswer = "ANSWER\nNEGATIVE\n";
    private final String positiveAnswer = "ANSWER\nPOSITIVE\n";

    public NetWorker(DBWorker worker) {
        db = worker;
    }

    public void activate() throws IOException {
        ServerSocket welcomeSocket = new ServerSocket(14204);

        System.err.println("Server online");

        while (true) {
            Socket connectionSocket = welcomeSocket.accept();
            System.err.println("Connection");
            BufferedReader inFromClient
                    = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            String ans = this.parseIncomingMessage(inFromClient);
            System.err.print(ans);

            outToClient.write(ans.getBytes("UTF-8"));
            outToClient.flush();
            outToClient.close();
        }
    }

    private String parseIncomingMessage(BufferedReader input) throws IOException {
        String str = input.readLine();
        if (str == null) {
            return negativeAnswer;
        }
        if (str.startsWith("GET")) {
            System.err.println("GET");
            return parseGET(input);
        }
        if (str.startsWith("PUT")) {
            System.err.println("PUT");
            return parsePUT(input);
        }
        if (str.startsWith("LOGIN")) {
            System.err.println("LOGIN");
            return parseLOGIN(input);
        }
        if (str.startsWith("POST")) {
            System.err.println("POST");
            return parsePOST(input);
        }
        return negativeAnswer;
    }

    private String parseGET(BufferedReader input) {                                                 //get markers
        String ans = negativeAnswer;
        try {
//            Marker m = new Marker(6, 54.847225, 83.092208, "Общежитие №5", "Второй дом");
//                Marker m2 = new Marker(7, 54.843062, 83.090753, "Новый корпус НГУ", "Альмаматер");
//                Marker m3 = new Marker(8, 54.846338, 83.093425, "СТЦ", "Теперь и с банковскими картами");
            int user_id = Integer.parseInt(input.readLine());
            List<Marker> markers = db.getMarkersList();
//            List<Marker> markers = new LinkedList<>();
//            markers.add(m);
//            markers.add(m2);
//            markers.add(m3);
            Gson gson = new Gson();
            StringBuilder str = new StringBuilder();
            str.append(positiveAnswer).append(markers.size()).append('\n');
            for (Marker mark : markers) {
                str.append(gson.toJson(mark)).append('\n');
//                str.append((db.checkRelation(user_id, mark.getId()))?(1):(0)).append('\n');
                str.append(0).append('\n');
//                    ans = ans + gson.toJson(mark) + "\n" + 0 + "\n";
                //((db.checkRelation(user_id, mark.getId()))?(1):(0))
            }
//                Gson g = new Gson();

//                String ans1 = positiveAnswer + 3 + '\n' + g.toJson(m) + '\n' + 0 + '\n' + g.toJson(m2) + '\n' + 0 + '\n' + g.toJson(m3) + '\n' + 0 + '\n';
//                //System.err.println(ans1);
//                ans = ans1;
            ans = str.toString();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return ans;
    }

    private String parsePUT(BufferedReader input) {                             //set marker positive
        String ans = negativeAnswer;
        try {
            int marker_id, user_id;
            user_id = Integer.parseInt(input.readLine());
            marker_id = Integer.parseInt(input.readLine());
            db.activateMarker(user_id, marker_id);
            ans = positiveAnswer;
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return ans;
    }

    private String parsePOST(BufferedReader input) {                            //new user
        String ans = negativeAnswer;
        try {
            String user, password;
            user = input.readLine();
            password = input.readLine();
            if ((user != null) && (password != null) && (db.createUser(user, password))) {
                int id = db.getUserId(user);
                ans = positiveAnswer + id + '\n';
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return ans;
    }

    private String parseLOGIN(BufferedReader input) {                           //login
        String ans = negativeAnswer;
        try {
            String user, password;
            user = input.readLine();
            password = input.readLine();
            if ((user != null) && (password != null) && (db.checkUser(user, password))) {
                int id = db.getUserId(user);
                ans = positiveAnswer + id + '\n';
            }
            //System.err.println(input.readLine());
            //ans = positiveAnswer + 1 + "\n";
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return ans;
    }

}
