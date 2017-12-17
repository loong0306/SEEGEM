package me.dragon.web.restcontroller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import me.dragon.core.exception.BusinessException;
import me.dragon.core.utils.base.WrapMapper;
import me.dragon.core.utils.base.Wrapper;
import me.dragon.model.vo.FacebookVO;
import me.dragon.model.vo.InstagramVO;
import me.dragon.service.FacebookService;
import me.dragon.service.InstagramService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by dragon on 12/9/2017.
 */
@Controller
@RequestMapping(value = "/api", produces = {"application/json;charset=UTF-8"})
@Api(value = "INSTAGRAM接口", tags = "INSTAGRAM接口", description = "INSTAGRAM接口", produces = "application/json;charset=utf-8")
public class InstagramController extends BaseController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private InstagramService instagramService;

    @RequestMapping(value = "/getInstagram", method = RequestMethod.POST)
    @ResponseBody
    public Wrapper<?> getInstagram() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss:SSS");
        logger.info("时间：[" + sdf.format(new Date()) + "] 用户访问：");
        try {
            PageHelper.startPage(1, 20);
            List<InstagramVO> instagramVOS = instagramService.apiGetInstagramVoList();
            PageInfo<InstagramVO> instagramVOPageInfo = new PageInfo<InstagramVO>(instagramVOS);
            return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, instagramVOPageInfo);
        } catch (BusinessException ex) {
            logger.error("获取INSTAGRAM出现异常{}", ex.getMessage());
            return WrapMapper.wrap(Wrapper.ERROR_CODE, Wrapper.ERROR_MESSAGE, ex.getMessage());
        } catch (Exception ex) {
            logger.error("获取INSTAGRAM出现异常{}", ex.getMessage());
            return WrapMapper.wrap(Wrapper.ERROR_CODE, Wrapper.ERROR_MESSAGE, ex.getMessage());
        }
    }
}
