package com.alkaid.test.jwt.controller;

import com.alkaid.test.jwt.util.Jwt;
import com.alkaid.test.jwt.util.JwtUtils;
import com.alkaid.test.jwt.vo.ReturnVo;
import net.minidev.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController {

    @RequestMapping(value="/login",method = RequestMethod.POST)
    public ReturnVo login(@RequestBody Map param, HttpServletResponse
            response) {
        String username = (String)param.get("username");
        String password = (String)param.get("password");
        if(username.equals("admin") && password.equals("123456")){
            //把token返回给客户端-->客户端保存至cookie-->客户端每次请求附带cookie参数
            String JWT = JwtUtils.createJWT("1", username, 30*60*1000);
            return ReturnVo.ok(JWT);
        }else{
            return ReturnVo.error();
        }
    }

    @RequestMapping(value="/jwt/login",method = RequestMethod.POST)
    public ReturnVo jwtLogin(@RequestBody Map param, HttpServletResponse
            response) throws IOException {
        String username = (String)param.get("username");
        String password = (String)param.get("password");
        if(username.equals("admin") && password.equals("123456")){
            JSONObject resultJSON=new JSONObject();
            //生成token
            Map<String , Object> payload = new HashMap<String, Object>();
            Date date = new Date();
            payload.put("uid", "admin");//用户ID
            payload.put("iat", date.getTime());//生成时间
            payload.put("ext",date.getTime()+1000*60*60);//过期时间1小时
            String token = Jwt.createToken(payload);

            resultJSON.put("success", true);
            resultJSON.put("msg", "登陆成功");
            resultJSON.put("token", token);

//            response.setContentType("application/json;charset=UTF-8");
//            response.setCharacterEncoding("UTF-8");
//            PrintWriter out = response.getWriter();
//            out.println(resultJSON.toJSONString());
//            out.flush();
//            out.close();

            return ReturnVo.ok(token);
        }else{
            return ReturnVo.error();
        }
    }

}
