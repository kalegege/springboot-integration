package com.wasu.springboot.integration.commons;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface FastdfsService {
    String[] uploadFile(InputStream filestream,String fileName,Long fileSize) throws FileNotFoundException,IOException,Exception;

    String[] uploadFile(InputStream filestream,String groupName,String fileName,Long fileSize) throws FileNotFoundException,IOException,Exception;

    byte[] downloadFile(String groupName,String fileId) throws IOException,Exception;

    byte[] downloadFile(String groupName,String fileId,long fileOffset,long downloadBytes) throws IOException,Exception;

//    FileInfo getFileInfo(String groupName,String fileId) throws IOException,Exception;

    int deleteFile(String groupName,String remoteFileName) throws IOException,Exception;

//    public NameValuePair[] getMetaInfo(String groupName,String fileId) throws IOException,Exception;
}
