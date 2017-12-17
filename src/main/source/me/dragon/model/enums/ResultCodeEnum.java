package me.dragon.model.enums;

/**
 * @author dragon
 */
public enum ResultCodeEnum {
    GL000500(500, "未知异常"),
    GL000400(400, "参数不合法"),
	GL000403(403, "无权访问"),
	GL000404(404, "服务器找不到给定的资源"),
    // 系统异常
    ES000001(100001, "数据库操作异常"),
    ES000002(100002, "日期转换异常"),
    ES000003(100003, "用户验证异常"),
    ES000004(100004, "数字转换异常"),
    ES000005(100005, "工作台选择错误"),
    ES000006(100006, "会话超时,请刷新页面重试"),
    ES000007(403, "无权限访问"),
    ES000008(100008, "查询参数为空"),
	ES000009(100009, "登录超时请重新登录"),
	ES000010(100010, "操作频率过快, 您的帐号已被冻结"),
    // 业务异常
    EB000001("用户名或密码错误"),
    EB000002("用户已被禁止使用");
    private int code;
    private String msg;

    ResultCodeEnum(String msg) {
        this.msg = msg;
    }

    ResultCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String msg() {
        return msg;
    }
    public String code() {
        return this.name();
    }

    public static int getErrorCode(String code) {
        try {
            for (ResultCodeEnum ele : ResultCodeEnum.values()) {
                if (code.equals(ele.code())) {
                    return ele.getCode();
                }
            }
            return 500;
        } catch (Exception e) {
            e.printStackTrace();
            return 500;
        }
    }
}
