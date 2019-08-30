package com.kingofthevim.game.engine.serialization;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Serialization {

    public ArrayList<String> getFileNames(String folder){

        FileHandle[] files = Gdx.files.internal(folder).list();
        ArrayList<String> filePaths = new ArrayList<>();
        for(FileHandle f : files) filePaths.add(f.name());
        Collections.sort(filePaths);

        return filePaths;
    }

    public ArrayList<String> getFilePaths(String folder){

        FileHandle[] files = Gdx.files.internal(folder).list();
        ArrayList<String> filePaths = new ArrayList<>();
        for(FileHandle f : files) filePaths.add(f.path());
        Collections.sort(filePaths);

        return filePaths;
    }

    public Queue<String> getFilePathsAsQueue(String folder){
        ArrayList<String> paths = getFilePaths(folder);
        Queue<String> queue = new LinkedList<>();
        queue.addAll(paths);
        return queue;
    }
}
