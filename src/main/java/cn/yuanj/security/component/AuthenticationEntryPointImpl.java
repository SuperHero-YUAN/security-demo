package cn.yuanj.security.component;

import cn.yuanj.security.utils.Result;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * @author YuanJ
 * @date 2022/3/19 18:00
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {
    private static final long serialVersionUID = -1L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        Gson gson = new Gson();
        Integer code = HttpStatus.UNAUTHORIZED.value();
        response.setStatus(200);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(gson.toJson(Result.fail(code, "请求访问：" + request.getRequestURI() + "，认证失败，无法访问系统资源")));
    }
}
