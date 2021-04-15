/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dz.apigw.utils;

/**
 *
 * @author Administrator
 */
public class HTTPStatusCode {
    public final static int ERRCODE_SUCCESS=0;
    public final static int ERRCODE_BADCREDENTIALS=-1;
    public final static int ERRCODE_USERDISABLED=-2;
    public final static int ERRCODE_NOTSUCCESSED=-1;
    
    
    public final static String ERRCODE_TEXT_OK="OK";

    public final static int PAYMENT_NOTIFICATION_PARAMS_REGISTER=1;
    public final static int PAYMENT_NOTIFICATION_PARAMS_CANCEL=2;
    public final static int PAYMENT_NOTIFICATION_PARAMS_DOWNLOAD=0;
    public final static int PAYMENT_NOTIFICATION_PARAMS_RENEW=4;
    //ISDN STATUS
    public final static int ISDN_STATUS_NOT_REGISTERED = 1;
    public final static int ISDN_STATUS_UNDER_KEEPING = 2;
    public final static int ISDN_STATUS_REGISTERED = 6;

    public final static String ISDN_STATUS_MESSAGE_NOT_REGISTERED="Bạn đã thực hiện giữ số {isdn} thành công. Mã xác thực: {mxt}. Mã giao dịch: {tran_id}";
    public final static String ISDN_STATUS_MESSAGE_UNDER_KEEPING="Bạn đã thực hiện giữ số {isdn} không thành công do số này đang trong tình trạng được giữ số.";
    public final static String ISDN_STATUS_MESSAGE_REGISTERED="Bạn đã thực hiện giữ số {isdn} không thành công do số này đã đăng ký.";
}
