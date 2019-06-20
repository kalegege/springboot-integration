package com.wasu.springboot.integration.fastdfs;

import com.wasu.springboot.integration.redis.MasterSlaveHolder;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.reflect.annotation.ExceptionProxy;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;

public class FastdfsServiceImpl implements FastdfsService {

    private final static Logger LOGGER = LoggerFactory.getLogger(FastdfsServiceImpl.class);

    private TrackerClient trackerClient;
    private TrackerGroup trackerGroup;

    private String trackerServer = "localhost";

    private int connect_timeout = 2;

    private int network_timeout = 30;

    private String charset = "UTF-8";

    private int tracker_http_port = 82;

    private boolean anti_steal_token = false;

    private String secret_key = "1234567890";

    public TrackerClient getTrackerClient() {
        return trackerClient;
    }

    public void setTrackerClient(TrackerClient trackerClient) {
        this.trackerClient = trackerClient;
    }

    public TrackerGroup getTrackerGroup() {
        return trackerGroup;
    }

    public void setTrackerGroup(TrackerGroup trackerGroup) {
        this.trackerGroup = trackerGroup;
    }

    public String getTrackerServer() {
        return trackerServer;
    }

    public void setTrackerServer(String trackerServer) {
        this.trackerServer = trackerServer;
    }

    public int getConnect_timeout() {
        return connect_timeout;
    }

    public void setConnect_timeout(int connect_timeout) {
        this.connect_timeout = connect_timeout;
    }

    public int getNetwork_timeout() {
        return network_timeout;
    }

    public void setNetwork_timeout(int network_timeout) {
        this.network_timeout = network_timeout;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public int getTracker_http_port() {
        return tracker_http_port;
    }

    public void setTracker_http_port(int tracker_http_port) {
        this.tracker_http_port = tracker_http_port;
    }

    public boolean isAnti_steal_token() {
        return anti_steal_token;
    }

    public void setAnti_steal_token(boolean anti_steal_token) {
        this.anti_steal_token = anti_steal_token;
    }

    public String getSecret_key() {
        return secret_key;
    }

    public void setSecret_key(String secret_key) {
        this.secret_key = secret_key;
    }

    public void init() {
        initClientGlobal();
    }

    private void initClientGlobal() {
        try {
            ClientGlobal.setG_connect_timeout(getConnect_timeout() * 1000);

            ClientGlobal.setG_network_timeout(getNetwork_timeout() * 1000);

            ClientGlobal.setG_charset(getCharset());
            String sTrackerServerList = getTrackerServer();
            String[] trackerServerList = sTrackerServerList.split(",");
            InetSocketAddress[] trackerServer = new InetSocketAddress[trackerServerList.length];
            for (int i = 0; i < trackerServerList.length; i++) {
                String address = trackerServerList[i];
                String ip = address.split(":")[0];
                Integer port = Integer.parseInt(address.split(":")[1]);
                trackerServer[i] = new InetSocketAddress(ip, port);
            }
            trackerGroup = new TrackerGroup(trackerServer);
            ClientGlobal.setG_tracker_group(trackerGroup);
            ClientGlobal.setG_tracker_http_port(getTracker_http_port());
            ClientGlobal.setG_anti_steal_token(isAnti_steal_token());
            ClientGlobal.setG_secret_key(getSecret_key());
            trackerClient = new TrackerClient(trackerGroup);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage(), e);
        }
    }

    @Override
    public String[] uploadFile(InputStream filestream, String fileName, Long fileSize) throws FileNotFoundException, IOException, Exception {
        return uploadFile(filestream, null, fileName, fileSize);
    }

    @Override
    public String[] uploadFile(InputStream filestream, String groupName, String fileName, Long fileSize) throws FileNotFoundException, IOException, Exception {
        TrackerServer trackerServer = null;
        String[] fileId = null;
        try {
            if (trackerGroup == null) {
                initClientGlobal();
            }
            if (trackerClient == null) {
                trackerClient = new TrackerClient(trackerGroup);
            }
            trackerServer = trackerClient.getConnection();

            StorageServer storageServer = null;
            StorageClient storageClient = new StorageClient(trackerServer, storageServer);

            String fileExtName = fileName.substring(fileName.lastIndexOf(".") + 1);

            UploadStream uploadStream = new UploadStream(filestream, fileSize);

            NameValuePair[] metaList = new NameValuePair[3];
            metaList[0] = new NameValuePair("fleName", fileName);
            metaList[1] = new NameValuePair("fileExtName", fileExtName);
            metaList[2] = new NameValuePair("fileSize", String.valueOf(fileSize));

            fileId = storageClient.upload_file(groupName, fileSize, uploadStream, fileExtName, metaList);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage(), e);
        } finally {
            if (trackerServer != null) {
                trackerServer.close();
            }
            trackerServer = null;
        }
        return fileId;
    }

    @Override
    public byte[] downloadFile(String groupName, String fileId) throws IOException, Exception {
        return downloadFile(groupName,fileId,0,0);
    }

    @Override
    public byte[] downloadFile(String groupName, String fileId, long fileOffset, long downloadBytes) throws IOException, Exception {
        TrackerServer trackerServer=null;

        try{
            if (trackerGroup == null) {
                initClientGlobal();
            }
            if (trackerClient == null) {
                trackerClient = new TrackerClient(trackerGroup);
            }
            ClientGlobal.setG_tracker_group(trackerGroup);
            trackerServer = trackerClient.getConnection();

            StorageServer storageServer = null;
            StorageClient storageClient = new StorageClient(trackerServer, storageServer);

            byte[] result=storageClient.download_file(groupName,fileId,fileOffset,downloadBytes);
            return result;
        }catch (Exception e) {
            LOGGER.warn(e.getMessage(), e);
            throw e;
        } finally {
            if (trackerServer != null) {
                trackerServer.close();
            }
            trackerServer = null;
        }
    }

    @Override
    public FileInfo getFileInfo(String groupName, String fileld) throws IOException, Exception {
        try{
            StorageClient storageClient=getStorageClient();
            return storageClient.get_file_info(groupName,fileld);
        }catch(Exception e){
            LOGGER.warn(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public int deleteFile(String groupName, String remoteFileName) throws IOException, Exception {
        TrackerServer trackerServer=null;

        try{
            if (trackerGroup == null) {
                initClientGlobal();
            }
            if (trackerClient == null) {
                trackerClient = new TrackerClient(trackerGroup);
            }
            ClientGlobal.setG_tracker_group(trackerGroup);
            trackerServer = trackerClient.getConnection();

            StorageServer storageServer = null;
            StorageClient storageClient = new StorageClient(trackerServer, storageServer);

            int result=storageClient.delete_file(groupName,remoteFileName);
            return result;
        } finally {
            if (trackerServer != null) {
                trackerServer.close();
            }
            trackerServer = null;
        }
    }

    @Override
    public NameValuePair[] getMetaInfo(String groupName, String fileId) throws IOException, Exception {
        TrackerServer trackerServer=null;

        try{
            if (trackerGroup == null) {
                initClientGlobal();
            }
            if (trackerClient == null) {
                trackerClient = new TrackerClient(trackerGroup);
            }
            trackerServer = trackerClient.getConnection();

            StorageServer storageServer = null;
            StorageClient storageClient = new StorageClient(trackerServer, storageServer);

            NameValuePair[] metaList=storageClient.get_metadata(groupName,fileId);
            return metaList;
        } catch (Exception e) {
            LOGGER.warn(e.getMessage(), e);
            throw e;
        }finally {
            if (trackerServer != null) {
                trackerServer.close();
            }
            trackerServer = null;
        }
    }

    public StorageClient getStorageClient() {
        return new StorageClient();
    }
}
