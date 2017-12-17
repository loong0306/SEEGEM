package me.dragon.utils;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import me.dragon.config.ApplicationContextUtil;
import me.dragon.config.OSSConfigure;
import me.dragon.core.exception.BusinessException;
import me.dragon.core.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class OSSUploadUtil {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    private OSSConfigure ossConfigure = (OSSConfigure) ApplicationContextUtil.getBean("ossConfigure");

    @Value("${ossPath}")
    private String ossPath;

    public String uploadFile(InputStream inputStream, String fileName, String ossFilePath) {
        logger.info("==> 上传文件 fileName = {}", fileName);
        logger.info("==> 上传文件 ossFilePath = {}", ossFilePath);
        if (StringUtils.isStrsEmptyOrNull(fileName, ossFilePath)) {
            logger.error("服务器路径没有配置");
            throw new BusinessException("服务器路径没有配置");
        }
        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
        if(StringUtils.isEmpty(fileType)){
            throw new BusinessException("上传文件类型错误");
        }
        String contentType = ContextTypeUtils.contentType(fileType);
        if(contentType == null){
            throw new BusinessException("上传文件格式错误");
        }
        try {
            OSSClient ossClient = new OSSClient(ossConfigure.getEndpoint(), ossConfigure.getAccessKeyId(), ossConfigure.getAccessKeySecret());
            ossFilePath = ossFilePath.substring(0, ossFilePath.length()).replaceAll("\\\\", "/") + "/";
            //创建上传Object的Metadata
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(inputStream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            objectMetadata.setContentType(contentType);
            objectMetadata.setContentDisposition("inline;filename=" + fileName);
            //上传文件
            ossClient.putObject(ossConfigure.getBucketName(), ossFilePath + fileName, inputStream, objectMetadata);
            ossFilePath = ossPath + ossFilePath + fileName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ossFilePath;
    }

    public InputStream compress(InputStream in) throws IOException {
        BufferedImage srcImage = ImageIO.read(in);
        Graphics g = srcImage.getGraphics();
        g.dispose();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(srcImage, "jpg", os);
        in = new ByteArrayInputStream(os.toByteArray());
        return in;
    }
}
