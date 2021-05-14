package tn.esprit.pidev.services;

import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import tn.esprit.pidev.entities.Test;
import tn.esprit.pidev.utils.Database;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestService {
    ArrayList<Test> testArrayList = new ArrayList<>();
    public static TestService instance = null;
    public boolean resultOK;
    private ConnectionRequest connectionRequest;

    public TestService() {
        connectionRequest = new ConnectionRequest();
    }

    public static TestService getInstance() {
        if (instance == null) {
            instance = new TestService();
        }
        return instance;
    }

    public ArrayList<Test> parseTest(String jsonText) {
        try {
            testArrayList = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> testListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) testListJson.get("root");
            for (Map<String, Object> obj : list) {
                Test test = new Test();
                test.setId((int) Float.parseFloat(obj.get("idtest").toString()));
                test.setName(obj.get("nom").toString());
                test.setCategory(obj.get("cat").toString());
                test.setContenu(obj.get("contenu").toString());
                test.setTeacherName(obj.get("noenseig").toString());
                test.setDate(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(obj.get("datetest").toString().substring(0, 10)).getTime()));
                testArrayList.add(test);
            }
        } catch (IOException | ParseException ex) {
        }
        return testArrayList;
    }

    public ArrayList<Test> showAll() {
        String url = Database.BASE_URL + "test/api/show"; // Add Symfony URL Here
        connectionRequest.setUrl(url);
        connectionRequest.setPost(false);
        connectionRequest.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                testArrayList = parseTest(new String(connectionRequest.getResponseData()));
                connectionRequest.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(connectionRequest);
        return testArrayList;
    }
}
