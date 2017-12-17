package me.dragon.web.restcontroller;

import io.swagger.annotations.Api;
import me.dragon.utils.PictureUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by dragon on 12/10/2017.
 */
@Controller
@RequestMapping(value = "/api")
@Api(value = "PICTURE接口", tags = "PICTURE接口", description = "PICTURE接口")
public class PictureController extends BaseController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/getPictureURL", method = RequestMethod.POST)
    @ResponseBody
    public String getPictureURL(String imgUrl) {
        PictureUtil thread = new PictureUtil(imgUrl, response);
        thread.run();
        return null;
    }
}
