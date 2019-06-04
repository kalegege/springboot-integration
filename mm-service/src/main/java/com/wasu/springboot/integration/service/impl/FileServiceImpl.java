package com.wasu.springboot.integration.service.impl;

import com.wasu.springboot.integration.constants.CommonConstant;
import com.wasu.springboot.integration.dao.FileDao;
import com.wasu.springboot.integration.entity.file.FileEntity;
import com.wasu.springboot.integration.service.FileService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service("fileService")
public class FileServiceImpl implements FileService {
    private static final Logger LOGGER= LoggerFactory.getLogger(FileServiceImpl.class);

    @Autowired
    private FileDao fileDao;

    @Override
    public void batchInsertFile(List<FileEntity> fileList) {
        if(CollectionUtils.isEmpty(fileList)){
            return ;
        }
        int increment=500;
        int insertNumber=500;
        if(fileList.size() > increment){
            int size=(fileList.size() - 1)/insertNumber + 1;
            int remainder=(fileList.size()%insertNumber);
            for(int i=0;i<size;i++){
                if((i == size - 1) && remainder > CommonConstant.NUMBER_0){
                    insertNumber=remainder;
                }
                this.fileDao.insert(fileList.subList(i*increment,i*increment+insertNumber));
            }
        }else{
            this.fileDao.insert(fileList);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public FileEntity uploadFile(MultipartFile mFile, FileEntity file) {
        try{
            if(null == mFile){
                return null;
            }
            file.setFileName(mFile.getOriginalFilename());
            file.setFileSize(mFile.getSize());

            String[] fileId=fileUploadService.uploadFile(mFile.getInputStream(),mFile.getOriginalFilename(),
                    mFile.getSize());
            if(null == fileId){
                return null;
            }
            file.setFileGroup(fileId[0]);
            file.setFilePath(fileId[1]);
            long result=fileDao.insert(file);
            if(result > 1){
                file.setId(result);
            }
            return file;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public FileEntity getFileById(FileEntity file) {
        if(null == file || file.getId() == null){
            return null;
        }
        FileEntity fileEntity=fileDao.getById(file.getId());
        if(null ==fileEntity){
            return null;
        }
        return fileEntity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteFile(FileEntity file) {
        FileEntity tempFile=getFileById(file);
        if(null != tempFile){
            fileDao.deleteById(file.getId());
        }
    }
}
