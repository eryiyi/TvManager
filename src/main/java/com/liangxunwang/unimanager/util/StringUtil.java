package com.liangxunwang.unimanager.util;


import jxl.Workbook;
import jxl.format.CellFormat;
import jxl.write.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * author: zhl
 * Date: 2014/7/29
 * Time: 18:11
 * 类的功能、说明写在此处.
 */
public class StringUtil {
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static boolean isEmail(String str) {
        String pattern1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(pattern1);
        Matcher mat = pattern.matcher(str);
        return !mat.find();
    }

    /**
     * 取出一段字符串中的一个img标签
     */
    public static String selsrcSingle(String str) {
        String ss = "";
        Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");//<img[^<>]*src=[\'\"]([0-9A-Za-z.\\/]*)[\'\"].(.*?)>");
        Matcher m = p.matcher(str);
        while (m.find()) {
            ss = m.group(1);
        }
        return ss;
    }

    /**
     * 判断是否为手机号
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 校验是否为正整数
     * @param intStr
     * @return
     */
    public static final boolean isValidInteger(String intStr) {
        return (intStr == null) ? false : intStr.matches("\\d+");
    }

    /**
     * 查询文件夹下面所有文件名
     * path:文件夹地址  例："C://Documents and Settings//yinxm//デスクトップ//TestFile"
     * */
    public static String[] getFile(String path){
        // get file list where the path has
        File file = new File(path);
        // get the folder list
        File[] array = file.listFiles();
        String[] arrayTitle = new String[array.length];
        for(int i=0;i<array.length;i++){
            if(array[i].isFile()){
                // only take file name
//                System.out.println("^^^^^" + array[i].getName());
                arrayTitle[i] = array[i].getName();
                // take file path and name
//                System.out.println("#####" + array[i]);
                // take file path and name
//                System.out.println("*****" + array[i].getPath());
            }else if(array[i].isDirectory()){
                getFile(array[i].getPath());
            }
        }
        return arrayTitle;
    }
    /**
     * 删除某个文件夹下的所有文件夹和文件
     */
        public static boolean deletefile(String delpath)
                        throws FileNotFoundException, IOException {
                try {
                        File file = new File(delpath);
                        if (!file.isDirectory()) {
//                                System.out.println("不是文件夹");
                                file.delete();
                        } else if (file.isDirectory()) {
                                System.out.println("2");
                                String[] filelist = file.list();
                                for (int i = 0; i < filelist.length; i++) {
                                        File delfile = new File(delpath + "\\" + filelist[i]);
                                        if (!delfile.isDirectory()) {
//                                                System.out.println("path=" + delfile.getPath());
//                                                System.out.println("absolutepath="
//                                                                + delfile.getAbsolutePath());
//                                                System.out.println("name=" + delfile.getName());
                                                delfile.delete();
//                                                System.out.println("删除文件成功");
                                        } else if (delfile.isDirectory()) {
                                                deletefile(delpath + "\\" + filelist[i]);
                                        }
                                }
//                                file.delete();//这个是删除该文件夹的
                        }
                } catch (FileNotFoundException e) {
//                        System.out.println("deletefile()   Exception:" + e.getMessage());
                }
                return true;
        }



    /**
     * create the order info. 创建订单信息
     *
     */
    public static String getOrderInfo(String outTradeNo, String subject, String body, String price) {
        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + Constants.PARTNER + "\"";

        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + Constants.SELLER + "\"";

        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + outTradeNo + "\"";

        // 商品名称
        orderInfo += "&subject=" + "\"" + subject + "\"";

        // 商品详情
        orderInfo += "&body=" + "\"" + body + "\"";

        // 商品金额
        orderInfo += "&total_fee=" + "\"" + price + "\"";

        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + "\"" + Constants.ZFB_NOTIFY_URL
                + "\"";

        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";

        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";

        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";

        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";

        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";

        // 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
        // orderInfo += "&paymethod=\"expressGateway\"";

        return orderInfo;
    }

    /**
     * sign the order info. 对订单信息进行签名
     *
     * @param content
     *            待签名订单信息
     */
    public static String sign(String content) {
        return SignUtils.sign(content, Constants.RSA_PRIVATE);
    }



    /**
     *
     * 基本功能：替换指定的标签
     * @author linshutao
     * @param str
     * @param beforeTag   要替换的标签
     * @param tagAttrib   要替换的标签属性值
     * @param startTag    新标签开始标记
     * @param endTag   新标签结束标记
     * @return String
     */
    public   static String replaceHtmlTag(String str, String beforeTag,
                                          String tagAttrib, String startTag, String endTag) {
        //  String regxpForTag = "<//s*" + beforeTag + "//s+([^>]*)//s*>" ;
        String regxpForTag = "<//s*" + beforeTag + "//s+([^>]*)//s*" ;
        String regxpForTagAttrib = tagAttrib  ;
        //Pattern.CASE_INSENSITIVE 忽略大小写
        Pattern patternForTag = Pattern.compile (regxpForTag,Pattern. CASE_INSENSITIVE );
        Pattern patternForAttrib = Pattern.compile (regxpForTagAttrib,Pattern. CASE_INSENSITIVE );
        Matcher matcherForTag = patternForTag.matcher(str);
        StringBuffer sb = new StringBuffer ();
        boolean result = matcherForTag.find();
        // 循环找出每个 img 标签
        while (result) {
            StringBuffer sbreplace = new StringBuffer( "<img " );
            Matcher matcherForAttrib = patternForAttrib.matcher(matcherForTag
                    .group(1));
            if (matcherForAttrib.find()) {
                matcherForAttrib.appendReplacement(sbreplace, startTag
                        + matcherForAttrib.group(1) + endTag);
            }
            matcherForAttrib.appendTail(sbreplace);
            matcherForTag.appendReplacement(sb, sbreplace.toString());
            result = matcherForTag.find();
        }
        matcherForTag.appendTail(sb);
        return sb.toString();
    }

    //生成随机数字和字母,
    public static String getStringRandom(int length) {

//        String val = "";
//        Random random = new Random();
        //参数length，表示生成几位随机数
//        for(int i = 0; i < length; i++) {
//            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
//            if( "char".equalsIgnoreCase(charOrNum) ) {
//                //输出是大写字母还是小写字母
////                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
//                int temp = Math.abs(random.nextInt()) % 10 + 48; // 产生48到57的随机数(0-9的键位值)
//                val += (char)(random.nextInt(26) + temp);
//            } else if( "num".equalsIgnoreCase(charOrNum) ) {
//                val += String.valueOf(random.nextInt(10));
//            }
//        }
//        return val;
        StringBuilder str=new StringBuilder();//定义变长字符串
        Random random=new Random();
//随机生成数字，并添加到字符串
        for(int i=0;i<8;i++){
            str.append(random.nextInt(10));
        }
//将字符串转换为数字并输出
//        int num=Integer.parseInt(str.toString());
        return str.toString();
    }


    //topnumber拆分
    public static String[] arrStr(String str){
        //拆分
        int count = str.length()/4;//取整
        String[] arr = new String[count+1];
        arr[0] = str;
        for(int i=1;i<count;i++){
            if(str.length()>4){
                str = str.substring(0,str.length() - 4);//获得上一个父
                arr[i] = str;
            }
        }
        return arr;
    }

    //根据两个topnumber 找共同点
    public static String arrStr2arrStr2(String str1, String str2){

        //拆分
        int count1 = str1.length()/4;//取整
        String[] arr1 = new String[count1];
        arr1[0] = str1;
        for(int i=1;i<count1;i++){
            if(str1.length()>4){
                str1 = str1.substring(0,str1.length() - 4);//获得上一个父
                arr1[i] = str1;
            }
        }

        int count2 = str2.length()/4;//取整
        String[] arr2 = new String[count2];
        arr2[0] = str2;
        for(int i=1;i<count2;i++){
            if(str2.length()>4){
                str2 = str2.substring(0,str2.length() - 4);//获得上一个父
                arr2[i] = str2;
            }
        }

        //找共同点
        String strGtd = "";
        for(int i = 0; i <arr1.length; i ++){
            String strTmp = arr1[i];
            boolean flag = false;
            for(int j=0;j < arr2.length ; j++){
                String strTmp2 = arr2[j];
                if(strTmp2.equals(strTmp)){
                    //找到共同点了
                    strGtd = strTmp;
                    flag = true;
                    break;
                }
            }
            if(flag){
                break;
            }
        }

        String string1 = "";
        for(int i= 0;i < arr1.length ;i++){
            if(!arr1[i].equals(strGtd)){
                string1 += arr1[i] + ",";
            }
            if(arr1[i].equals(strGtd)){
                break;
            }
        }

        string1 += strGtd + ",";

        String strss = "";
        for(int i = 0;i < arr2.length ;i++){
            if(!arr2[i].equals(strGtd)){
                strss += arr2[i] + ",";
            }
            if(arr2[i].equals(strGtd)){
                break;
            }
        }
        String[] strrr = strss.split(",");
        for(int i = (strrr.length-1); i>=0 ; i--){
            string1 += strrr[i] + ",";
        }

        return string1;
    }
}
