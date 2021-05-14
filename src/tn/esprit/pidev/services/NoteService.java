package tn.esprit.pidev.services;

import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import tn.esprit.pidev.entities.Note;
import tn.esprit.pidev.utils.Database;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NoteService {
    ArrayList<Note> noteArrayList = new ArrayList<>();
    public static NoteService instance=null;
    public boolean resultOK;
    private ConnectionRequest connectionRequest;

    public NoteService() {
        connectionRequest = new ConnectionRequest();
    }

    public static NoteService getInstance() {
        if (instance == null) {
            instance = new NoteService();
        }
        return instance;
    }
    public ArrayList<Note> parseNote(String jsonText) {
        try {
            noteArrayList = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> noteListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) noteListJson.get("root");
            for (Map<String, Object> obj : list) {
                Note test = new Note();
                noteArrayList.add(test);
            }
        } catch (IOException ex) {
        }
        return noteArrayList;
    }
    public boolean addNote(Note note, int id) {
        String url = Database.BASE_URL + "note/api/answer?idTest="+id+
                "&studentName="+note.getStudentName()+
                "&result="+note.getResult(); // Add Symfony URL here
        connectionRequest.setUrl(url);
        connectionRequest.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = connectionRequest.getResponseCode() == 200; //Code HTTP 200 OK
                connectionRequest.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(connectionRequest);
        return resultOK;
    }
    public ArrayList<Note> showAll() {
        String url = Database.BASE_URL + "note/api/show"; // Add Symfony URL Here
        connectionRequest.setUrl(url);
        connectionRequest.setPost(false);
        connectionRequest.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                noteArrayList = parseNote(new String(connectionRequest.getResponseData()));
                connectionRequest.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(connectionRequest);
        return noteArrayList;
    }
}
