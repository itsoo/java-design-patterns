/**
 * The MIT License
 * Copyright (c) 2014-2016 Ilkka Seppälä
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.iluwatar.model.view.presenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is the GUI implementation of the View component in the Model-View-Presenter pattern.
 */
public class FileSelectorJFrame extends JFrame implements FileSelectorView, ActionListener {

    /**
     * Default serial version ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The "OK" button for loading the file.
     */
    private JButton ok;

    /**
     * The cancel button.
     */
    private JButton cancel;

    /**
     * The information label.
     */
    private JLabel info;

    /**
     * The contents label.
     */
    private JLabel contents;

    /**
     * The text field for giving the name of the file that we want to open.
     */
    private JTextField input;

    /**
     * A text area that will keep the contents of the file opened.
     */
    private JTextArea area;

    /**
     * The panel that will hold our widgets.
     */
    private JPanel panel;

    /**
     * The Presenter component that the frame will interact with
     */
    private FileSelectorPresenter presenter;

    /**
     * The name of the file that we want to read it's contents.
     */
    private String fileName;

    /**
     * Constructor.
     */
    public FileSelectorJFrame() {
        super("File Loader");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setBounds(100, 100, 500, 200);

    /*
     * Add the panel.
     */
        this.panel = new JPanel();
        panel.setLayout(null);
        this.add(panel);
        panel.setBounds(0, 0, 500, 200);
        panel.setBackground(Color.LIGHT_GRAY);

    /*
     * Add the info label.
     */
        this.info = new JLabel("File Name :");
        this.panel.add(info);
        info.setBounds(30, 10, 100, 30);

    /*
     * Add the contents label.
     */
        this.contents = new JLabel("File contents :");
        this.panel.add(contents);
        this.contents.setBounds(30, 100, 120, 30);

    /*
     * Add the text field.
     */
        this.input = new JTextField(100);
        this.panel.add(input);
        this.input.setBounds(150, 15, 200, 20);

    /*
     * Add the text area.
     */
        this.area = new JTextArea(100, 100);
        JScrollPane pane = new JScrollPane(area);
        pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.panel.add(pane);
        this.area.setEditable(false);
        pane.setBounds(150, 100, 250, 80);

    /*
     * Add the OK button.
     */
        this.ok = new JButton("OK");
        this.panel.add(ok);
        this.ok.setBounds(250, 50, 100, 25);
        this.ok.addActionListener(this);

    /*
     * Add the cancel button.
     */
        this.cancel = new JButton("Cancel");
        this.panel.add(this.cancel);
        this.cancel.setBounds(380, 50, 100, 25);
        this.cancel.addActionListener(this);

        this.presenter = null;
        this.fileName = null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.ok.equals(e.getSource())) {
            this.fileName = this.input.getText();
            presenter.fileNameChanged();
            presenter.confirmed();
        } else if (this.cancel.equals(e.getSource())) {
            presenter.cancelled();
        }
    }

    @Override
    public void open() {
        this.setVisible(true);
    }

    @Override
    public void close() {
        this.dispose();
    }

    @Override
    public boolean isOpened() {
        return this.isVisible();
    }

    @Override
    public void setPresenter(FileSelectorPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public FileSelectorPresenter getPresenter() {
        return this.presenter;
    }

    @Override
    public void setFileName(String name) {
        this.fileName = name;
    }

    @Override
    public String getFileName() {
        return this.fileName;
    }

    @Override
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    @Override
    public void displayData(String data) {
        this.area.setText(data);
    }
}