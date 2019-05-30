package com.wasu.springboot.integration.service.impl;

import com.wasu.springboot.integration.constants.CommonConstant;
import com.wasu.springboot.integration.dao.FileDao;
import com.wasu.springboot.integration.entity.file.FileEntity;
import com.wasu.springboot.integration.service.FileService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
}
