package com.nine.backend.service;

import ch.qos.logback.core.util.StringUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson2.JSON;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nine.backend.mapper.AdminMapper;
import com.nine.backend.mapper.DoctorMapper;
import com.nine.backend.mapper.PatientMapper;
import com.nine.backend.mapper.SignInMapper;
import com.nine.backend.pojo.Admin;
import com.nine.backend.pojo.Doctor;
import com.nine.backend.pojo.Patient;
import com.nine.backend.utils.JwtUtils;
import com.nine.backend.utils.Result;
import com.nine.backend.utils.SendMessage;
import com.wf.captcha.SpecCaptcha;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author Huang_ruijie
 * @version 1.0
 * @date 2024/8/20 上午11:18
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    PatientMapper patientMapper;
    @Autowired
    DoctorMapper doctorMapper;
    @Autowired
    SignInMapper signInMapper;
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public void captcha(HttpServletRequest request,HttpServletResponse response) throws IOException {
        // 创建Captcha对象
        SpecCaptcha captcha = new SpecCaptcha(120, 40);//尺寸
        // 获取验证码文本
        String verCode = captcha.text().toLowerCase();
        System.out.println("Captcha Session ID: " + request.getSession().getId());
        request.getSession().setAttribute("CAPTCHA", verCode);
        captcha.out(response.getOutputStream());
    }

    @Override
    public Result login(HttpServletRequest request, HttpServletResponse response) {
        //从请求体获取用户的输入
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String captcha = request.getParameter("code").toLowerCase();

        if (StringUtil.isNullOrEmpty(phone)) return Result.error("请输入手机号");
        if (StringUtil.isNullOrEmpty(password)) return Result.error("请输入密码");
        if (StringUtil.isNullOrEmpty(captcha)) return Result.error("请输入验证码");

        //角色（患者 = 0，医生 = 1）
        int role = 0;//默认为患者
        //验证手机号是否被注册(医生、管理员账号提前被录入)
        //先在患者表中查手机号
        QueryWrapper<Patient> queryWrapper_01 = new QueryWrapper<>();
        queryWrapper_01.eq("phone", phone);
        if (patientMapper.selectOne(queryWrapper_01) == null) {
            //再从医生表中查
            QueryWrapper<Doctor> queryWrapper_02 = new QueryWrapper<>();
            queryWrapper_02.eq("phone", phone);
            Doctor doctor = doctorMapper.selectOne(queryWrapper_02);
            if (doctor == null) {
                return Result.error("手机号未注册");
            }
            if(doctor.getDuty() == 0){
                return Result.error("当前医生未在岗，请联系管理员");
            }
            else role = 1;
        }
        //拿到Session中的验证码答案进行比对
        System.out.println("Captcha Session ID: " + request.getSession().getId());
        String sessionCode = request.getSession().getAttribute("CAPTCHA").toString();
        if (StringUtil.isNullOrEmpty(sessionCode)) {
            return Result.error("验证码为空");
        }
        if (captcha.equals(sessionCode)) {
            //对密码进行加密
            String password_MD5 = SecureUtil.md5(password);
            if (role == 0) {//当用户为患者
                QueryWrapper<Patient> queryWrapper_03 = new QueryWrapper<>();
                //构造条件语句，用加密后的密码与数据库比对
                queryWrapper_03.eq("phone", phone)
                        .eq("password", password_MD5);
                Patient patient = patientMapper.selectOne(queryWrapper_03);
                if (patient != null) {
                    //将用户的id放入Session中
                    request.getSession().setAttribute("patientId", patient.getId());
                } else return Result.error("手机号或密码错误");
            }
            if (role == 1) {//当用户为医生
                QueryWrapper<Doctor> queryWrapper_04 = new QueryWrapper<>();
                queryWrapper_04.eq("phone", phone)
                        .eq("password", password_MD5);
                Doctor doctor = doctorMapper.selectOne(queryWrapper_04);
                if (doctor != null) {
                    request.getSession().setAttribute("doctorId", doctor.getId());
                } else return Result.error("手机号或密码错误");
            }
            //生成JWT令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put("phone", phone);
            String jwt = JwtUtils.generateJWT(claims);
            //返回JWT
            Cookie cookie = new Cookie("token", jwt);
            cookie.setMaxAge(60 * 60);//有效期
            response.addCookie(cookie);
            Map<String, Object> map = new HashMap<>();
            map.put("role", role);
            return Result.success(map);
        }
        return Result.error("验证码错误");
    }

    @Override
    public Result sendSms(HttpServletRequest request) throws Exception {
        String phone = request.getParameter("phoneNum");
        //校验手机号是否已经注册
        QueryWrapper<Patient> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone);
        Patient patient = patientMapper.selectOne(queryWrapper);
        if (patient != null) {
            return Result.error("账号已注册,请直接登录");
        }
        //生成四位随机验证码
        Random random = new Random();
        String code = String.valueOf(1000 + random.nextInt(9000));
        String templateParam = String.format("{\"code\":\"%s\"}", code);
        //配置信息
        com.aliyun.dysmsapi20170525.Client client = SendMessage.createClient();
        com.aliyun.dysmsapi20170525.models.SendSmsRequest sendSmsRequest = new com.aliyun.dysmsapi20170525.models.SendSmsRequest()
                .setSignName("八一五医院")
                .setTemplateCode("SMS_471935026")
                .setPhoneNumbers(phone)
                .setTemplateParam(templateParam);
        com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
        //发送短信
        //SendSmsResponse sendSmsResponse = client.sendSms(sendSmsRequest);
        // 响应包含服务端响应的 body 和 headers
        //System.out.println(JSON.toJSONString(sendSmsResponse));
        SendSmsResponse sendSmsResponse = client.sendSmsWithOptions(sendSmsRequest, runtime);
        System.out.println(JSON.toJSONString(sendSmsResponse));
        //将验证码写入Session
        request.getSession().setAttribute("SMSCODE", code);
        return Result.success();
    }

    @Override
    public Result signIn(HttpServletRequest request) {
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String messageCode = request.getParameter("messageCode");
        //判断用户输入时候为空
        if (StringUtil.isNullOrEmpty(phone)
                || StringUtil.isNullOrEmpty(password)
                || StringUtil.isNullOrEmpty(messageCode)){
            return Result.error("请填写完整信息");
        }

        //判断用户是否获取验证码
        String code;
        try {
            code = request.getSession().getAttribute("SMSCODE").toString();
        } catch (Exception e) {
            return Result.error("没有获取验证码");
        }

        if (StringUtil.isNullOrEmpty(code)) {
            return Result.error("验证码为空");
        }
        if (code.equals(messageCode)) {
            //对密码进行加密
            String password_MD5 = SecureUtil.md5(password);
            Patient patient = new Patient();
            patient.setPhone(phone);
            patient.setPassword(password_MD5);
            patient.setMessageCode(messageCode);
            patientMapper.insert(patient);
            return Result.success();
        }
        return Result.error("验证码错误");
    }

    @Override
    public Result logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0); // 立即删除cookie
        cookie.setPath("/"); // 确保路径正确
        response.addCookie(cookie);
        return Result.success();
    }
}