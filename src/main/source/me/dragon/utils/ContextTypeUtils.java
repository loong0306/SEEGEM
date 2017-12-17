package me.dragon.utils;

import me.dragon.core.exception.BusinessException;
import me.dragon.core.utils.StringUtils;
import me.dragon.model.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Description 文件头工具类 </p>
 */
public class ContextTypeUtils {

    private static Logger logger = LoggerFactory.getLogger(ContextTypeUtils.class);

    public static String contentType(String fileType) {
        if (StringUtils.isSNullOrEmpty(fileType)) {
            logger.error("判断OSS服务文件上传时文件 fileType is null");
            throw new BusinessException("传入文件类型为空");
        }
        fileType = fileType.toUpperCase(); // 转成大写
        String contentType = null;
        if (fileType.equals("BMP")) {
            contentType = Constants.IMAGE_BMP;
        }
        if (fileType.equals("GIF")) {
            contentType = Constants.IMAGE_GIF;
        }
        if (fileType.equals("JPEG") || fileType.equals("JPG") || fileType.equals("PNG")) {
            contentType = Constants.IMAGE_JPEG;
        }
        if (fileType.equals("HTML")) {
            contentType = Constants.TEXT_HTML;
        }
        if (fileType.equals("TXT")) {
            contentType = Constants.TEXT_PLAIN;
        }
        if (fileType.equals("VSD")) {
            contentType = Constants.APPLICATION_VSD;
        }
        if (fileType.equals("PPTX") || fileType.equals("PPT")) {
            contentType = Constants.APPLICATION_PPT;
        }
        if (fileType.equals("DOCX") || fileType.equals("DOC")) {
            contentType = Constants.APPLICATION_DOC;
        }
        if (fileType.equals("XML")) {
            contentType = Constants.TEXT_XML;
        }
        return contentType;
    }
}
