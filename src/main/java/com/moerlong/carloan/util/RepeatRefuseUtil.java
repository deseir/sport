package com.moerlong.carloan.util;

import javax.servlet.http.HttpSession;

public class RepeatRefuseUtil {


    public static synchronized void repeatRefuse(String uuidParam, HttpSession session) throws Exception {

        String sessionUuid = (String)session.getAttribute("uuid");
        if(sessionUuid != null){
            if(sessionUuid.equals(uuidParam)){
                throw new Exception("uuid is same, refuse request");
            }else{
                session.setAttribute("uuid", uuidParam);
            }
        }else{
            session.setAttribute("uuid", uuidParam);
        }
    }
}
