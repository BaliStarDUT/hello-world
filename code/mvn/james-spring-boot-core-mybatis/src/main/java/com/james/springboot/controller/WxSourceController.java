package com.james.springboot.controller;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.result.WxBatchGetMaterialResult;
import com.soecode.wxtools.bean.result.WxMaterialCountResult;
import com.soecode.wxtools.bean.result.WxMediaUploadResult;
import com.soecode.wxtools.exception.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by James Yang on 2017/6/29 0029 下午 2:20.
 */
@RestController
@RequestMapping(value = "/wxsource")
public class WxSourceController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    // 实例化 统一业务API入口
    private IService iService = new WxService();
    @RequestMapping(value = "/get_media_list",method = RequestMethod.GET)
    public String wxBatchGetMedia(@RequestParam(name = "type",required = false,defaultValue = WxConsts.MEDIA_IMAGE) String type,
                                  @RequestParam(name = "offset",required = false,defaultValue = "0") int offset,
                                  @RequestParam(name = "count",required = false,defaultValue = "5") int count){
        WxBatchGetMaterialResult batchGetMaterialResult = null;
        try {
            batchGetMaterialResult = iService.batchGetMeterial(WxConsts.MEDIA_IMAGE,0,5);
        } catch (WxErrorException e) {
            logger.error("获取素材列表失败："+e.getMessage());
            e.printStackTrace();
        }
        return batchGetMaterialResult.toString();
    }

    @RequestMapping(value = "/get_media_count",method = RequestMethod.GET)
    public String wxGetMediaCount(){
        WxMaterialCountResult materialCountResult = null;
        try {
            materialCountResult = iService.getMaterialCount();
        } catch (WxErrorException e) {
            logger.error("获取素材总数失败："+e.getMessage());
            e.printStackTrace();
        }
        return materialCountResult.toString();
    }

    @RequestMapping(value = "/tmp_media",method = RequestMethod.POST)
    public String wxUploadTmpMedia(@RequestParam(value = "type",required = true) String type,
                                @RequestParam(value = "media",required = true) MultipartFile media){
        WxMediaUploadResult wxMediaUploadResult = null;
        if (media.isEmpty()|| StringUtils.isEmpty(type)) {
            logger.error("上传临时素材失败，临时素材为空："+media.getOriginalFilename());
        }
        try {
            wxMediaUploadResult = iService.uploadTempMedia(WxConsts.MEDIA_IMAGE,"jpg",media.getInputStream());
            logger.debug("上传临时素材成功："+wxMediaUploadResult.toString());
        } catch (WxErrorException e) {
            logger.error("上传临时素材失败："+e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("上传临时素材失败："+e.getMessage());
            e.printStackTrace();
        }
        return wxMediaUploadResult.toString();
    }

    @RequestMapping(value = "/tmp_media",method = RequestMethod.GET)
    public String wxTmpMedia(
            @RequestParam(value = "mediaid",required = true) String mediaid){
        if (StringUtils.isEmpty(mediaid)) {
            logger.error("获取临时素材失败，mediaid为空："+mediaid);
        }
        File file = null;
        try {
//            Files.copy(file.getInputStream(), Paths.get(ROOT, file.getOriginalFilename()));
            file = iService.downloadTempMedia(mediaid,new File("/"));
            logger.debug("下载临时素材成功："+file.getPath());
        } catch (WxErrorException e) {
            logger.error("下载临时素材失败："+e.getMessage());
            e.printStackTrace();
        }
        return "下载临时素材成功："+file.getPath();
    }

    @RequestMapping(value = "/media",method = RequestMethod.POST)
    public String wxUploadMedia(@RequestParam(value = "type",required = true) String type,
                                   @RequestParam(value = "media",required = true) MultipartFile media){
        WxMediaUploadResult wxMediaUploadResult = null;
        if (media.isEmpty()|| StringUtils.isEmpty(type)) {
            logger.error("上传永久素材失败，临时素材为空："+media.getOriginalFilename());
        }
        try {
            wxMediaUploadResult = iService.uploadMedia(WxConsts.MEDIA_IMAGE,null,null);
            logger.debug("上传临时素材成功："+wxMediaUploadResult.toString());
        } catch (WxErrorException e) {
            logger.error("上传临时素材失败："+e.getMessage());
            e.printStackTrace();
        }
        return wxMediaUploadResult.toString();
    }
}
