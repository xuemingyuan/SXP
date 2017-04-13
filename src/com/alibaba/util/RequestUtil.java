package com.alibaba.util;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RequestUtil {
    private  static  final  Logger  logger = LoggerFactory.getLogger(RequestUtil.class);
    private  static  final  Base64  base64 = new Base64(true);
    public  static  final  String  LAST_PAGE = "lastPage";//δ��¼ʱ���ʵ�ҳ��
    public  static  final  String  REDIRECT_HOME = "/";//δ��¼ʱ��ת����ҳ
    public  static  final  String  LOGIN_HOME = "/index.jsp";//��¼�ɹ�������ҳ��
       
    
    /**
     * ���浱ǰ����
     */
    public static void saveRequest(HttpServletRequest request) {
        request.getSession().setAttribute(LAST_PAGE, RequestUtil.hashRequestPage(request));
        logger.debug("�����ص�url��sessionID:{}", request.getSession().getId());
        logger.debug("save request for {}", request.getRequestURI());
    }
    
    /**
     * ��������ҳ��
     * @param request
     * @return
     */
    public static  String hashRequestPage(HttpServletRequest request) {
        String reqUri = request.getRequestURI();
        String query = request.getQueryString();
        if (query != null) {
            reqUri += "?" + query;
        }
        String targetPage = null;
        try {
            targetPage = base64.encodeAsString(reqUri.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            //this does not happen
        }
        return targetPage;
    }
    
    /**
     * ȡ��֮ǰ���������
     * @return
     */
    public static String retrieveSavedRequest(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session == null) {
            return REDIRECT_HOME;
        }
        String HashedlastPage = (String) session.getAttribute(LAST_PAGE);
        if (HashedlastPage == null) {
            return LOGIN_HOME;
        } else {
            return retrieve(HashedlastPage);
        }
    }

    /**
     * ���������ҳ��
     * @param targetPage
     * @return
     */
    public static String retrieve(String targetPage) {
        byte[] decode = base64.decode(targetPage);
        try {
            String requestUri = new String(decode, "UTF-8");
            int i = requestUri.indexOf("/", 1);
            return requestUri.substring(i);
        } catch (UnsupportedEncodingException ex) {
            //this does not happen
            return null;
        }
    }

	public static void saveRequest() {
		// TODO Auto-generated method stub
		
	}

	public static String retrieveSavedRequest() {
		// TODO Auto-generated method stub
		return null;
	}
}
