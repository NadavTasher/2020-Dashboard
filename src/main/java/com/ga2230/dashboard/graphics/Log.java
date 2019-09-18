package com.ga2230.dashboard.graphics;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ga2230.dashboard.communications.Broadcaster;
import com.ga2230.dashboard.communications.Communicator;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Log extends Panel {

    private JTextArea textArea;
    private JScrollPane scrollPane;

    public Log() {
        textArea = new JTextArea();
        scrollPane = new JScrollPane();
        textArea.setEditable(false);
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.GREEN);
//        scrollPane.add(textArea);
        add(textArea);
        Communicator.pullListener.listen(thing -> textArea.setText(beautify(thing.toString())));
    }

    private String beautify(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Object obj = mapper.readValue(json, Object.class);
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e) {
            return json;
        }
    }

    @Override
    public void setSize(int width, int height) {
        Dimension dimension = new Dimension(width, height);
        textArea.setPreferredSize(dimension);
        textArea.setMinimumSize(dimension);
        textArea.setMaximumSize(dimension);
        super.setSize(width, height);
    }
}
