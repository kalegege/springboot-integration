package com.wasu.springboot.integration.fastdfs;

import org.csource.fastdfs.FileInfo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class FastdfsInputSteam extends InputStream{

    private FastdfsServiceImpl fastdfsHelper;

    private static final int MAX_BUFFER_SIZE = 1024 * 256;

    private String groupName;

    private String fileName;

    private long remoteFileSize = 0;

    private long remoteFileOffset = 0;

    private byte[] buf;

    private int count =0;

    private int pos =0 ;

    public FastdfsInputSteam(FastdfsServiceImpl fastdfsHelper, String groupName, String fileName) {
        this.fastdfsHelper = fastdfsHelper;
        this.groupName = groupName;
        this.fileName = fileName;

        init();
    }

    private void init(){
        try{
            FileInfo fileInfo=fastdfsHelper.getFileInfo(groupName,fileName);
            if(null == fileInfo){
                throw new FileNotFoundException();
            }
            remoteFileSize = fileInfo.getFileSize();
        }catch(Exception e){
            throw new RuntimeException();
        }
    }
    @Override
    public int read() throws IOException {
        if(loadBuffer() < 0){
            return -1;
        }
        return (pos < count) ? (buf[pos++] & 0xff) : -1;
    }

    private synchronized int loadBuffer() {
        if(pos < count){
            return 0;
        }

        if(remoteFileOffset > remoteFileSize){
            return -1;
        }

        try{
            long remoteRemainSize=remoteFileSize-remoteFileOffset;
            buf = fastdfsHelper.downloadFile(groupName,fileName,remoteFileOffset,
                    remoteRemainSize > MAX_BUFFER_SIZE?MAX_BUFFER_SIZE:remoteRemainSize);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        if(buf == null){
            return -1;
        }

        pos =0;
        count = buf.length;

        remoteFileOffset += count;

        return 1;
    }
}
